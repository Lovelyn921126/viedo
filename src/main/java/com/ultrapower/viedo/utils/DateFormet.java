/*
 * FileName: DateFormat.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.utils;

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
 * 2018年4月25日 下午5:07:04          dell        4.1         To create
 * </p>
 *
 * @since
 * @see
 */
public enum DateFormet {
    YY_MM_DD_HH_MM_SS("yyyy-MM-dd HH:mm:ss"),

    YY_MM_DD_HH_MM_SS_SSS("yyyy-MM-dd HH:mm:ss.SSS"),

    YY_MM_DD("yyyy-MM-dd"),

    MM_DD("MM_dd"),

    MM_DD_HH_MM_SS("MM-dd HH:mm:ss"),

    YYMMDD("yyyyMMdd"),

    HH_MM_SS("HH:mm:ss");

    DateFormet(String value) {
        this.value = value;
    }

    private String value;

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

}
