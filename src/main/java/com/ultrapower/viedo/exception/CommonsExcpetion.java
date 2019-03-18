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
public class CommonsExcpetion extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Integer code = 500;

    private String msg = "系统错误";

    public CommonsExcpetion() {
        super();

    }

    /**
     * @param code
     * @param msg
     */
    public CommonsExcpetion(Integer code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }

    /**
     *
     * @param msg
     * @param e
     */
    public CommonsExcpetion(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;

    }

    /**
     * @param code
     * @param msg
     */
    public CommonsExcpetion(Integer code, String msg, Throwable e) {
        super(msg, e);
        this.code = code;
        this.msg = msg;

    }

    /**
     * @param cause
     */
    public CommonsExcpetion(Throwable cause) {
        super(cause);
        this.msg = msg;

        // TODO Auto-generated constructor stub
    }

    public CommonsExcpetion(String msg) {
        this.msg = msg;
    }

    public CommonsExcpetion(Object obj, String msg) {

        this.msg = JSON.toJSONString(obj) + "*****msg===" + msg;
    }

    /**
     * @return the code
     */
    public Integer getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(Integer code) {
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

}
