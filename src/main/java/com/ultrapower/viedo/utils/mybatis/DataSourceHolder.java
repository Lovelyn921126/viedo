/*
 * FileName: DataSourceHolder.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.utils.mybatis;

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
 * 2018年4月4日 下午3:13:21          dell        4.1         To create
 * </p>
 *
 * @since
 * @see
 */
public class DataSourceHolder {

    private final static String MASTER = "master";
    private final static String SLAVE = "slave";
    private static ThreadLocal<String> DATESOURCE = new ThreadLocal<String>();

    public static void setDateSource(String datasource) {
        DATESOURCE.set(datasource);

    }

    public static String getDateSource() {
        return DATESOURCE.get();
    }

    public static void setMaster() {
        DATESOURCE.set(MASTER);
        System.out.println(getDateSource());
    }

    public static void setSlave() {
        DATESOURCE.set(SLAVE);
        System.out.println(getDateSource());
    }

    public static boolean isMaster() {
        System.out.println(getDateSource());
        return getDateSource() == MASTER;
    }

    public static boolean isSlave() {
        System.out.println(getDateSource());
        return getDateSource() == SLAVE;
    }

    public static void clean() {
        DATESOURCE.remove();
    }

    /**
     * @return the dATESOURCE
     */
    public ThreadLocal<String> getDATESOURCE() {
        return DATESOURCE;
    }

    /**
     * @param dATESOURCE the dATESOURCE to set
     */
    public void setDATESOURCE(ThreadLocal<String> dATESOURCE) {
        DATESOURCE = dATESOURCE;
    }
}
