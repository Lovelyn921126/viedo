/*
 * FileName: CommonExpection.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.utils;

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
 * 2018年4月13日 上午9:38:40          dell        4.1         To create
 * </p>
 *
 * @since
 * @see
 */
public class CommonExpection extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1674310849541657370L;
    /**
     * 错误代码
     */
    private Integer code = 500;
    /**
     * 错误内容
     *
     */
    private String msg;

    /**
     * @param code
     * @param msg
     */
    public CommonExpection(Integer code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }

    public CommonExpection(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public CommonExpection(Integer code, String msg, Throwable e) {
        super(msg, e);
        this.code = code;
        this.msg = msg;
    }

    /**
     * @param cause
     */
    public CommonExpection(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }

    public CommonExpection(String msg) {
        this.msg = msg;
    }

    public CommonExpection(Object obj, String msg) {

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
