/*
 * FileName: ProvinceSynchroResult.java
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
 * 2018年7月3日 上午9:36:46          dell        4.1         To create
 * </p>
 *
 * @since
 * @see
 */
public enum ProvinceSynchroResultEnum {
    /**
     *文件读取正常
     */
    readNormal("000", "文件读取正常"),
    /**
     *文件格式错误，解析失败
     */
    fileError("001", "解析失败"),
    /**
     *文件不存在或文件名错误
     */
    fileNotExist("002", "文件不存在或文件名错误"),
    /**
     *没有上传相关的信息 （有文件 但文件内关于工单的所有信息为空）
     */

    notMessage("003", "没有相关的记录"),
    /**
     * 004, "文件内容错误"
     */
    fileMessageError("004", "文件内容错误"),

    /**
     *日期不匹配
     */
    dateFormat("011", "日期不匹配"),
    /**
     *非法数字
     */
    illegalNumber("012", "非法数字"),
    /**
     *字符长度超出范围
     */
    characterExceedingLength("013", "字符长度超出范围"),
    /**
     *字段要求格式错误
     */

    invalidFormat("014", "字段要求格式错误"),
    /**
     *必填字段为空
     */
    requiredIsNull("015", "必填字段为空"),
    /**
     *  包含特殊字符
     */
    containsSpecialCharacters("016", "包含特殊字符"),
    /**
     *工单的处理记录 异常为空
     */
    demandHandleEmpty("017", "工单的处理记录 异常为空"),
    /**
    *其他
    */
    Other("010", "其他");

    ProvinceSynchroResultEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private String code;
    private String msg;

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

}
