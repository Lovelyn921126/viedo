/*
 * FileName: EventQueue.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.disruptor;

import org.springframework.data.redis.core.RedisTemplate;

/**
 * <p>
 * Description:
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
    private RedisTemplate redisTemplate;
    /**
     * 本地任务失败重试次数
     */
    private Integer processingErrorRetryCount;
    /**
     * 队列名称
     */
    private String queueName;
    /**
     *队列镜像的大小
     */
    private long maxBakSize;

    /**
     * @return the redisTemplate
     */
    public RedisTemplate getRedisTemplate() {
        return redisTemplate;
    }

    /**
     * @param redisTemplate the redisTemplate to set
     */
    public void setRedisTemplate(RedisTemplate redisTemplate) {
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

    /**
     * @return
     */
    public String next() {
        // TODO Auto-generated method stub
        return null;
    }
}
