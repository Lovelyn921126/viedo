/*
 * FileName: UserInfoDaoMapper.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.service;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.ultrapower.viedo.bean.UserInfo;
import com.ultrapower.viedo.dao.one.UserInfoDaoMapper;
import com.ultrapower.viedo.utils.CLogger;
import com.ultrapower.viedo.utils.CommonExpection;
import com.ultrapower.viedo.vo.UserInfoCacheBean;
import com.ultrapower.viedo.vo.UserParentOrganization;

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
 * 2017年12月29日 下午5:13:19          dell        4.1         To create
 * </p>
 *
 * @since
 * @see
 */
@Service
@Validated
public class UserInfoService {
    private Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private UserInfoDaoMapper userInfoDaoMapper;

    public @NotNull UserInfoCacheBean get2(@NotNull @Min(value = 1) Integer uuid) {
        UserInfoCacheBean user = new UserInfoCacheBean();
        if (uuid > 100) {
            return null;
        }
        return user;

    }

    @Cacheable(value = { "userinfo" }, key = "#id")
    public List<UserInfoCacheBean> getUserInfoById(String id) {
        List<UserInfoCacheBean> bean = null;
        try {
            System.out.println();
            bean = userInfoDaoMapper.getUserInfoById(id);
            System.out.println("aa");
            // int a = 1 / 0;
        } catch (Exception e) {
            CLogger.error(id, "发生错误", e);
        }
        return bean;
    };

    public String getUserNameByUserId(String id) {
        return userInfoDaoMapper.getUserNameByUserId(id);
    };

    /**通过用户名获取用户信息
     * @param userName
     *
     * @return
     */
    public UserInfo getUserByUserName(String userName) {
        return userInfoDaoMapper.getUserByUserName(userName);
    }

    /**通过用户ID 获取用户省分信息
     * @param id
     * @return
     */
    public UserParentOrganization getParentOrganization(Integer id) {
        return userInfoDaoMapper.getParentOrganization(id);
    }

    //每次都会执行该方法，并将执行结果以键值对的形式存入指定的缓存中
    @CachePut(value = { "userinfo" }, key = "id")
    public void updateUserInfo(String id) {
        userInfoDaoMapper.updateUserInfo();
        log.info("发生了异常 info");
        log.error("发生了异常 error");
        CLogger.error(id, "fashengyichang");
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        /*   executorService.execute(new Runnable() {
            @Override
            public void run() {
                throw new CommonExpection("发生了异常");
            }
        });*/
        throw new CommonExpection("发生了异常");

    }

    //beforeInvocation 清除操作默认是在对应方法成功执行之后触发的，
    //即方法如果因为抛出异常而未能成功返回时也不会触发清除操作。使用beforeInvocation可以改变触发清除操作的时间，
    //当我们指定该属性值为true时，Spring会在调用该方法之前清除缓存中的指定元素。
    @CacheEvict(value = { "userInfo" }, key = "id", allEntries = false, beforeInvocation = false)
    public void updateUserInfo1(String id) {
        userInfoDaoMapper.updateUserInfo();
        throw new RuntimeException("发生了异常");

    }

    @Caching(cacheable = @Cacheable("userInfo"), evict = @CacheEvict("userInfo"), put = @CachePut("userInfo"))
    public void testAdd() {

    }

}
