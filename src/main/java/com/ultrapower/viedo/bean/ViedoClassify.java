/*
 * FileName: ViedoClassify.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.bean;

import java.io.Serializable;
import java.sql.Timestamp;

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
 * 2017年11月29日 下午2:49:07          dell        4.1         To create
 * </p>
 *
 * @since 
 * @see     
 */
public class ViedoClassify implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -9127617832843497758L;
    private Integer id;
    /**
     * 分类名称
     */
    private String classifyName;
    /**
     *  是否删除（0删除，1正常）
     */
    private Integer vaildStatus;
    /**
     * 父级分类ID
     */
    private Integer parentId;
    /**
     * 分类级别
     */
    private Integer level;
    /**
     * 创建时间
     */
    private Timestamp createtime;
    /**
     * 专业的路径
     */
    private String way;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the classifyName
     */
    public String getClassifyName() {
        return classifyName;
    }

    /**
     * @param classifyName the classifyName to set
     */
    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }

    /**
     * @return the vaildStatus
     */
    public Integer getVaildStatus() {
        return vaildStatus;
    }

    /**
     * @param vaildStatus the vaildStatus to set
     */
    public void setVaildStatus(Integer vaildStatus) {
        this.vaildStatus = vaildStatus;
    }

    /**
     * @return the parentId
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * @param parentId the parentId to set
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * @return the level
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * @param level the level to set
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * @return the createtime
     */
    public Timestamp getCreatetime() {
        return createtime;
    }

    /**
     * @param createtime the createtime to set
     */
    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    /**
     * @return the way
     */
    public String getWay() {
        return way;
    }

    /**
     * @param way the way to set
     */
    public void setWay(String way) {
        this.way = way;
    }

}
