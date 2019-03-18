/*
 * FileName: ServcerCode.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.bean;

import java.io.Serializable;

/**
 * <p>
 * Description:  系统编码
 * </p>
 *
 * @author dell
 * @version 4.1

 * <p>
 * History:
 *
 * Date                     Author         Version     Description
 * ---------------------------------------------------------------------------------
 * 2018年6月12日 下午9:26:12          dell        4.1         To create
 * </p>
 *
 * @since
 * @see
 */
public class ServcerCode implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 6108299162611713061L;
    /**
     * 主键
     */
    private Integer Id;
    /**
     * 系统名称
     */
    private String systemName;
    /**
     * 系统编码
     */
    private String systemCode;
    /**
    *系统名称编码 合成 用于下拉框展示 类似于 CRM系统（10.1001）
    */
    private String systemNameCode;

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
        Id = id;
    }

    /**
     * @return the systemName
     */
    public String getSystemName() {
        return systemName;
    }

    /**
     * @param systemName the systemName to set
     */
    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    /**
     * @return the systemCode
     */
    public String getSystemCode() {
        return systemCode;
    }

    /**
     * @param systemCode the systemCode to set
     */
    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }

    /**
     * @return the systemNameCode
     */
    public String getSystemNameCode() {
        return systemNameCode;
    }

    /**
     * @param systemNameCode the systemNameCode to set
     */
    public void setSystemNameCode(String systemNameCode) {
        this.systemNameCode = systemNameCode;
    }

}
