/*
 * FileName: ViedoSystemResult.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.constants;

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
 * 2017年12月4日 上午9:27:03          dell        4.1         To create
 * </p>
 *
 * @since 
 * @see     
 */
public enum ViedoSystemResult {

    /**
    * 
    */
    Empty(501, "参数为空"), UrlError(500, "路径不合法"), NotChange(501, "你本次提交的内容没有改变，请重新选择"), UserInfoError(501, "用户错误"), Success(200, "SUCCESS"), ViedoExsts(500, "视频已存在，请重新上传"), Overflow(500, "视频超过规定大小，请重新选择"), SaveError(500, "上传失败,文件上传异常"), UserInfoERROR(501, "用户错误"), UserInfoEmpty(501, "用户不存在"), SystemError(500, "系统错误，请重新上传"), FormatError(500, "格式错误"), ERROR(200, "系统错误");

    /**
     * @param code
     * @param msg
     */
    ViedoSystemResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private int code;
    private String msg;

    /**
     * @return the code
     */
    public int getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(int code) {
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
