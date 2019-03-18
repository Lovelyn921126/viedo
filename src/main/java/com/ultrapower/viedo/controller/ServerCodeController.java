/*
 * FileName: ServerCode.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ultrapower.viedo.bean.ServcerCode;
import com.ultrapower.viedo.service.ServerCodeService;
import com.ultrapower.viedo.utils.CommonExpection;
import com.ultrapower.viedo.utils.web.R;

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
 * 2018年6月13日 下午8:29:05          dell        4.1         To create
 * </p>
 *
 * @since
 * @see
 */
@Controller
@RequestMapping("/serverCode/")
@Api(value = "服务编码")
public class ServerCodeController {

    @Autowired
    private ServerCodeService serverCodeService;

    /**
     *  获取所有的 系統編碼
     * @param pageSize 分页大小
     * @param pageNum 页码
     * @return
     */
    @RequestMapping("getServerCode.do")
    @ResponseBody
    public R getServerCode(Integer pageSize, Integer pageNum) {
        R r = new R();
        List<ServcerCode> list;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("date", null);
        map.put("count", 0);
        try {
            //获取相关记录
            ServcerCode servcerCode = new ServcerCode();
            list = serverCodeService.getServerCode(servcerCode, pageSize, pageNum);
            if (list != null && list.size() > 0) {
                //获取相应记录的数目
                Integer count = serverCodeService.getServerCodeCount(servcerCode);
                map.put("date", list);
                map.put("count", count);
                return r.ok(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new CommonExpection("系統異常", e);
            //return r.error(e.getMessage());
        }
        return r.ok(map);

    }

    /**
     * 添加服务编码
     * @param servcerCode 服务编码
     * @return
     */
    @RequestMapping("addServerCode.do")
    @ResponseBody
    public R addServerCode(ServcerCode servcerCode) {
        R r = new R();
        Integer result = 0;
        try {
            if (servcerCode == null) {
                return r.error("参数不全");
            }
            if (StringUtils.isBlank(servcerCode.getSystemCode())) {
                return r.error("服务编码 為空");
            }
            if (StringUtils.isBlank(servcerCode.getSystemName())) {
                return r.error("服务名称 為空");
            }
            //设置 系统名称编码 合成 用于下拉框展示 类似于 CRM系统（10.1001）
            String systemNameCode = servcerCode.getSystemName() + "(" + servcerCode.getSystemCode() + ")";
            servcerCode.setSystemNameCode(systemNameCode);
            //验证服务编码 是否已存在  后续会改进
            ServcerCode Code = new ServcerCode();
            Code.setSystemCode(servcerCode.getSystemCode());
            Integer code = serverCodeService.getServerCodeCount(Code);
            if (code > 0) {
                return r.error("服務编码已存在");
            }
            //验证服务名称 是否已存在  后续会改进
            ServcerCode name = new ServcerCode();
            name.setSystemName(servcerCode.getSystemName());
            Integer Name = serverCodeService.getServerCodeCount(name);
            if (Name > 0) {
                return r.error("服务名称已存在");
            }
            result = serverCodeService.addServerCode(servcerCode);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CommonExpection("系統異常", e);
            //  return r.error(e.getMessage());
        }
        return r.ok(String.valueOf(result));
    }

    /**
     * 修改服务编码
     * @param servcerCode 服务编码
     * @return
     */
    @RequestMapping("updateServerCode.do")
    @ResponseBody
    public R updateServerCode(ServcerCode servcerCode) {
        R r = new R();
        try {
            if (servcerCode == null) {
                return r.error("参数不全");
            }
            //验证ID 因为修改是根据ID 修改的所有ID 必传
            if (servcerCode.getId() == null && servcerCode.getId() <= 0) {
                return r.error("ID 不合法");
            }
            if (StringUtils.isBlank(servcerCode.getSystemCode()) && StringUtils.isNotBlank(servcerCode.getSystemName())) {
                return r.error("没有要修改的内容");
            }
            //设置系统名称编码 合成 用于下拉框展示 类似于 CRM系统（10.1001）
            String systemNameCode = servcerCode.getSystemName() + "(" + servcerCode.getSystemCode() + ")";
            servcerCode.setSystemNameCode(systemNameCode);
            //验证要修改的 服务编码是否已存在
            ServcerCode Code = new ServcerCode();
            Code.setSystemCode(servcerCode.getSystemCode());
            Integer code = serverCodeService.getServerCodeCount(Code);
            if (code > 0) {
                return r.error("服務编码已存在");
            }
            //验证要修改的服务名称是否已存在
            ServcerCode name = new ServcerCode();
            name.setSystemName(servcerCode.getSystemName());
            Integer Name = serverCodeService.getServerCodeCount(name);
            if (Name > 0) {
                return r.error("服务名称已存在");
            }
            Integer result = serverCodeService.updateServerCode(servcerCode);
            return r.ok(result.toString());
        } catch (Exception e) {
            e.printStackTrace();
            throw new CommonExpection("系統異常", e);
            // return r.error(e.getMessage());
        }
    }

    /**
    * 删除 服务编码信息
    * @param servcerCode 服务编码
    * @return
    */
    @RequestMapping("deleteServerCode.do")
    @ResponseBody
    public R deleteServerCode(ServcerCode servcerCode) {
        R r = new R();
        if (servcerCode == null) {
            return r.error("参数不全");
        }
        if (servcerCode.getId() == null && servcerCode.getId() <= 0) {
            return r.error("ID 不合法");
        }
        try {
            Integer result = serverCodeService.deleteServerCode(servcerCode);
            return r.ok(result.toString());
        } catch (Exception e) {
            e.printStackTrace();
            //return r.error(e.getMessage());
            throw new CommonExpection("系統異常", e);
        }
    }

}
