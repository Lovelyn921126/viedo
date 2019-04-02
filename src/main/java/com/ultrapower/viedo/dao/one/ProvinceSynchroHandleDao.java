/*
 * FileName: ProvinceSynchroDemandDao.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.dao.one;

import org.springframework.stereotype.Repository;

import com.ultrapower.viedo.bean.ProvinceSynchroHandle;

/**
 * <p>
 * Description: 省分同步工單數據
 * </p>
 *
 * @author dell
 * @version 4.1

 * <p>
 * History:
 *
 * Date                     Author         Version     Description
 * ---------------------------------------------------------------------------------
 * 2018年6月26日 上午11:19:02          dell        4.1         To create
 * </p>
 *
 * @since
 * @see
 */
@Repository
public interface ProvinceSynchroHandleDao {
    /**
    * 添加省分同步工單數據
    * @param demand
    * @return
    */
    public Integer addProvinceSynchroHandel(ProvinceSynchroHandle handle);

    /**
     * 修改省分同步工單數據  用于测试
     * @param demand
     * @return
     */
    public Integer updateProvinceSynchroHandel();

    /**
     * 通过省分工单编号删除 工单处理记录
     * @param demand
     * @return
     */
    public Integer deleteProvinceSynchroHandelByProcessNum(String processNum);

}
