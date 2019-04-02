/*
 * FileName: BrowseRecordDao.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ultrapower.viedo.bean.BrowseRecord;
import com.ultrapower.viedo.dao.one.BrowseRecordDaoMapper;
import com.ultrapower.viedo.dao.one.ViedoDetialDaoMapper;
import com.ultrapower.viedo.utils.CommonExpection;

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
@Service
public class BrowseRecordService {
    //getPariseRecord  addPariseRecord

    @Autowired
    private BrowseRecordDaoMapper browseRecordDao;
    @Autowired
    private ViedoDetialDaoMapper viedoDetialDao;

    /**
     * 添加点赞记录
     * @param parameter
     * @return
     */
    public int addBrowseRecord(BrowseRecord parameter) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", parameter.getViedoId());
        parameter.setBrowseTime(new Timestamp(System.currentTimeMillis()));
        parameter.setVaildStatus(1);
        int result;
        try {
            result = browseRecordDao.addBrowseRecord(parameter);
            if (result > 0) {
                int count = viedoDetialDao.updateViedoDetailBrowseCount(map);
                return count;
            }
        } catch (Exception e) {
            throw new CommonExpection(e.getMessage());
        }
        return 0;
    }

    /**
     * 获取点赞记录
     * @param parameter viedoid视频ID  username 用户名
     * @return
     */
    public List<BrowseRecord> getBrowseRecord(Map<String, Object> parameter) {
        try {
            return browseRecordDao.getBrowseRecordByUserId(parameter);
        } catch (Exception e) {
            throw new CommonExpection(e.getMessage());
        }
    }
}
