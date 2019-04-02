/*
 * FileName: ContactController.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ultrapower.viedo.service.AxService;
import com.ultrapower.viedo.service.UserInfoService;
import com.ultrapower.viedo.utils.ThreadUtils.OneLevelAsynContent;

import io.swagger.annotations.Api;

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
@Api(value = "AsyncController-api")
@RestController
@RequestMapping("/AsyncController")
public class AsyncController {
    @Autowired
    OneLevelAsynContent oneLevelAsynContent;
    @Autowired
    UserInfoService userInfoService;
    @Autowired
    AxService axService;

    /* @RequestMapping("/getBook.do")
    public void getBook(HttpServletRequest request, @RequestParam("Id") final Integer Id) {
        oneLevelAsynContent.submitFuture(request, () -> userInfoService.get2(Id));
    }*/

    @GetMapping("/addStudent.do")
    public String name() {
        axService.name();
        return "success";
    }
}