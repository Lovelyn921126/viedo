/*
 * FileName: ProvinceSynchroDemandDao.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.dao.one;

import org.springframework.stereotype.Repository;

import com.ultrapower.viedo.bean.ProvinceSynchroDemand;

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
public interface ProvinceSynchroDemandDao {
    /**
    * 添加省分同步工單數據
    * @param demand
    * @return
    */
    public Integer addProvinceSynchroDemand(ProvinceSynchroDemand demand);

}
