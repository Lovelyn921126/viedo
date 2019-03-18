/*
 * FileName: BrowseRecordDao.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.ultrapower.viedo.bean.ViedoDetial;
import com.ultrapower.viedo.bean.ViedoRecord;
import com.ultrapower.viedo.vo.ViedoDetialVo;

/**
 * <p>
 * Description:  视频详情
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
public interface ViedoDetialDaoMapper {
    /**
     * 添加视频详情
     * @param viedoDetial
     * @return
     */
    public ViedoDetial addViedoDetail(ViedoDetial viedoDetial);

    /**
     * 添加视频上传记录的详情详情
     * @param viedoDetial
     * @return
     */
    public int addViedoDetailUploadRecord(ViedoRecord viedoRecord);

    /**
     * 查询视频详情
     * @param map id 视频ID orderByUploadTime 详情页排序按上传时间
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<ViedoDetialVo> getViedoDetail(Map<String, Object> map, PageBounds pageBounds);

    /**
     * 查询视频详情根据视频ID
     * @param map id 视频ID
     * @return
     */

    public ViedoDetialVo getViedoDetailById(Map<String, Object> map);

    /**
     * 更新视频详情
     * @param viedoDetial
     * @return
     */
    public int updateViedoDetail(ViedoDetial viedoDetial);

    /**
     * 获取视频总数
     * @param map
     * @param viedoDetial
     * @return
     */
    public Integer getCountViedoDetail(Map<String, Object> map);

    /**
     * 增加浏览次数
     * @param viedoDetial
     * @return
     */
    public int updateViedoDetailBrowseCount(Map<String, Object> map);

    /**
     * 增加点赞次数
     * @param viedoDetial
     * @return
     */
    public int updateViedoDetailPariseCount(Map<String, Object> map);

    /**
     * 比对MD5
     * @param viedoDetial
     * @return
     */
    public Integer comparisonViedoMD5(String md5);

}
