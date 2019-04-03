package com.ultrapower.viedo.utils.cache.MapDB;

import java.util.concurrent.TimeUnit;

import org.mapdb.DBMaker;
import org.mapdb.HTreeMap;

public class HeepMapDB {

    public static HTreeMap name() {
        HTreeMap cache = DBMaker.heapDB().concurrencyScale(16).make().hashMap("mycache")//并发级别
                        .expireAfterCreate(10, TimeUnit.SECONDS) //ttl  多久没有创建 就回收
                        .expireMaxSize(10000) //缓存容量
                        .expireAfterGet(10, TimeUnit.SECONDS) // tti
                        .expireAfterUpdate(10, TimeUnit.SECONDS).create(); //ttl 多久没有覆盖就回收
        return cache;
    }
}
