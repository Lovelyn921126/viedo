package com.ultrapower.viedo.utils.cache.MapDB;

import java.util.concurrent.TimeUnit;

import org.mapdb.DBMaker;
import org.mapdb.HTreeMap;

public class OffHeepMapDB {

    public static HTreeMap name(String cacheName) {
        HTreeMap cache = DBMaker.memoryDB().concurrencyScale(16).make().hashMap(cacheName).expireStoreSize(64 * 1000 * 1000) //堆外缓存大学
                        .expireAfterCreate(10, TimeUnit.SECONDS) //ttl  多久没有创建 就回收
                        .expireMaxSize(10000) //缓存容量
                        .expireAfterGet(10, TimeUnit.SECONDS) // tti
                        .expireAfterUpdate(10, TimeUnit.SECONDS).create(); //ttl 多久没有覆盖就回收
        return cache;
    }
}
