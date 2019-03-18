/*
 * FileName: ProvinceSynchroController.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ultrapower.viedo.bean.ProvinceSynchroResult;
import com.ultrapower.viedo.constants.Constants;
import com.ultrapower.viedo.service.ProvinceSynchroDemandService;
import com.ultrapower.viedo.task.ProvinceSynchroDemandTask;
import com.ultrapower.viedo.utils.GetProperty;
import com.ultrapower.viedo.utils.SftpUtils;
import com.ultrapower.viedo.vo.RepData;

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
 * 2018年7月3日 下午6:03:17          dell        4.1         To create
 * </p>
 *
 * @since
 * @see
 */
@Controller
@RequestMapping("/provinceSynchro/")
@Api(value = "倒三角省分同步数据")
public class ProvinceSynchroController {

    @Autowired
    private ProvinceSynchroDemandService provinceSynchroDemandService;
    @Autowired
    private ProvinceSynchroDemandTask provinceSynchroDemandTask;

    /**
     * 测试
     * @return
     */
    @RequestMapping("updateProvinceSynchroHandel.do")
    @ResponseBody
    public RepData updateProvinceSynchroHandel() {
        RepData<String> repData = new RepData<String>();
        provinceSynchroDemandService.updateProvinceSynchroHandel();
        return repData;

    }

    /**
     *  固话 某省的数据
     * @param provinceCode  省分编号（并非沃工单的省分ID 具体可以查询  provinceZBMappings 表）
     * @param createDate  格式为 yyyyMMdd
     * @return
     * @throws ParseException
     */
    @RequestMapping("getProvinceSynchroDemand.do")
    @ResponseBody
    public RepData getProvinceSynchroDemand(String provinceCode, String createDate) throws ParseException {
        RepData<String> repData = new RepData<String>();

        ProvinceSynchroResult result = provinceSynchroDemandService.getProvinceSynchroDemand(provinceCode, createDate);
        SftpUtils.writStringToFile(result, provinceCode);
        repData.setCode(Integer.valueOf(result.getCode()));
        repData.setMsg(result.getMsg());
        return repData;

    }

    /**
     * 固话所有的数据
     * @return
     * @throws ParseException
     */
    @RequestMapping("provinceSynchroTimedTask.do")
    @ResponseBody
    public RepData provinceSynchroTimedTask() throws ParseException {
        RepData<String> repData = new RepData<String>();

        provinceSynchroDemandTask.provinceSynchroTimedTask();
        return repData;

    }

    /**
     * 固话所有的数据
     * @return
     */
    @RequestMapping("readConfigAboutProvinceSynchro.do")
    @ResponseBody
    public RepData readConfigAboutProvinceSynchro() {
        RepData<String> repData = new RepData<String>();

        //省分同步全量数据FTP IP
        Constants.FtpIpAddr = GetProperty.getValue("config.properties", "FtpIpAddr");
        ////省分同步全量数据FTP 路径
        Constants.FtpPath = GetProperty.getValue("config.properties", "FtpPath");
        ////省分同步全量数据FTP 端口
        Constants.FtpPort = GetProperty.getValue("config.properties", "FtpPort");
        ////省分同步全量数据FTP 用户名
        Constants.FtpUserName = GetProperty.getValue("config.properties", "FtpUserName");
        ////省分同步全量数据FTP 密码
        Constants.FtpPwd = GetProperty.getValue("config.properties", "FtpPwd");
        //省分同步全量数据FTP 文件的后缀
        Constants.FtpFileSuffix = GetProperty.getValue("config.properties", "FtpFileSuffix");
        //省分同步全量数据FTP 文件读取完成之后 返回的文件后缀
        Constants.FtpCompleteFileSuffix = GetProperty.getValue("config.properties", "FtpCompleteFileSuffix");
        //省分同步全量数据FTP 读取到本地的临时路径
        Constants.FtpTempDir = GetProperty.getValue("config.properties", "FtpTempDir");
        System.out.println(Constants.FtpIpAddr);
        System.out.println(Constants.FtpPath);
        System.out.println(Constants.FtpPort);
        System.out.println(Constants.FtpUserName);
        System.out.println(Constants.FtpPwd);
        System.out.println(Constants.FtpFileSuffix);
        System.out.println(Constants.FtpCompleteFileSuffix);
        System.out.println(Constants.FtpTempDir);
        return repData;

    }

}
