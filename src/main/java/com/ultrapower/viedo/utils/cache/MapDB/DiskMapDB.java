package com.ultrapower.viedo.utils.cache.MapDB;

import java.util.concurrent.TimeUnit;

import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.HTreeMap;

public class DiskMapDB {
    private static int concurrencyLevel = 4;
    private static long TTLTime = 5000;
    private static long TTITime = 5000;
    private static long maximumSize = 10000;

    public static HTreeMap getCache(int concurrencyLevel, long TTLTime, long TTITime, long maximumSize, String cacheName, String fileName) {
        DB cache = DBMaker.fileDB(fileName).fileMmapEnable()//启动MAP
                        .fileMmapEnableIfSupported()//在支持的平台上启用MMAp
                        .fileMmapPreclearDisable()//让mmap 处理 的更快
                        .cleanerHackEnable()//一下BUG的处理
                        .transactionEnable()//事务
                        .concurrencyScale(16).make();
        HTreeMap mycache = cache.hashMap(cacheName).expireAfterCreate(TTLTime, TimeUnit.SECONDS) //ttl  多久没有创建 就回收
                        .expireMaxSize(maximumSize) //缓存容量
                        .expireAfterGet(TTITime, TimeUnit.SECONDS) // tti
                        .expireAfterUpdate(TTLTime, TimeUnit.SECONDS).create(); //ttl 多久没有覆盖就回收
        //  mycache。put()  db.commit();
        return mycache;

    }

    public static HTreeMap getDefaultCache(String cacheName, String fileName) {
        return getCache(concurrencyLevel, TTLTime, TTITime, maximumSize, cacheName, fileName);

    }
}
