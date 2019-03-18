/*
* FileName: UserController.java
*
* Company: 北京神州泰岳软件股份有限公司
* Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
*/
package com.ultrapower.viedo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.ultrapower.viedo.bean.ProvinceTest;
import com.ultrapower.viedo.service.UserInfoService;
import com.ultrapower.viedo.vo.RepData;
import com.ultrapower.viedo.vo.UserInfoCacheBean;

import io.swagger.annotations.Api;
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
 * 2018年4月26日 下午9:46:05          dell        4.1         To create
 * </p>
 *
 * @since
 * @see
 */
@Controller
@RequestMapping("/userController")
@Api(tags = "二：用户信息", position = 2, produces = "application/json", consumes = "application/text") //swagger分类标题注解
public class UserController {

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping("/save.do")
    @ResponseBody
    public String save(@Valid ProvinceTest user, BindingResult result) {
        if (result.hasErrors()) {
            return "error";
        }
        return "success";
    }

    @RequestMapping(value = "/listCompound", method = RequestMethod.GET)
    @ResponseBody
    @ApiResponses(value = { @ApiResponse(code = 500, message = "系统错误"), @ApiResponse(code = 200, message = "0 成功,其它为错误,返回格式：{code:0,data[{}]},data中的属性参照下方Model", response = UserInfoCacheBean.class) })
    @ApiOperation(httpMethod = "GET", value = "个人信息") //swagger 当前接口注解
    public String listCompound(@ApiParam(required = true, name = "start", value = "start") int start, int limit, @ApiParam(required = false, name = "userName", value = "名称模糊查询") String userName) {
        List<UserInfoCacheBean> data = new ArrayList<UserInfoCacheBean>();
        data = userInfoService.getUserInfoById("5598978");
        String msg = data.size() > 0 ? "" : "没有查询到相关记录";
        RepData result = new RepData();
        result.setMsg(msg);
        result.setCode(0);
        result.setData(data);
        return JSONObject.toJSONString(result);
    }
}