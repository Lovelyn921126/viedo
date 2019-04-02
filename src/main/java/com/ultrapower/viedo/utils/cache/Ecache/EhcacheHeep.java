/*
 * FileName: EhcacheHeep.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.utils.cache.Ecache;

import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;

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

    public static void name(String cacheName) {
        System.out.println("[Ehcache配置初始化<开始>]");

        CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder().withCache("preConfigured", CacheConfigurationBuilder.newCacheConfigurationBuilder(Long.class, String.class, ResourcePoolsBuilder.heap(10))).build();

    }
}
