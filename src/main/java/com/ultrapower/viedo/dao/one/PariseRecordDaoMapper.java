/*
 * FileName: PariseRecordDao.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.dao.one;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ultrapower.viedo.bean.PraiseRecord;

/**
 * <p>
 * Description: 点赞记录
 * </p>
 *
 * @author dell
 * @version 4.1

 * <p>
 * History: 
 *
 * Date                     Author         Version     Description
 * ---------------------------------------------------------------------------------
 * 2017年11月29日 下午3:25:22          dell        4.1         To create
 * </p>
 *
 * @since 
 * @see     
 */
@Repository
public interface PariseRecordDaoMapper {
    //getPariseRecord  addPariseRecord
    /**
     * 添加点赞记录
     * @param parameter
     * @return
     */
    public int addPariseRecord(PraiseRecord parameter);

    /**
     * 获取点赞记录
     * @param parameter viedoid视频ID  username 用户名
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<PraiseRecord> getPariseRecord(Map<String, Object> parameter);

    /**
     * @param map
     * @return
     */
    public Integer getPariseRecordCountByUserId(Map<String, Object> map);

}
