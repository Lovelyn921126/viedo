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

import com.ultrapower.viedo.bean.IntfaceResultRelation;
import com.ultrapower.viedo.bean.ServerIntfaceRelation;
import com.ultrapower.viedo.service.IntfaceResultRelationService;
import com.ultrapower.viedo.service.ServerIntfaceRelationService;
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
@RequestMapping("/intfaceResultRelation/")
@Api(value = "接口返回值相关接口")
public class IntfaceResultRelationController {

    @Autowired
    private IntfaceResultRelationService intfaceResultRelationService;
    @Autowired
    private ServerIntfaceRelationService serverIntfaceRelationService;

    /**
     *  获取所有的 系統編碼
     * @param servcerCode 系統編碼
     * @param pageSize 分页大小
     * @param pageNum 页码
     * @return
     */
    @RequestMapping("getIntfaceResultRelation.do")
    @ResponseBody
    public R getIntfaceResultRelation(IntfaceResultRelation relation, Integer pageSize, Integer pageNum) {
        R r = new R();
        List<IntfaceResultRelation> list;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("date", null);
        map.put("count", 0);
        try {
            //获取相关记录

            list = intfaceResultRelationService.getIntfaceResultRelation(relation, pageSize, pageNum);
            if (list != null && list.size() > 0) {
                //获取相应记录的数目
                Integer count = intfaceResultRelationService.getIntfaceResultRelationCount(relation);
                map.put("date", list);
                map.put("count", count);
                return r.ok(map);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new CommonExpection("系統異常", e);
        }
        return r.ok(map);

    }

    /**
     * 添加服务编码
     * @param servcerCode 服务编码
     * @return
     */
    @RequestMapping("addIntfaceResultRelation.do")
    @ResponseBody
    public R addIntfaceResultRelation(IntfaceResultRelation relation) {
        R r = new R();
        Integer result = 0;
        if (relation == null) {
            return r.error("参数不全");
        }
        if (relation.getIntfaceId() == null && relation.getIntfaceId() < 0) {
            return r.error("接口Id 不合法");
        }

        if (StringUtils.isBlank(relation.getResultCode())) {
            return r.error("返回值 為空");
        }
        if (StringUtils.isBlank(relation.getResultDiscribe())) {
            return r.error("描述 為空");
        }

        try {
            //校验记录是否存在 校验 该记录是否已存在 目前之校验了 接口是否存在 后续会考虑是否校验 返回值的
            ServerIntfaceRelation serverIntfaceRelation = new ServerIntfaceRelation();
            serverIntfaceRelation.setId(relation.getIntfaceId());
            Integer count = serverIntfaceRelationService.getServerInfaceRelationCount(serverIntfaceRelation);
            if (count < 0) {
                return r.error("接口 不存在");
            }
            result = intfaceResultRelationService.addIntfaceResultRelation(relation);
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
    @RequestMapping("updateIntfaceResultRelation.do")
    @ResponseBody
    public R updateIntfaceResultRelation(IntfaceResultRelation relation) {
        R r = new R();
        //修改 目前只根据 主键ID 修改 所以主键ID 必
        if (relation == null) {
            return r.error("参数不全");
        }

        if (relation.getId() == null && relation.getId() <= 0) {
            return r.error("ID 不合法");
        }
        if (StringUtils.isBlank(relation.getResultCode()) && StringUtils.isNotBlank(relation.getResultDiscribe())) {
            return r.error("没有要修改的内容");
        }
        try {
            Integer result = intfaceResultRelationService.updateIntfaceResultRelation(relation);
            return r.ok(result.toString());

        } catch (Exception e) {
            e.printStackTrace();
            throw new CommonExpection("系統異常", e);

        }
    }

    /**
    * 删除 服务编码信息
    * @param servcerCode 服务编码
    * @return
    */
    @RequestMapping("deleteIntfaceResultRelation.do")
    @ResponseBody
    public R deleteIntfaceResultRelation(IntfaceResultRelation relation) {
        R r = new R();
        if (relation == null) {
            return r.error("参数不全");
        }
        if (relation.getId() == null && relation.getId() <= 0) {
            return r.error("ID 不合法");
        }
        try {
            Integer result = intfaceResultRelationService.deleteIntfaceResultRelation(relation);
            return r.ok(result.toString());
        } catch (Exception e) {
            e.printStackTrace();
            throw new CommonExpection("系統異常", e);
        }
    }

}
