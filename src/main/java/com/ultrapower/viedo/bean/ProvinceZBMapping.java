/*
 * FileName: ProvinceZBMapping.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.bean;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

/**
 * <p>
 * Description:  总部与省分编号ID 映射表
 * </p>
 *
 * @author dell
 * @version 4.1

 * <p>
 * History:
 *
 * Date                     Author         Version     Description
 * ---------------------------------------------------------------------------------
 * 2018年5月31日 上午9:50:34          dell        4.1         To create
 * </p>
 *
 * @since
 * @see
 */
@Component
public class ProvinceZBMapping {
    /**
    * 主键
    */
    private Integer id;
    /**
     * 省份接口的省份编码
     */
    private Integer pSourceNum;
    /**
     * 省份接口的省份名称
     */
    private String pSourceName;
    /**
     * 总部省份ID
     */
    private Integer provinceId;
    /**
     * 沃工单省份ID
     */
    private String provinceName;
    /**
     *省份編碼
     */
    private String provinceCode;

    @PostConstruct
    public void initProvinceZBMapping() {

    }

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
     * @return the pSourceNum
     */
    public Integer getpSourceNum() {
        return pSourceNum;
    }

    /**
     * @param pSourceNum the pSourceNum to set
     */
    public void setpSourceNum(Integer pSourceNum) {
        this.pSourceNum = pSourceNum;
    }

    /**
     * @return the pSourceName
     */
    public String getpSourceName() {
        return pSourceName;
    }

    /**
     * @param pSourceName the pSourceName to set
     */
    public void setpSourceName(String pSourceName) {
        this.pSourceName = pSourceName;
    }

    /**
     * @return the provinceId
     */
    public Integer getProvinceId() {
        return provinceId;
    }

    /**
     * @param provinceId the provinceId to set
     */
    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    /**
     * @return the provinceName
     */
    public String getProvinceName() {
        return provinceName;
    }

    /**
     * @param provinceName the provinceName to set
     */
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    /**
     * @return the provinceCode
     */
    public String getProvinceCode() {
        return provinceCode;
    }

    /**
     * @param provinceCode the provinceCode to set
     */
    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }
}
