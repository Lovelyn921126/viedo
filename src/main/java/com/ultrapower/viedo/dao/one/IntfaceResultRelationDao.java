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

import com.ultrapower.viedo.bean.IntfaceResultRelation;

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
public interface IntfaceResultRelationDao {
    /**
      * 获取 相应条件的  接口 返回值 对应关系
     * @param map intfaceId 接口ID   Id 主鍵ID
     * @return
     */
    public List<IntfaceResultRelation> getIntfaceResultRelation(Map<String, Object> map) throws Exception;

    /**
     * 获取 相应条件的  接口 返回值 对应关系
    * @param map intfaceId 接口ID   Id 主鍵ID
    * @return
    */
    public Integer getIntfaceResultRelationCount(Map<String, Object> map) throws Exception;

    /**
     * 获取 相应条件的 服务 接口 对应关系
    * @param id  主鍵
    * @return
    */
    public List<IntfaceResultRelation> getIntfaceResultRelationById(Integer id) throws Exception;

    /**
     * 添加服务 接口 对应关系
     * @param IntfaceResultRelation
     * @return
     */
    public Integer addIntfaceResultRelation(IntfaceResultRelation intfaceResultRelation) throws Exception;

    /**
    * 修改 服务 接口 对应关系
    * @param map resultCode 返回碼  resultDiscribe 描述
    * @return
    */
    public Integer updateIntfaceResultRelation(IntfaceResultRelation relation) throws Exception;

    /**
     * 刪除 服务 接口 对应关系
     * @param map Id 主鍵
     * @return
     */
    public Integer deleteIntfaceResultRelation(IntfaceResultRelation relation) throws Exception;

}
