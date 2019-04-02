package com.ultrapower.viedo.utils.cache.Ecache;

import java.util.concurrent.TimeUnit;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.MemoryUnit;
import org.ehcache.expiry.Duration;
import org.ehcache.expiry.Expirations;



public class OfHeepEhcahce {
    //ehcahce 堆外缓存
	
	public static Cache<String, String> name(String cacheName) {
		CacheManager  cacheManager=CacheManagerBuilder.newCacheManagerBuilder().build();
		CacheConfigurationBuilder<String,String> cacheConfigurationBuilder=CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class,String.class,ResourcePoolsBuilder.newResourcePoolsBuilder().offheap(100, MemoryUnit.MB)
				).withExpiry(Expirations.timeToIdleExpiration(Duration.of(10, TimeUnit.SECONDS))).withSizeOfMaxObjectGraph(3).withSizeOfMaxObjectSize(1, MemoryUnit.MB);
	return	cacheManager.createCache(cacheName, cacheConfigurationBuilder);
	}
}
