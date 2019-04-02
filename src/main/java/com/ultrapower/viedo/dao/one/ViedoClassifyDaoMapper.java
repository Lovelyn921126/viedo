/*
 * FileName: BrowseRecordDao.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.dao.one;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ultrapower.viedo.bean.ViedoClassify;

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
@Repository
public interface ViedoClassifyDaoMapper {
    /**
     * 根据条件获取视频分类 的信息
      * @param map parentid 父级分类ID id 分类ID  level 分类级别
     * @return ViedoClassify 实体
     */
    public List<ViedoClassify> getViedoClassify(Map<String, Object> map);

    /**
     * 通过ID 获取取视频分类 的信息
     * @param map
     * @return
     */
    public ViedoClassify getViedoClassifyById(Map<String, Object> map);

}
