/*
 * FileName: ProvinceZBmappingService.java
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

import com.ultrapower.viedo.bean.ProvinceZBMapping;
import com.ultrapower.viedo.dao.ProvinceZBMappingDao;

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
 * 2018年7月10日 上午9:34:38          dell        4.1         To create
 * </p>
 *
 * @since
 * @see
 */
@Service
public class ProvinceZBmappingService {
    @Autowired
    private ProvinceZBMappingDao provinceZBMappingDao;

    /**
     * 获取所有的 总部 省分 对应关系
     * @return
     * @throws Exception
     */
    public List<ProvinceZBMapping> getProvinceZbMapping() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        return provinceZBMappingDao.getProvinceZbMapping(map);
    }
}
