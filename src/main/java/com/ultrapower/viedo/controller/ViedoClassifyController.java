/*
 * FileName: ViedoClassifyController.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ultrapower.viedo.bean.ProvinceTest;
import com.ultrapower.viedo.bean.ViedoClassify;
import com.ultrapower.viedo.service.ViedoClassifyService;
import com.ultrapower.viedo.vo.RepData;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

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
 * 2017年12月6日 下午1:36:41          dell        4.1         To create
 * </p>
 *
 * @since
 * @see
 */
@Controller
@RequestMapping("/viedoClassify/")
@Api(tags = { "Viedo" }, description = "关于视频分类接口", value = "/viedoClassify/")
public class ViedoClassifyController {

    @Autowired
    private ViedoClassifyService viedoClassifyService;

    @RequestMapping("getAllViedoClassify.do")
    @ResponseBody
    @ApiOperation(httpMethod = "GET", value = "Find purchase order by ID", consumes = "application/x-www-form-urlencoded", notes = "H5上传页面", tags = { "Pet Store" })
    public RepData<List<ViedoClassify>> getAllViedoClassify(@Validated ProvinceTest test) {
        Map<String, Object> map = new HashMap<String, Object>();
        System.out.println("test");
        return viedoClassifyService.getViedoClassify(map);
    }

}
