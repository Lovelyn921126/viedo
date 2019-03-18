/*
 * FileName: ServerIntfaceRelationDao.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ultrapower.viedo.bean.ServcerCode;

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
public interface ServerCodeDao {
    /**
      * 获取 相应条件的 系統編碼
     * @param map systemName 系统名称  Id 系統ID systemCode 系统編碼
     * @return
     */
    public List<ServcerCode> getServerCode(Map<String, Object> map) throws Exception;

    /**
     *根据ID 系統編碼
    * @param map  Id 系統ID
    * @return
    */
    public ServcerCode getServerCodeById(Map<String, Object> map) throws Exception;

    /**
     * 获取 相应条件的 系統編碼
    * @param map systemName 系统名称  Id 系統ID systemCode 系统編碼
    * @return
    */
    public Integer getServerCodeCount(Map<String, Object> map) throws Exception;

    /**
     * 添加服务 系統編碼 記錄
     * @param IntfaceResultRelation
     * @return
     */
    public Integer addServerCode(ServcerCode servcerCode) throws Exception;

    /**
    * 修改 服务 接口 对应关系
    * @param map systemName 系統名稱  SYSTEM_CODE 系統編碼
    * @return
    */
    public Integer updateServerCode(Map<String, Object> servcerCode) throws Exception;

    /**
     * 刪除 服务 系統編碼 記錄
     * @param map systemName 系统名称  Id 系統ID systemCode 系统編碼
     * @return
     */
    public Integer deleteServerCode(Map<String, Object> servcerCode) throws Exception;

}
