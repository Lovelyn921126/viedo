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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ultrapower.viedo.bean.ServcerCode;
import com.ultrapower.viedo.dao.one.ServerCodeDao;

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
public class ServerCodeService {
    @Autowired
    private ServerCodeDao serverCodeDao;

    /**
    * 获取 相应条件的 系統編碼
    * @param code 系統編碼
    * @param pageSize  分页大小
    * @param pageNum 分页页码
    * @return
     * @throws Exception
    */
    public List<ServcerCode> getServerCode(ServcerCode code, Integer pageSize, Integer pageNum) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        //systemName 系统名称 Id 系統ID systemCode 系统編碼
        map.put("systemCode", code.getSystemCode());
        map.put("systemName", code.getSystemName());
        map.put("Id", code.getId());
        if (pageNum != null && pageNum > 0 && pageSize != null && pageSize > 0) {
            map.put("pageSize", pageNum * pageSize);
            map.put("pageNum", ((pageNum - 1) * pageSize) + 1);
        }
        return serverCodeDao.getServerCode(map);
    }

    /**
     * 获取 相应条件的 系統編碼 数目
     * @param code 系統編碼
     * @return
     * @throws Exception
     */
    public Integer getServerCodeCount(ServcerCode code) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        //systemName 系统名称 Id 系統ID systemCode 系统編碼
        map.put("systemCode", code.getSystemCode());
        map.put("systemName", code.getSystemName());
        map.put("Id", code.getId());
        return serverCodeDao.getServerCodeCount(map);
    }

    /**
     * 校验 记录是否存在
     * @param id
     * @return  id 服务ID
     * @throws Exception
     */
    public ServcerCode getServerCodeById(Integer id) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        //systemName 系统名称 Id 系統ID systemCode 系统編碼

        map.put("Id", id);
        List<ServcerCode> list = serverCodeDao.getServerCode(map);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    /**
     * 添加 相应条件的 系統編碼 数目
     * @param code 系統編碼
     * @return
     * @throws Exception
     */
    public Integer addServerCode(ServcerCode code) throws Exception {

        if (code != null) {

            return serverCodeDao.addServerCode(code);

        }
        return 0;
    }

    /**
     * 删除 相应条件的 系統編碼 数目
     * @param code 系統編碼
     * @return
     * @throws Exception
     */
    public Integer deleteServerCode(ServcerCode code) throws Exception {
        if (code != null) {
            Map<String, Object> map = new HashMap<String, Object>();
            //systemName 系统名称 Id 系統ID systemCode 系统編碼
            map.put("systemCode", code.getSystemCode());
            map.put("systemName", code.getSystemName());

            map.put("Id", code.getId());
            return serverCodeDao.deleteServerCode(map);
        }
        return 0;

    }

    /**
     * 修改 相应条件的 系統編碼 数目
     * @param code 系統編碼
     * @return
     * @throws Exception
     */
    public Integer updateServerCode(ServcerCode code) throws Exception {
        if (code != null) {

            Map<String, Object> map = new HashMap<String, Object>();
            //systemName 系统名称 Id 系統ID systemCode 系统編碼
            map.put("systemCode", code.getSystemCode());
            map.put("systemName", code.getSystemName());
            map.put("Id", code.getId());
            return serverCodeDao.updateServerCode(map);
        }
        return 0;
    }

}
