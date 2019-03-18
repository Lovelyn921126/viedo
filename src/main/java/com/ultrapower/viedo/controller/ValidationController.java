/*
 * FileName: TestController.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.controller;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ultrapower.viedo.service.UserInfoService;
import com.ultrapower.viedo.vo.RepData;
import com.ultrapower.viedo.vo.UserInfoCacheBean;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

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
 * 2018年1月2日 下午2:16:24          dell        4.1         To create
 * </p>
 *
 * @since
 * @see
 */
@Controller

@Api(value = "/test/", description = "测试 用户EChache缓存", position = 1, produces = "application/json", consumes = "application/text")
@RequestMapping("/test/")
class ValidationController {

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping("/getCounpon.do")
    public void get() {
        try {
            userInfoService.get2(0);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            ;
        }

    }

    @RequestMapping("update.do")
    @ResponseBody
    @Validated
    public @NotNull UserInfoCacheBean update() {
        return userInfoService.get2(0);
    }

    @RequestMapping("getUserInfo.do")
    @ResponseBody
    @ApiOperation(value = "getUserInfo.do", position = 2, httpMethod = "GET", response = UserInfoCacheBean.class, responseContainer = "List")
    @ApiImplicitParams({ @ApiImplicitParam(value = "用户ID", name = "id", paramType = "query", dataType = "int", required = true) })
    public String getUserInfo(UserInfoCacheBean cacheBean) {
        RepData<UserInfoCacheBean> repData = new RepData<UserInfoCacheBean>();
        repData.setCode(200);
        List<UserInfoCacheBean> infoCacheBean = userInfoService.getUserInfoById("5598978");
        //UserInfoCacheBean infoCacheBean1 = userInfoService.getUserInfoById(id);
        //UserInfoService personService = (UserInfoService) cxt.getBean("userInfoService");
        // userInfoService.getUserNameByUserId("5598978");
        //boolean flag = (infoCacheBean == infoCacheBean1);

        return userInfoService.getUserNameByUserId("5598978");

    }

    @RequestMapping("updateUserInfo.do")
    @ResponseBody
    @ApiOperation(value = "updateUserInfo.do", position = 1, httpMethod = "GET", produces = "application/xml")
    @ApiResponses({ @ApiResponse(code = 200, message = "SUCCESS", response = UserInfoCacheBean.class), @ApiResponse(code = 404, message = "找不到页面"), @ApiResponse(code = 404, message = "系统错误") })
    public void updateUserInfo(@ApiParam(value = "id", name = "用户ID", allowableValues = "range[0,max]", required = true) String id, UserInfoCacheBean cacheBean) {
        userInfoService.updateUserInfo(id);

    }
}
