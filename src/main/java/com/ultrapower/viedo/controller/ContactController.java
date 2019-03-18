/*
 * FileName: ContactController.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ultrapower.viedo.service.UserInfoService;
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
 * 2018年4月26日 下午7:53:02          dell        4.1         To create
 * </p>
 *
 * @since
 * @see
 */
@Api(value = "contacts-api")
@Controller
@RequestMapping("/contacts")
public class ContactController {
    @Autowired
    UserInfoService contactService;

    @ResponseBody
    @ApiOperation(value = "创建用户", notes = "返回用户实体对象", response = UserInfoCacheBean.class, position = 2)
    @RequestMapping(value = "/1.0/contact/get.do/{id}", method = RequestMethod.GET)
    public List<UserInfoCacheBean> get(@PathVariable String id) {
        return contactService.getUserInfoById(id);
    }

    @RequestMapping(value = "/1.0/contact/update.do/{id}", method = RequestMethod.POST)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "更新成功", response = UserInfoCacheBean.class), @ApiResponse(code = 404, message = "找不到页面"), @ApiResponse(code = 500, message = "内部报错") })
    public void update(@ApiParam(name = "id", value = "编号", required = true) @PathVariable Integer id, @RequestBody UserInfoCacheBean contact) {
        contact.setId(id);
        //contactService.update(contact);
    }
}