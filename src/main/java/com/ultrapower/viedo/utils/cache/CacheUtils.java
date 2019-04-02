package com.ultrapower.viedo.utils.cache;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.Cache;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ultrapower.viedo.utils.cache.gauvaCache.GuavaCache;

public class CacheUtils {
  public static void set(String key,Object value) {
	if (value==null) {
		return;
	}
	//复制对象 
	//本地缓存为引用 分布式需要序列化
	//如果不复制的话   则更新数据后 会造成 本地缓存与分布式不一致
	//复制
	
	//写本地缓存 --标识符
	if (true) {
		//获取缓存
		
		//存放缓存
	}
	//同步分布式缓存
	
	//  异步写 分布式缓存
}
  public static Map get(List<String> keys,List<Class> values) {
	Map<String, Object> result=Maps.newHashMap();
	List<String> missKeys=Lists.newArrayList();
	List<Class> missValues=Lists.newArrayList();
	if (true) {
		for (int i = 0; i < keys.size(); i++) {
			String key=keys.get(i);
			Class type=values.get(i);
			//获取本地缓存
			Cache cache=GuavaCache.getCache();
			if (cache!=null) {
		    Object value=		cache.getIfPresent(key);
		    result.put(key, value);
		    if (value==null) {
				missKeys.add(key);
				missValues.add(type);
			 }
			}else {
				missKeys.add(key);
				missValues.add(type);
			}
			
		}
	}
	
	//没有配置远程缓存 返回
	if (true) {
		return result;
	}
	Map<String, Object> missReuslt =Maps.newHashMap();
	//对key 进行分区  不能一次批量 调用太大
     final	List<List<String>> keyPage=	Lists.partition(missKeys, 10);
     List<Future<Map<String, Object>>> pageFuture=  Lists.newArrayList();
     try {
		for (List<String> future : keyPage) {
			//批量调用 远程缓存
		}
		for (Future<Map<String, Object>> future : pageFuture) {
		missReuslt.putAll(future.get(3000, TimeUnit.MICROSECONDS));	
		}
	} catch (Exception e) {
		pageFuture.forEach(future->future.cancel(true));
	}
     return result;
       }
}
