/*
 * FileName: ServerIntfaceRelationDao.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.dao.one;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ultrapower.viedo.bean.ServerIntfaceRelation;

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
 * 2018年6月13日 下午3:56:21          dell        4.1         To create
 * </p>
 *
 * @since
 * @see
 */
@Repository
public interface ServerIntfaceRelationDao {

    /**
      * 获取 相应条件的 服务 接口 对应关系
     * @param map systemId 系统ID systemNameCode 服务端名称加编号  serviceName 客户端名称 intfaceName 接口名称 serverCode
     * @return
     */
    public List<ServerIntfaceRelation> getServerInfaceRelation(Map<String, Object> map) throws Exception;

    /**
     * 获取 相应条件的 服务 接口 对应关系 数目
    * @param map systemId 系统ID systemNameCode 服务端名称加编号  serviceName 客户端名称 intfaceName 接口名称 serverCode
    * @return
    */
    public Integer getServerInfaceRelationCount(Map<String, Object> map) throws Exception;

    /**
     * 添加服务 接口 对应关系
     * @param serverIntfaceRelation
     * @return
     */
    public Integer addSererIntfaceRelation(ServerIntfaceRelation serverIntfaceRelation) throws Exception;

    /**
    * 修改 服务 接口 对应关系記錄
    * @param relation
    * @return
    */
    public Integer updateServerIntfaceRelation(ServerIntfaceRelation relation) throws Exception;

    /**
     * 刪除 服务 接口 对应关系記錄
     * @param relation
     * @return
     */
    public Integer deleteServerIntfaceRelation(ServerIntfaceRelation relation) throws Exception;

}
