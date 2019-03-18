/*
 * FileName: IntfaceResultRelation.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.bean;

import java.io.Serializable;

/**
 * <p>
 * Description:  接口返回值对应
 * </p>
 *
 * @author dell
 * @version 4.1

 * <p>
 * History:
 *
 * Date                     Author         Version     Description
 * ---------------------------------------------------------------------------------
 * 2018年6月12日 下午9:32:09          dell        4.1         To create
 * </p>
 *
 * @since
 * @see
 */
public class IntfaceResultRelation implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 5226924136203506579L;
    /**
     * 主键
     */
    private Integer Id;
    /**
     * 接口ID 即系统与接口管理表的ID
     */
    private Integer intfaceId;
    /**
     * 接口返回码
     */
    private String resultCode;
    /**
     * 接口返回描述
     */
    private String resultDiscribe;

    /**
     * @return the intfaceId
     */
    public Integer getIntfaceId() {
        return intfaceId;
    }

    /**
     * @param intfaceId the intfaceId to set
     */
    public void setIntfaceId(Integer intfaceId) {
        this.intfaceId = intfaceId;
    }

    /**
     * @return the resultCode
     */
    public String getResultCode() {
        return resultCode;
    }

    /**
     * @param resultCode the resultCode to set
     */
    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    /**
     * @return the resultDiscribe
     */
    public String getResultDiscribe() {
        return resultDiscribe;
    }

    /**
     * @param resultDiscribe the resultDiscribe to set
     */
    public void setResultDiscribe(String resultDiscribe) {
        this.resultDiscribe = resultDiscribe;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return Id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.Id = id;
    }
}
