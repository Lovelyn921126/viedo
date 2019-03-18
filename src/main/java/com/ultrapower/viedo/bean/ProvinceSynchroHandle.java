/*
 * FileName: ProvinceSynchroHandle.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.ultrapower.viedo.viladate.ValidateInt;
import com.ultrapower.viedo.viladate.ValidateParameter;
import com.ultrapower.viedo.viladate.ValidateString;

/**
 * <p>
 * Description:  省分同步工单处理工程
 * </p>
 *
 * @author dell
 * @version 4.1

 * <p>
 * History:
 *
 * Date                     Author         Version     Description
 * ---------------------------------------------------------------------------------
 * 2018年5月31日 下午3:38:57          dell        4.1         To create
 * </p>
 *
 * @since
 * @see
 */
@XmlRootElement(name = "handle")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class ProvinceSynchroHandle extends ValidateParameter {
    /**
     *
     */
    private static final long serialVersionUID = -1931197458291145885L;
    /**
     * 主键ID
     */
    @XmlElement(name = "id")
    private Integer id;
    /**
     * 处理类型（发起、处理、改派关单等）
     */
    @XmlElement(name = "handleType")
    @ValidateString(required = true, maxLength = 10, description = "处理类型")
    private String handleType;
    /**
     * 工单处理人昵称
     */
    @XmlElement(name = "handleNick")
    @ValidateString(required = true, maxLength = 100, description = "工单处理人昵称")
    private String handleNick;
    /**
     * handle_account 工单处理人帐号
     */
    @XmlElement(name = "handleAccount")
    @ValidateString(required = true, maxLength = 100, description = "工单处理人帐号")
    private String handleAccount;
    /**
     *工单处理人邮箱
     */
    @XmlElement(name = "handleEmail")
    @ValidateString(required = true, maxLength = 200, description = "工单处理人邮箱")
    private String handleEmail;
    /**
     *工单处理人手机号码
     */
    @XmlElement(name = "handlePhone")
    @ValidateString(required = true, maxLength = 100, description = "工单处理人手机号码")
    private String handlePhone;
    /**
     *工单处理人所属组织机构
     */
    @XmlElement(name = "handleOrgName")
    @ValidateString(required = true, maxLength = 500, description = "工单处理人所属组织机构")
    private String handleOrgName;
    /**
     *  当前处理层级：地市、省分、总部
     */
    @XmlElement(name = "handleLevel")
    @ValidateString(required = true, maxLength = 100, description = " 当前处理层级")
    private String handleLevel;
    /**
     *工单处理开始时间，格式： yyyy-MM-dd HH:mm:ss
     */
    @XmlElement(name = "handleDate")
    @ValidateString(required = true, maxLength = 200, description = "工单处理结束时间")
    private String handleDate;
    /**
     *工单处理结束时间，格式：yyyy-MM-dd HH:mm:ss
     */
    @XmlElement(name = "endDate")
    @ValidateString(required = true, maxLength = 200, description = "工单处理结束时间")
    private String endDate;
    /**
     *此步骤处理时长，单位：分钟
     */
    @XmlElement(name = "handleTime")
    @ValidateInt(required = true, description = "此步骤处理时长", minValue = 0)
    private String handleTime;
    /**
     *工单处理说明
     */
    @XmlElement(name = "handleContent")
    @ValidateString(required = true, maxLength = 4000, description = "工单处理说明")
    private String handleContent;
    /**
        *省分工单编号
        */
    private String processNum;

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
     * @return the handleType
     */
    public String getHandleType() {
        return handleType;
    }

    /**
     * @param handleType the handleType to set
     */
    public void setHandleType(String handleType) {
        this.handleType = handleType;
    }

    /**
     * @return the handleNick
     */
    public String getHandleNick() {
        return handleNick;
    }

    /**
     * @param handleNick the handleNick to set
     */
    public void setHandleNick(String handleNick) {
        this.handleNick = handleNick;
    }

    /**
     * @return the handleAccount
     */
    public String getHandleAccount() {
        return handleAccount;
    }

    /**
     * @param handleAccount the handleAccount to set
     */
    public void setHandleAccount(String handleAccount) {
        this.handleAccount = handleAccount;
    }

    /**
     * @return the handleEmail
     */
    public String getHandleEmail() {
        return handleEmail;
    }

    /**
     * @param handleEmail the handleEmail to set
     */
    public void setHandleEmail(String handleEmail) {
        this.handleEmail = handleEmail;
    }

    /**
     * @return the handlePhone
     */
    public String getHandlePhone() {
        return handlePhone;
    }

    /**
     * @param handlePhone the handlePhone to set
     */
    public void setHandlePhone(String handlePhone) {
        this.handlePhone = handlePhone;
    }

    /**
     * @return the handleOrgName
     */
    public String getHandleOrgName() {
        return handleOrgName;
    }

    /**
     * @param handleOrgName the handleOrgName to set
     */
    public void setHandleOrgName(String handleOrgName) {
        this.handleOrgName = handleOrgName;
    }

    /**
     * @return the handleLevel
     */
    public String getHandleLevel() {
        return handleLevel;
    }

    /**
     * @param handleLevel the handleLevel to set
     */
    public void setHandleLevel(String handleLevel) {
        this.handleLevel = handleLevel;
    }

    /**
     * @return the handleDate
     */
    public String getHandleDate() {
        return handleDate;
    }

    /**
     * @param handleDate the handleDate to set
     */
    public void setHandleDate(String handleDate) {
        this.handleDate = handleDate;
    }

    /**
     * @return the endDate
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the handleTime
     */
    public String getHandleTime() {
        return handleTime;
    }

    /**
     * @param handleTime the handleTime to set
     */
    public void setHandleTime(String handleTime) {
        this.handleTime = handleTime;
    }

    /**
     * @return the handleContent
     */
    public String getHandleContent() {
        return handleContent;
    }

    /**
     * @param handleContent the handleContent to set
     */
    public void setHandleContent(String handleContent) {
        this.handleContent = handleContent;
    }

    /**
     * @return the processNum
     */
    public String getProcessNum() {
        return processNum;
    }

    /**
     * @param processNum the processNum to set
     */
    public void setProcessNum(String processNum) {
        this.processNum = processNum;
    }

}
