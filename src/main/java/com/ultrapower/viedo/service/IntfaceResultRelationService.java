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

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ultrapower.viedo.bean.IntfaceResultRelation;
import com.ultrapower.viedo.dao.IntfaceResultRelationDao;

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
public class IntfaceResultRelationService {
    @Autowired
    private IntfaceResultRelationDao intfaceResultRelationDao;

    /**
    * 获取 相应条件的 接口返回值关系
    * @param relation 系統編碼
    * @param pageSize  分页大小
    * @param pageNum 分页页码
    * @return
     * @throws Exception
    */
    public List<IntfaceResultRelation> getIntfaceResultRelation(IntfaceResultRelation relation, Integer pageSize, Integer pageNum) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        // intfaceId 接口ID Id 主鍵ID
        map.put("intfaceId", relation.getIntfaceId());
        map.put("Id", relation.getId());
        if (pageNum != null && pageNum > 0 && pageSize != null && pageSize > 0) {
            map.put("pageSize", pageNum * pageSize);
            map.put("pageNum", ((pageNum - 1) * pageSize) + 1);
        }
        return intfaceResultRelationDao.getIntfaceResultRelation(map);
    }

    /**
     * 获取 相应条件的 系統編碼 数目
     * @param code 系統編碼
     * @return
     * @throws Exception
     */
    public Integer getIntfaceResultRelationCount(IntfaceResultRelation relation) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        // intfaceId 接口ID Id 主鍵ID
        map.put("intfaceId", relation.getIntfaceId());
        map.put("Id", relation.getId());

        return intfaceResultRelationDao.getIntfaceResultRelationCount(map);
    }

    /**
     * 添加 相应条件的 系統編碼 数目
     * @param code 系統編碼
     * @return
     * @throws Exception
     */
    public Integer addIntfaceResultRelation(IntfaceResultRelation relation) throws Exception {

        if (relation != null) {
            if (StringUtils.isNotBlank(relation.getResultCode()) && StringUtils.isNotBlank(relation.getResultDiscribe())) {
                return intfaceResultRelationDao.addIntfaceResultRelation(relation);
            }
        }
        return 0;
    }

    /**
     * 添加 相应条件的 系統編碼 数目
     * @param code 系統編碼
     * @return
     * @throws Exception
     */
    public Integer deleteIntfaceResultRelation(IntfaceResultRelation relation) throws Exception {
        if (relation != null) {
            return intfaceResultRelationDao.deleteIntfaceResultRelation(relation);
        }
        return 0;

    }

    /**
     * 添加 相应条件的 系統編碼 数目
     * @param code 系統編碼
     * @return
     * @throws Exception
     */
    public Integer updateIntfaceResultRelation(IntfaceResultRelation relation) throws Exception {

        return intfaceResultRelationDao.updateIntfaceResultRelation(relation);

    }
}
