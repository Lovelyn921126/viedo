package com.ultrapower.viedo.utils.cache.gauvaCache;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.Weigher;

public class GuavaCache {

    private static int concurrencyLevel = 4;
    private static long TTLTime = 5000;
    private static long TTITime = 5000;
    private static long maximumSize = 10000;
    private static long maximumWeight = 150;

    /**
    *  获取默认的无加载的guava 缓存 TTL，TTI 时间均为5s
    * @return
    */
    public static Cache<Object, Object> getDefaultCache() {
        //maximumSize 缓存容量
        // expireAfterWrite 设置缓存的TTL 缓存数据给定的时间没有写 则被回收
        //expireAfterAccess tti    缓存数据给定的时间没有被读 则被回收
        //weakKeys weakValues 设置弱引用
        //sofrValue  设置 软引用
        //concurrencyLevel  并发级别
        // recordStats 启动记录统计信息
        return getCache(concurrencyLevel, TTLTime, TTITime, maximumSize);
    }

    /**
     *  获取guava 缓存
     * @param concurrencyLevel  并发级别 最大16
     * @param TTLTime 设置缓存的TTL 缓存数据给定的时间没有写 则被回收  （毫秒）
     * @param TTITime   缓存数据给定的时间没有被读 则被回 （毫秒）
     * @param maximumSize 缓存容量
     * @return
     */
    public static Cache<Object, Object> getCache(int concurrencyLevel, long TTLTime, long TTITime, long maximumSize) {
        //maximumSize 缓存容量
        // expireAfterWrite 设置缓存的TTL 缓存数据给定的时间没有写 则被回收
        //expireAfterAccess tti    缓存数据给定的时间没有被读 则被回收
        //weakKeys weakValues 设置弱引用
        //sofrValue  设置 软引用
        //concurrencyLevel  并发级别
        // recordStats 启动记录统计信息
        return CacheBuilder.newBuilder().concurrencyLevel(concurrencyLevel).expireAfterWrite(TTLTime, TimeUnit.MILLISECONDS).expireAfterAccess(TTITime, TimeUnit.MILLISECONDS).maximumSize(maximumSize).build();
    }

    /**
     * 获取指定的计时器
     * @return
     */
    public static LoadingCache<String, AtomicInteger> getAtomicLoadingCache(int concurrencyLevel, long TTLTime, long TTITime, long maximumSize) {
        //maximumSize 缓存容量
        // expireAfterWrite 设置缓存的TTL 缓存数据给定的时间没有写 则被回收
        //expireAfterAccess tti    缓存数据给定的时间没有被读 则被回收
        //weakKeys weakValues 设置弱引用
        //sofrValue  设置 软引用
        //concurrencyLevel  并发级别
        // recordStats 启动记录统计信息
        return CacheBuilder.newBuilder().concurrencyLevel(concurrencyLevel).expireAfterWrite(TTLTime, TimeUnit.MILLISECONDS).expireAfterAccess(TTITime, TimeUnit.MILLISECONDS).maximumSize(maximumSize).build(new CacheLoader<String, AtomicInteger>() {
            @Override
            public AtomicInteger load(String key) throws Exception {
                return new AtomicInteger(0);

            };
        });
    }

    /**
     * 获取指定的加载器的guava cache
     * @return
     */
    public static LoadingCache<String, Object> getAtomicLoadingCache(int concurrencyLevel, long TTLTime, long TTITime, long maximumSize, CacheLoader<? super String, Object> loader) {
        //maximumSize 缓存容量
        // expireAfterWrite 设置缓存的TTL 缓存数据给定的时间没有写 则被回收
        //expireAfterAccess tti    缓存数据给定的时间没有被读 则被回收
        //weakKeys weakValues 设置弱引用
        //sofrValue  设置 软引用
        //concurrencyLevel  并发级别
        // recordStats 启动记录统计信息
        return CacheBuilder.newBuilder().concurrencyLevel(concurrencyLevel).expireAfterWrite(TTLTime, TimeUnit.MILLISECONDS).expireAfterAccess(TTITime, TimeUnit.MILLISECONDS).maximumSize(maximumSize).build(loader);

    }

    /**
     * 获取指定的计时器
     * @return
     */
    public static LoadingCache<String, AtomicInteger> getDefaultAtomicLoadingCache() {
        //maximumSize 缓存容量
        // expireAfterWrite 设置缓存的TTL 缓存数据给定的时间没有写 则被回收
        //expireAfterAccess tti    缓存数据给定的时间没有被读 则被回收
        //weakKeys weakValues 设置弱引用
        //sofrValue  设置 软引用
        //concurrencyLevel  并发级别
        // recordStats 启动记录统计信息
        return getAtomicLoadingCache(concurrencyLevel, TTLTime, TTITime, maximumSize);
    }

    /**
     * 获取指定的计时器
     * @return
     */
    public static Cache<Object, Object> getWeightLoadingCache() {
        //maximumSize 缓存容量
        // expireAfterWrite 设置缓存的TTL 缓存数据给定的时间没有写 则被回收
        //expireAfterAccess tti    缓存数据给定的时间没有被读 则被回收
        //weakKeys weakValues 设置弱引用
        //sofrValue  设置 软引用
        //concurrencyLevel  并发级别
        // recordStats 启动记录统计信息
        return CacheBuilder.newBuilder().concurrencyLevel(concurrencyLevel).expireAfterWrite(TTLTime, TimeUnit.MILLISECONDS).expireAfterAccess(TTITime, TimeUnit.MILLISECONDS).maximumSize(maximumSize).maximumWeight(maximumWeight).weigher(new Weigher<Object, Object>() {
            /* (non-Javadoc)
             * @see com.google.common.cache.Weigher#weigh(java.lang.Object, java.lang.Object)
             */
            @Override
            public int weigh(Object key, Object value) {
                // TODO Auto-generated method stub
                return 0;
            }
        }).build();
    }
}
