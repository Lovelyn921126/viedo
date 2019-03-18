/*
 * FileName: provinceSynchroDemandTask.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.task;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ultrapower.viedo.bean.ProvinceSynchroResult;
import com.ultrapower.viedo.bean.ProvinceZBMapping;
import com.ultrapower.viedo.exception.CommonsExcpetion;
import com.ultrapower.viedo.service.ProvinceSynchroDemandService;
import com.ultrapower.viedo.service.ProvinceZBmappingService;
import com.ultrapower.viedo.utils.SftpUtils;

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
 * 2018年7月10日 上午9:25:36          dell        4.1         To create
 * </p>
 *
 * @since
 * @see
 */
@Component
public class ProvinceSynchroDemandTask {
    @Autowired
    private ProvinceSynchroDemandService provinceSynchroDemandService;
    @Autowired
    private ProvinceZBmappingService provinceZBmappingService;

    public void provinceSynchroTimedTask() throws ParseException {
        //获取省分 总部映射关系
        List<ProvinceZBMapping> provinceZBMappings = null;
        try {
            provinceZBMappings = provinceZBmappingService.getProvinceZbMapping();
        } catch (Exception e) {
            throw new CommonsExcpetion(500, e.getMessage());
        }

        //遍历所有省分的 的信息
        if (provinceZBMappings != null && provinceZBMappings.size() > 0) {
            for (ProvinceZBMapping provinceZBMapping : provinceZBMappings) {
                ProvinceSynchroResult result = new ProvinceSynchroResult();

                result = provinceSynchroDemandService.getProvinceSynchroDemand(provinceZBMapping.getProvinceCode(), null);
                SftpUtils.writStringToFile(result, provinceZBMapping.getProvinceCode());

            }
        }
    }
}
