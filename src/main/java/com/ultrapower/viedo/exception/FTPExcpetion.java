/*
 * FileName: TfsExcpetion.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.exception;

import com.alibaba.fastjson.JSON;

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
 * 2018年6月28日 下午11:14:46          dell        4.1         To create
 * </p>
 *
 * @since
 * @see
 */
public class FTPExcpetion extends RuntimeException {

    private String code = "500";

    private String msg = "系统错误";
    private String provinceCode = "WD";

    /**
     * @param code
     * @param msg
     */
    public FTPExcpetion(String code, String msg, String provinceCode) {
        super();
        this.code = code;
        this.msg = msg;
        this.provinceCode = provinceCode;
    }

    /**
     *
     * @param msg
     * @param e
     */
    public FTPExcpetion(String msg, String provinceCode, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.provinceCode = provinceCode;
    }

    /**
     * @param code
     * @param msg
     */
    public FTPExcpetion(String code, String msg, String provinceCode, Throwable e) {
        super(msg, e);
        this.code = code;
        this.msg = msg;
        this.provinceCode = provinceCode;
    }

    /**
     * @param cause
     */
    public FTPExcpetion(Throwable cause) {
        super(cause);
        this.msg = msg;
        this.provinceCode = provinceCode;
        // TODO Auto-generated constructor stub
    }

    public FTPExcpetion(String msg) {
        this.msg = msg;
    }

    public FTPExcpetion(String code, String msg) {

        this.code = code;
        this.msg = msg;
    }

    public FTPExcpetion(Object obj, String msg) {

        this.msg = JSON.toJSONString(obj) + "*****msg===" + msg;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
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
