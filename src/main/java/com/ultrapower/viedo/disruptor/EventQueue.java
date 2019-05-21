/*
 * FileName: EventQueue.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.disruptor;

import java.time.LocalTime;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.data.redis.core.RedisTemplate;

import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;
import com.ultrapower.viedo.utils.cache.gauvaCache.GuavaCache;

/**
 * <p>
 * Description:  访问 redis 队列
 * </p>
 *
 * @author dell
 * @version 4.1

 * <p>
 * History:
 *
 * Date                     Author         Version     Description
 * ---------------------------------------------------------------------------------
 * 2019年4月10日 下午4:43:46          dell        4.1         To create
 * </p>
 *
 * @since
 * @see
 */
public class EventQueue {
    /**
     * 本地的redis 客户端
     */
    private RedisTemplate<String, String> redisTemplate;
    /**
     * 本地任务失败重试次数
     */
    private Integer processingErrorRetryCount;
    /**
     * 等待队列名称
     */
    private String queueName;
    /**
     *队列镜像的大小
     */
    private long maxBakSize;
    String localIp = "39.106.59.1444";
    /**
     * 本地处理队列
     */
    private String processingQueueName;
    /**
     *  失败队列的名称
     */
    private String failQueueName;
    /**
     *  备份队列的名称
     */
    private String bakQueueName;
    /**
     * 记录是吧的次数
     */
    private LoadingCache<String, AtomicInteger> failCache = GuavaCache.getDefaultAtomicLoadingCache();

    Lock lock = new ReentrantLock();
    //如果等待队列 小于10000 则会进行重拍
    //通过lrem 先删除 然后在通过lpush 进行重排  如果大于10000 因为遍历list性能会变的很差 则此时 不会进行排重
    //数据同时 会被放入 则 此时不会进行排重 数据同时放入 备份队列 当队列满了时  使用 FIFO 移除最新插入的的任务
    final static EventQueueScript ENQUEUE_TO_LIFT_REIDS_SCRIPT = new EventQueueScript("local remCount=0 " + "if redis.call('llen',KEYS[1])<10000 " + "then remCount=redis.call('lrem',KEYS[1],1,KEYS[2]) end " + "redis.call('lpush',KEYS[1],KEYS[2]) " + "if tonumber(KEYS[4])<0 " + "then return nil end " + "local len=redis.call('llen',KEYS[3]) " + "if len >tonumber(KEYS[4]) " + "then redis.call('lpop',KEYS[3]) end " + "redis.call('rpush',KEYS[3],KEYS[2])");
    /**
     * 添加等待队列 到队尾
     */
    final static EventQueueScript ADD_TO_BACK_REDIS_SCRIPT = new EventQueueScript("redis.call('lrem',KEYS[1],ARGV[1]) " + "redis.call('RPUSH',KEYS[2],ARGV[1])");
    /**
     * 如果超过重试次数 则 加入失败队列
     */
    final static EventQueueScript ADD_TO_FAIL_QUEUE_REIDS_SCRIPT = new EventQueueScript("redis.call('lrem',KEYS[1],ARGV[1]) " + "redis.call('RPUSH',KEYS[2],ARGV[1])");

    /**
     * @return the redisTemplate
     */
    public RedisTemplate<String, String> getRedisTemplate() {
        return redisTemplate;
    }

    /**
     * @param redisTemplate the redisTemplate to set
     */
    public void setRedisTemplate(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * @return the processingErrorRetryCount
     */
    public Integer getProcessingErrorRetryCount() {
        return processingErrorRetryCount;
    }

    /**
     * @param processingErrorRetryCount the processingErrorRetryCount to set
     */
    public void setProcessingErrorRetryCount(Integer processingErrorRetryCount) {
        this.processingErrorRetryCount = processingErrorRetryCount;
    }

    /**
     * @return the queueName
     */
    public String getQueueName() {
        return queueName;
    }

    /**
     * @param queueName the queueName to set
     */
    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    /**
     * @return the maxBakSize
     */
    public long getMaxBakSize() {
        return maxBakSize;
    }

    /**
     * @param maxBakSize the maxBakSize to set
     */
    public void setMaxBakSize(long maxBakSize) {
        this.maxBakSize = maxBakSize;
    }

    /**  next 是从redis 等待 队列中获取任务 并推送到本地任务处理队列 然后返回此任务，放入本地队列时 使用了 rightPopAndLeftPush目的是为了防止因为网络
     *  异常导致任务丢失  因为发生网络报警时 需要告警 然后人工介入 或者启动一个worker 定期检查队列内容是否长时间未消费
     *  如果长时间未消费 则应该再转回等待队列 如果队列没有任务 则应该短暂休息一会 然后重试 不应该死循环造成耗死cpu
     * @return
     */
    public String next() {
        System.out.println("eventQueue----next");
        while (true) {
            //将任务 从等待队列 移入 本地处理队列
            String id = redisTemplate.opsForList().rightPopAndLeftPush(queueName, getProcessingQueueName());

            if (id != null) {
                return id;
            }
            //lock.lock();
        }
    }

    public void success(String id) {
        redisTemplate.opsForList().remove(getProcessingQueueName(), 0, id);
    }

    public void fail(final String id) throws ExecutionException {
        int failCount = failCache.get(id).incrementAndGet();
        if (failCount < processingErrorRetryCount) {
            ADD_TO_BACK_REDIS_SCRIPT.exec(redisTemplate, Lists.newArrayList(getProcessingQueueName(), queueName), id);
        } else {
            ADD_TO_FAIL_QUEUE_REIDS_SCRIPT.exec(redisTemplate, Lists.newArrayList(getProcessingQueueName(), getFailQueueName()), id);
        }

    }

    public void enqueueToBack(final String id) {
        ENQUEUE_TO_LIFT_REIDS_SCRIPT.exec(getRedisTemplate(), Lists.newArrayList(queueName, id, getBakQueueName(), "4000"));
    }

    /**
     * @return the processingQueueName
     */
    public String getProcessingQueueName() {
        return getQueueName() + "_processing_queue_" + localIp;
    }

    /**
     * @param processingQueueName the processingQueueName to set
     */
    public void setProcessingQueueName(String processingQueueName) {
        this.processingQueueName = processingQueueName;
    }

    /**
     * @return the failQueueName
     */
    public String getFailQueueName() {
        return getQueueName() + "_failed_queue";
    }

    /**
     * @param failQueueName the failQueueName to set
     */
    public void setFailQueueName(String failQueueName) {
        this.failQueueName = failQueueName;
    }

    /**
     * @return the bakQueueName
     */
    public String getBakQueueName() {
        return getQueueName() + "_bak_queue_" + LocalTime.now().getHour();
    }

    /**
     * @param bakQueueName the bakQueueName to set
     */
    public void setBakQueueName(String bakQueueName) {
        this.bakQueueName = bakQueueName;
    }

    /**
     * @return the failCache
     */
    public LoadingCache<String, AtomicInteger> getFailCache() {
        return failCache;
    }

    /**
     * @param failCache the failCache to set
     */
    public void setFailCache(LoadingCache<String, AtomicInteger> failCache) {
        this.failCache = failCache;
    }
}
