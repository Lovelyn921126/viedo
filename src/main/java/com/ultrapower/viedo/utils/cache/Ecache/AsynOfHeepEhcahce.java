package com.ultrapower.viedo.utils.cache.Ecache;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.PooledExecutionServiceConfigurationBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.builders.WriteBehindConfigurationBuilder;
import org.ehcache.config.units.MemoryUnit;
import org.ehcache.expiry.Duration;
import org.ehcache.expiry.Expirations;
import org.ehcache.spi.loaderwriter.BulkCacheLoadingException;
import org.ehcache.spi.loaderwriter.BulkCacheWritingException;
import org.ehcache.spi.loaderwriter.CacheLoaderWriter;



public class AsynOfHeepEhcahce {
    //ehcahce 堆外缓存
	
	public static Cache<String, String> name(String cacheName) {
		CacheManager  cacheManager=CacheManagerBuilder.newCacheManagerBuilder().using(PooledExecutionServiceConfigurationBuilder.newPooledExecutionServiceConfigurationBuilder()
				.pool("writeBehindPool", 1, 5).build()).build(true);
		CacheConfigurationBuilder<String,String> cacheConfigurationBuilder=CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class,String.class,ResourcePoolsBuilder.newResourcePoolsBuilder().offheap(100, MemoryUnit.MB)
				).withExpiry(Expirations.timeToIdleExpiration(Duration.of(10, TimeUnit.SECONDS))).withSizeOfMaxObjectGraph(3).withSizeOfMaxObjectSize(1, MemoryUnit.MB)
				.withLoaderWriter(new CacheLoaderWriter<String, String>(
						) {
							
							@Override
							public void writeAll(Iterable<? extends Entry<? extends String, ? extends String>> arg0)
									throws BulkCacheWritingException, Exception {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void write(String arg0, String arg1) throws Exception {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public Map<String, String> loadAll(Iterable<? extends String> arg0) throws BulkCacheLoadingException, Exception {
								// TODO Auto-generated method stub
								return null;
							}
							
							@Override
							public String load(String arg0) throws Exception {
								// TODO Auto-generated method stub
								return null;
							}
							
							@Override
							public void deleteAll(Iterable<? extends String> arg0) throws BulkCacheWritingException, Exception {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void delete(String arg0) throws Exception {
								// TODO Auto-generated method stub
								
							}
						}).add(WriteBehindConfigurationBuilder.newUnBatchedWriteBehindConfiguration().queueSize(5).concurrencyLevel(2).useThreadPool("writeBehindPool").build());
						
	return	cacheManager.createCache(cacheName, cacheConfigurationBuilder);
	}
}
