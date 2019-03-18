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
import com.ultrapower.viedo.bean.ServerIntfaceRelation;
import com.ultrapower.viedo.service.ServerCodeService;
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
@RequestMapping("/serverIntfaceRelation/")
@Api(value = "服务编码与接口的关系")
public class ServerIntfaceRelationController {

    @Autowired
    private ServerIntfaceRelationService serverIntfaceRelationService;
    @Autowired
    private ServerCodeService serverCodeService;

    /**
     *  获取获取 相应条件的 系統編碼
     * @param servcerCode 系統編碼
     * @param pageSize 分页大小
     * @param pageNum 页码
     * @return
     */
    @RequestMapping("getServerInfaceRelation.do")
    @ResponseBody
    public R getServerInfaceRelation(Integer pageSize, Integer pageNum) {
        R r = new R();
        List<ServerIntfaceRelation> list;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("date", null);
        map.put("count", 0);
        try {
            //获取相关记录
            ServerIntfaceRelation relation = new ServerIntfaceRelation();
            list = serverIntfaceRelationService.getServerInfaceRelation(relation, pageSize, pageNum);
            if (list != null && list.size() > 0) {
                //获取相应记录的数目
                Integer count = serverIntfaceRelationService.getServerInfaceRelationCount(relation);

                map.put("date", list);
                map.put("count", count);
                // return r.ok(map);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new CommonExpection("系統異常", e);
            //return r.error(e.getMessage());
        }
        return r.ok(map);

    }

    /**
    * 根据 服务号 获取起 调用客户端的接口信息
    * @param systemId
    * @param pageSize
    * @param pageNum
    * @return
    */
    @RequestMapping("getServerInfaceRelationByServerId.do")
    @ResponseBody
    public R getServerInfaceRelationByServerId(Integer systemId) {
        R r = new R();
        List<ServerIntfaceRelation> list;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("date", null);
        map.put("count", 0);
        try {
            //获取相关记录
            ServerIntfaceRelation relation = new ServerIntfaceRelation();
            relation.setSystemId(systemId);
            list = serverIntfaceRelationService.getServerInfaceRelation(relation, 0, 0);

            map.put("date", list);

        } catch (Exception e) {
            e.printStackTrace();
            throw new CommonExpection("系統異常", e);
            //return r.error(e.getMessage());
        }
        return r.ok(map);

    }

    /**
     * 根据 客戶端ID 获取起 调用他的的接口信息
     * @param systemId
     * @param pageSize
     * @param pageNum
     * @return
     */
    @RequestMapping("getServerInfaceRelationBySenderId.do")
    @ResponseBody
    public R getServerInfaceRelationBySenderId(Integer senderId) {
        R r = new R();
        List<ServerIntfaceRelation> list;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("date", null);
        map.put("count", 0);
        try {
            //获取相关记录
            ServerIntfaceRelation relation = new ServerIntfaceRelation();
            relation.setSenderId(senderId);
            list = serverIntfaceRelationService.getServerInfaceRelation(relation, 0, 0);
            map.put("date", list);
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
    @RequestMapping("addSererIntfaceRelation.do")
    @ResponseBody
    public R addSererIntfaceRelation(ServerIntfaceRelation relation) {
        R r = new R();
        Integer result = 0;
        try {
            if (relation == null) {
                return r.error("参数不全");
            }
            //验证客户端合法
            if (relation.getSystemId() == null && relation.getSystemId() <= 0) {
                return r.error("服务端Id 不合法");
            }
            //参数合法后验证 是否存在

            ServcerCode systemCode = serverCodeService.getServerCodeById(relation.getSystemId());
            if (systemCode == null) {
                return r.error("服务端Id 不存在");
            }
            //设置系统名称与编码整合
            relation.setSystemNameCode(systemCode.getSystemName() + "(" + systemCode.getSystemCode() + ")");

            //验证服务端合法
            if (relation.getSenderId() == null && relation.getSenderId() <= 0) {
                return r.error("客户端Id 不合法");
            }

            ServcerCode senderCode = serverCodeService.getServerCodeById(relation.getSenderId());

            if (senderCode == null) {
                return r.error("客户端Id 不存在");
            }
            relation.setSenderName(senderCode.getSystemName());
            if (relation.getSenderId() == relation.getSystemId()) {
                return r.error("客户端与服务端不能一样 ");
            }
            //验证接口名称
            if (StringUtils.isBlank(relation.getIntfaceName())) {
                return r.error("接口名称 為空");
            }
            //验证 serviceCode
            if (StringUtils.isBlank(relation.getServerCode())) {
                return r.error("ServerCode 為空");
            }
            //验证 客户端
            Integer count = serverIntfaceRelationService.getServerInfaceRelationCount(relation);
            if (count > 0) {
                return r.error("改接口记录已存在");
            }
            result = serverIntfaceRelationService.addSererIntfaceRelation(relation);
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
    @RequestMapping("updateServerIntfaceRelation.do")
    @ResponseBody
    public R updateServerIntfaceRelation(ServerIntfaceRelation relation) {
        R r = new R();
        try {
            if (relation == null) {
                return r.error("参数不全");
            }
            if (relation.getId() == null && relation.getId() <= 0) {
                return r.error("ID 不合法");
            }
            ServerIntfaceRelation serverIntfaceRelation = serverIntfaceRelationService.getServerInfaceRelationById(relation);
            if (serverIntfaceRelation == null) {
                return r.error("记录不存在");

            }
            if (relation.getSystemId() != null) {
                if (relation.getSenderId() <= 0) {
                    return r.error("服务端ID 不合法");
                } else {
                    ServcerCode senderCode = serverCodeService.getServerCodeById(relation.getSystemId());
                    if (senderCode == null) {
                        return r.error("服务端ID 不存在");
                    } else {
                        if (relation.getSystemId() == serverIntfaceRelation.getSenderId()) {
                            return r.error("客户端与服务端不能一样");
                        }
                    }
                }
            }
            if (relation.getSenderId() != null) {
                if (relation.getSenderId() <= 0) {
                    return r.error("客户端ID 不合法");
                } else {
                    ServcerCode senderCode = serverCodeService.getServerCodeById(relation.getSenderId());
                    if (senderCode == null) {
                        return r.error("客户端ID 不存在");
                    } else {

                        if (relation.getSenderId() == serverIntfaceRelation.getSystemId()) {
                            return r.error("客户端与服务端不能一样");
                        }

                        relation.setSenderName(senderCode.getSystemName());
                    }
                }
            }

            if (StringUtils.isBlank(relation.getIntfaceName()) && StringUtils.isNotBlank(relation.getServerCode()) && relation.getSystemId() > 0 && relation.getSenderId() != null && relation.getSenderId() != null) {
                return r.error("没有要修改的内容");
            }
            //验证参数 合法
            Integer count = serverIntfaceRelationService.getServerInfaceRelationCount(relation);
            if (count > 0) {
                return r.error("改接口记录已存在");
            }
            Integer result = serverIntfaceRelationService.updateServerIntfaceRelation(relation);
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
    @RequestMapping("deleteServerIntfaceRelation.do")
    @ResponseBody
    public R deleteServerIntfaceRelation(ServerIntfaceRelation relation) {
        R r = new R();
        try {
            if (relation == null) {
                return r.error("参数不全");
            }
            if (relation.getId() == null && relation.getId() <= 0) {
                return r.error("ID 不合法");
            }

            Integer result = serverIntfaceRelationService.deleteServerIntfaceRelation(relation);
            return r.ok(result.toString());
        } catch (Exception e) {
            e.printStackTrace();
            throw new CommonExpection("系統異常", e);
        }
    }

}
