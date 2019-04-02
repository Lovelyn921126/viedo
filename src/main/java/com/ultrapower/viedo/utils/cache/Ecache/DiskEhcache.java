package com.ultrapower.viedo.utils.cache.Ecache;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.PooledExecutionServiceConfigurationBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.MemoryUnit;
import org.ehcache.expiry.Duration;
import org.ehcache.expiry.Expirations;
import org.ehcache.impl.config.persistence.CacheManagerPersistenceConfiguration;

public class DiskEhcache {
  
	public static Cache<String, String> name(String cacheName) {
        CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder().using(PooledExecutionServiceConfigurationBuilder.newPooledExecutionServiceConfigurationBuilder()
        		.defaultPool("default", 1, 10).build()).with(new CacheManagerPersistenceConfiguration(new File("E:\\bak"))).build(true);
        CacheConfigurationBuilder<String, String> cacheConfigurationBuilder=CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class,String.class,
        		ResourcePoolsBuilder.newResourcePoolsBuilder().disk(100, MemoryUnit.MB,true)
        		).withDiskStoreThreadPool("default", 5)
        		.withExpiry(Expirations.timeToIdleExpiration(Duration.of(10, TimeUnit.SECONDS)))
        		.withSizeOfMaxObjectGraph(3)
        		.withSizeOfMaxObjectSize(1, MemoryUnit.MB);
      return  cacheManager.createCache("cacheName", cacheConfigurationBuilder);
	}
}
