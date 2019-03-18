/*
 * FileName: BrowseRecordDao.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ultrapower.viedo.bean.ViedoClassify;
import com.ultrapower.viedo.constants.ViedoSystemResult;
import com.ultrapower.viedo.dao.ViedoClassifyDaoMapper;
import com.ultrapower.viedo.vo.RepData;

/**
 * <p>
 * Description:  视频分类
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
public class ViedoClassifyService {
    @Autowired
    private ViedoClassifyDaoMapper viedoClassifyDao;

    /**
     * 根据条件获取视频分类 的信息
     * @param map parentid 父级分类ID id 分类ID  level 分类级别
     * @return ViedoClassify 实体
     */
    public RepData<List<ViedoClassify>> getViedoClassify(Map<String, Object> map) {
        RepData<List<ViedoClassify>> repData = new RepData<List<ViedoClassify>>();
        repData.setCode(ViedoSystemResult.Success.getCode());
        repData.setMsg(ViedoSystemResult.Success.getMsg());
        List<ViedoClassify> list = viedoClassifyDao.getViedoClassify(map);
        repData.setData(list);
        repData.setCount(list.size());
        return repData;
    }
}
