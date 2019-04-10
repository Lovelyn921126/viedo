package com.ultrapower.viedo.utils.cache.MapDB;

import java.util.concurrent.TimeUnit;

import org.mapdb.DBMaker;
import org.mapdb.HTreeMap;

public class HeepMapDB {
    private static int concurrencyLevel = 4;
    private static long TTLTime = 5000;
    private static long TTITime = 5000;
    private static long maximumSize = 10000;

    public static HTreeMap getDefaultCache(String cacheName) {
        return getCache(concurrencyLevel, TTLTime, TTITime, maximumSize, cacheName);
    }

    public static HTreeMap getCache(int concurrencyLevel, long TTLTime, long TTITime, long maximumSize, String cacheName) {
        HTreeMap cache = DBMaker.heapDB().concurrencyScale(concurrencyLevel).make().hashMap(cacheName)//并发级别
                        .expireAfterCreate(TTLTime, TimeUnit.MICROSECONDS) //ttl  多久没有创建 就回收
                        .expireMaxSize(maximumSize) //缓存容量
                        .expireAfterGet(TTITime, TimeUnit.MICROSECONDS) // tti
                        .expireAfterUpdate(TTLTime, TimeUnit.MICROSECONDS).create(); //ttl 多久没有覆盖就回收
        return cache;
    }
}
