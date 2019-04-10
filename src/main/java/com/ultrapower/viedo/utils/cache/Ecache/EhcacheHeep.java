/*
= * FileName: EhcacheHeep.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.utils.cache.Ecache;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.EntryUnit;
import org.ehcache.expiry.Duration;
import org.ehcache.expiry.Expirations;
import org.ehcache.spi.loaderwriter.BulkCacheLoadingException;
import org.ehcache.spi.loaderwriter.BulkCacheWritingException;
import org.ehcache.spi.loaderwriter.CacheLoaderWriter;

/**
 * <p>
 * Description:
 * </p>
 *
 * @author dell
 * @version 4.1

 * <p>
 * History:
 *
 * Date                     Author         Version     Description
 * ---------------------------------------------------------------------------------
 * 2019年4月2日 下午6:20:02          dell        4.1         To create
 * </p>
 *
 * @since
 * @see
 */
public class EhcacheHeep {

    public static Cache<String, String> name(String cacheName) {
        System.out.println("[Ehcache配置初始化<开始>]");

        //EntryUnit.ENTRIES  缓存的条目数
        //MemoryUnit.MB 缓存的容量
        //withExpiry(Expirations.timeToLiveExpiration(Duration.of(10, TimeUnit.SECONDS))  设置TTL 即 缓存的写更新
        //timeToIdleExpiration    设置TTL,tti 即 缓存的写更新 跟读跟新
        CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build();
        CacheConfigurationBuilder<String, String> cacheConfig = CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, String.class, ResourcePoolsBuilder.newResourcePoolsBuilder().heap(100, EntryUnit.ENTRIES)).withDispatcherConcurrency(4).withExpiry(Expirations.timeToIdleExpiration(Duration.of(10, TimeUnit.SECONDS))).withLoaderWriter(new CacheLoaderWriter<String, String>() {

            @Override
            public void writeAll(Iterable<? extends Entry<? extends String, ? extends String>> arg0) throws BulkCacheWritingException, Exception {
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
        });
        return cacheManager.createCache(cacheName, cacheConfig);
    }
}
