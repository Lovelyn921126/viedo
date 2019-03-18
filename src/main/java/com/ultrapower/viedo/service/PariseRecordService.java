/*
 * FileName: PariseRecordDao.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ultrapower.viedo.bean.PraiseRecord;
import com.ultrapower.viedo.constants.ViedoSystemResult;
import com.ultrapower.viedo.dao.PariseRecordDaoMapper;
import com.ultrapower.viedo.dao.ViedoDetialDaoMapper;
import com.ultrapower.viedo.vo.RepData;

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
@Service
public class PariseRecordService {
    //getPariseRecord  addPariseRecord
    @Autowired
    private PariseRecordDaoMapper pariseRecordDao;
    @Autowired
    private ViedoDetialDaoMapper viedoDetialDao;

    /**
     * 添加点赞记录
     * @param parameter
     * @return
     */
    public int addPariseRecord(PraiseRecord parameter) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", parameter.getViedoId());

        map.put("viedoId", parameter.getViedoId());
        map.put("userName", parameter.getUserName());
        Integer pariseRecord = pariseRecordDao.getPariseRecordCountByUserId(map);
        parameter.setPraiseTime(new Timestamp(System.currentTimeMillis()));
        parameter.setVaildStatus(1);
        if (pariseRecord == 0) {
            int result = pariseRecordDao.addPariseRecord(parameter);
            if (result > 0) {
                int count = viedoDetialDao.updateViedoDetailPariseCount(map);

                return count;
            }
        }
        return 0;

    }

    /**
     * 获取点赞记录
     * @param parameter viedoid视频ID  username 用户名
     * @return
     */
    public RepData<Boolean> getPariseRecord(PraiseRecord parameter) {
        RepData<Boolean> repData = new RepData<Boolean>();

        repData.setCode(ViedoSystemResult.Success.getCode());
        repData.setMsg(ViedoSystemResult.Success.getMsg());
        repData.setData(false);
        repData.setCount(0);
        if (StringUtils.isBlank(parameter.getUserName()) || parameter.getViedoId() < 0) {
            return repData;
        }
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("viedoId", parameter.getViedoId());
        map.put("userName", parameter.getUserName());
        repData.setData(pariseRecordDao.getPariseRecordCountByUserId(map) > 0);
        repData.setCount(1);
        return repData;
    }
}
