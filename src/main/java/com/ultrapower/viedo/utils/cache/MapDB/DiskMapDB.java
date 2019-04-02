package com.ultrapower.viedo.utils.cache.MapDB;

import static org.hamcrest.CoreMatchers.containsString;

import java.util.concurrent.TimeUnit;

import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.HTreeMap;

public class DiskMapDB {
  public static void name(String cacheName) {
	DB cache=DBMaker.fileDB("E:\\bak\\a.data").fileMmapEnable()//启动MAP
			.fileMmapEnableIfSupported()//在支持的平台上启用MMAp
			.fileMmapPreclearDisable()//让mmap 处理 的更快
			.cleanerHackEnable()//一下BUG的处理
			.transactionEnable()//事务
			.concurrencyScale(16).make();
HTreeMap mycache=cache.hashMap(cacheName)
.expireAfterCreate(10,TimeUnit.SECONDS)  //ttl  多久没有创建 就回收
.expireMaxSize(10000)  //缓存容量
.expireAfterGet(10, TimeUnit.SECONDS) // tti
.expireAfterUpdate(10,TimeUnit.SECONDS).create(); //ttl 多久没有覆盖就回收
//  mycache。put()  db.commit();

}
}
