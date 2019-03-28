/*
 * FileName: LoaderCache.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.utils.cache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicLong;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.ultrapower.viedo.bean.UserInfo;

import lombok.extern.slf4j.Slf4j;

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
 * 2019年3月28日 下午8:48:07          dell        4.1         To create
 * </p>
 *
 * @since
 * @see
 */
@Slf4j
public class LoadingCacheUtils {
    public static CacheLoader<Long, AtomicLong> createCacheLoader() {
        return new CacheLoader<Long, AtomicLong>() {

            @Override
            public AtomicLong load(Long key) throws Exception {
                log.info("加载创建key:" + key);
                return new AtomicLong(0);
            }
        };
    }

    public static void main(String[] args) throws ExecutionException {
        /* Cache<Integer, String> cache = CacheBuilder.newBuilder().build();
        cache.put(1, "a");
        System.out.println(cache.getIfPresent(1));
        System.out.println(cache.getIfPresent(2));*/
        LoadingCache<Integer, UserInfo> cache = CacheBuilder.newBuilder().build(new CacheLoader<Integer, UserInfo>() {
            @Override
            public UserInfo load(Integer key) throws Exception {
                return new UserInfo();
            }
        });
        cache.put(1, new UserInfo());
        cache.get(1).setIsNewUser(1);
        System.out.println(cache.get(1).getIsNewUser());
        System.out.println(cache.getIfPresent(1));
        /* try {
            System.out.println(cache.get(2, () -> {
                return "hellow world";
            }));
        } catch (ExecutionException e) {
            e.printStackTrace();
        }*/
        /* int limit = 10;
        LoadingCache<Long, AtomicLong> cache = CacheBuilder.newBuilder().expireAfterAccess(2, TimeUnit.MILLISECONDS).build(createCacheLoader());
        long current = System.currentTimeMillis() / 1000;
        while (true) {
            System.out.println("cache----" + cache.get(current));
            cache.get(current).incrementAndGet();
            if (cache.get(current).incrementAndGet() > limit) {

                System.out.println("限流了");
            }
        }*/
    }

    public static void getLoadingCache() {

    }
}
