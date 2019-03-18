/*
 * FileName: BrowseRecordDao.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ultrapower.viedo.bean.BrowseRecord;

/**
 * <p>
 * Description:  浏览记录的Dao
 * </p>
 *
 * @author dell
 * @version 4.1

 * <p>
 * History:
 *
 * Date                     Author         Version     Description
 * ---------------------------------------------------------------------------------
 * 2017年11月29日 下午2:53:20          dell        4.1         To create
 * </p>
 *
 * @since
 * @see
 */
@Repository
public interface BrowseRecordDaoMapper {
    /**
     * 添加点赞记录
     * @param parameter
     * @return
     */
    public int addBrowseRecord(BrowseRecord parameter) throws Exception;

    /**
     *  获取点赞记录
     * @param parameter viedoid视频ID  username 用户名
     * @return
     */

    public List<BrowseRecord> getBrowseRecordByUserId(Map<String, Object> parameter) throws Exception;
}
