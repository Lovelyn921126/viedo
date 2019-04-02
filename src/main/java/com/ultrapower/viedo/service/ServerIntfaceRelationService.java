/*
 * FileName: ServerCodeService.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ultrapower.viedo.bean.ServerIntfaceRelation;
import com.ultrapower.viedo.dao.one.ServerIntfaceRelationDao;

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
 * 2018年6月13日 下午5:27:55          dell        4.1         To create
 * </p>
 *
 * @since
 * @see
 */
@Service
public class ServerIntfaceRelationService {
    @Autowired
    private ServerIntfaceRelationDao serverIntfaceRelationDao;

    /**
    * 获取 相应条件的 系統編碼
    * @param code 系統編碼
    * @param pageSize  分页大小
    * @param pageNum 分页页码
    * @return
    */
    public List<ServerIntfaceRelation> getServerInfaceRelation(ServerIntfaceRelation relation, Integer pageSize, Integer pageNum) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        //systemId 系统ID systemNameCode 服务端名称加编号 serviceName 客户端名称 intfaceName 接口名称 serverCode
        map.put("systemId", relation.getSystemId());
        map.put("systemNameCode", relation.getSystemNameCode());
        map.put("serviceName", relation.getSenderName());
        map.put("intfaceName", relation.getIntfaceName());
        map.put("serverCode", relation.getServerCode());
        map.put("senderId", relation.getSenderId());
        //编辑分页 数据
        if (pageNum != null && pageNum > 0 && pageSize != null && pageSize > 0) {
            map.put("pageSize", pageNum * pageSize);
            map.put("pageNum", ((pageNum - 1) * pageSize) + 1);
        }
        return serverIntfaceRelationDao.getServerInfaceRelation(map);
    }

    /**
     * 根据ID的 系統編碼
     * @param code 系統編碼
     * @return
     */
    public ServerIntfaceRelation getServerInfaceRelationById(ServerIntfaceRelation relation) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        //systemId 系统ID systemNameCode 服务端名称加编号 serviceName 客户端名称 intfaceName 接口名称 serverCode
        map.put("Id", relation.getId());
        List<ServerIntfaceRelation> list = serverIntfaceRelationDao.getServerInfaceRelation(map);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    /**
     * 获取 相应条件的 系統編碼 数目
     * @param code 系統編碼
     * @return
     */
    public Integer getServerInfaceRelationCount(ServerIntfaceRelation relation) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        //systemId 系统ID systemNameCode 服务端名称加编号 serviceName 客户端名称 intfaceName 接口名称 serverCode
        map.put("systemId", relation.getSystemId());
        map.put("systemNameCode", relation.getSystemNameCode());
        map.put("senderName", relation.getSenderName());
        map.put("senderId", relation.getSenderId());
        map.put("intfaceName", relation.getIntfaceName());
        map.put("serverCode", relation.getServerCode());
        return serverIntfaceRelationDao.getServerInfaceRelationCount(map);
    }

    /**
     * 添加 相应条件的 系統編碼 数目
     * @param code 系統編碼
     * @return
     */
    public Integer addSererIntfaceRelation(ServerIntfaceRelation relation) throws Exception {

        if (relation != null) {

            return serverIntfaceRelationDao.addSererIntfaceRelation(relation);
        }
        return 0;
    }

    /**
     * 添加 相应条件的 系統編碼 数目
     * @param code 系統編碼
     * @return
     */
    public Integer deleteServerIntfaceRelation(ServerIntfaceRelation relation) throws Exception {

        return serverIntfaceRelationDao.deleteServerIntfaceRelation(relation);

    }

    /**
     * 修改 相应条件的 系統編碼 数目
     * @param code 系統編碼
     * @return
     */
    public Integer updateServerIntfaceRelation(ServerIntfaceRelation relation) throws Exception {

        return serverIntfaceRelationDao.updateServerIntfaceRelation(relation);
    }
}
