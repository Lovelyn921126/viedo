package com.ultrapower.viedo.utils.cache.gauvaCache;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

public class GuavaCache {

    public static Cache<Object, Object> getCache() {
        //maximumSize 缓存容量
        // expireAfterWrite 设置缓存的TTL 缓存数据给定的时间没有写 则被回收
        //expireAfterAccess tti    缓存数据给定的时间没有被读 则被回收
        //weakKeys weakValues 设置弱引用
        //sofrValue  设置 软引用
        //concurrencyLevel  并发级别  
        // recordStats 启动记录统计信息
        return CacheBuilder.newBuilder().concurrencyLevel(4).expireAfterWrite(4, TimeUnit.SECONDS).expireAfterAccess(100, TimeUnit.SECONDS).weakKeys().maximumSize(10000).recordStats().build();
    }

    public static LoadingCache<String, AtomicInteger> getLoadingCache() {
        //maximumSize 缓存容量
        // expireAfterWrite 设置缓存的TTL 缓存数据给定的时间没有写 则被回收
        //expireAfterAccess tti    缓存数据给定的时间没有被读 则被回收
        //weakKeys weakValues 设置弱引用
        //sofrValue  设置 软引用
        //concurrencyLevel  并发级别  
        // recordStats 启动记录统计信息
        return CacheBuilder.newBuilder().concurrencyLevel(4).expireAfterWrite(4, TimeUnit.SECONDS).expireAfterAccess(100, TimeUnit.SECONDS).weakKeys().maximumSize(10000).recordStats().build(new CacheLoader<String, AtomicInteger>() {
            @Override
            public AtomicInteger load(String key) throws Exception {
                return new AtomicInteger(0);

            };
        });
    }
}
