package com.ultrapower.viedo.utils.cache.MapDB;

import java.util.concurrent.TimeUnit;

import org.mapdb.DBMaker;
import org.mapdb.HTreeMap;

public class OffHeepMapDB {
    private static int concurrencyLevel = 4;
    private static long TTLTime = 5000;
    private static long TTITime = 5000;
    private static long maximumSize = 10000;
    private static long storeSize = 64 * 1000 * 1000;

    public static HTreeMap getCache(int concurrencyLevel, long TTLTime, long TTITime, long maximumSize, long storeSize, String cacheName) {
        HTreeMap cache = DBMaker.memoryDB().concurrencyScale(concurrencyLevel).make().hashMap(cacheName).expireStoreSize(storeSize) //堆外缓存大学
                        .expireAfterCreate(TTLTime, TimeUnit.MILLISECONDS) //ttl  多久没有创建 就回收
                        .expireMaxSize(maximumSize) //缓存容量
                        .expireAfterGet(TTITime, TimeUnit.MILLISECONDS) // tti
                        .expireAfterUpdate(TTLTime, TimeUnit.MILLISECONDS).create(); //ttl 多久没有覆盖就回收
        return cache;
    }

    public static HTreeMap getDefaultCache(String cacheName) {
        HTreeMap cache = DBMaker.memoryDB().concurrencyScale(concurrencyLevel).make().hashMap(cacheName).expireStoreSize(storeSize) //堆外缓存大学
                        .expireAfterCreate(TTLTime, TimeUnit.MILLISECONDS) //ttl  多久没有创建 就回收
                        .expireMaxSize(maximumSize) //缓存容量
                        .expireAfterGet(TTITime, TimeUnit.MILLISECONDS) // tti
                        .expireAfterUpdate(TTLTime, TimeUnit.MILLISECONDS).create(); //ttl 多久没有覆盖就回收
        return cache;
    }
}
