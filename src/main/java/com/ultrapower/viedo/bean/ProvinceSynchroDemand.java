/*
 * FileName: ProvinceSynchroDemand.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.ultrapower.viedo.utils.DateFormet;
import com.ultrapower.viedo.viladate.ValidateDate;
import com.ultrapower.viedo.viladate.ValidateParameter;
import com.ultrapower.viedo.viladate.ValidateString;

/**
 * <p>
 * Description: 省分同步工单
 * </p>
 *
 * @author dell
 * @version 4.1

 * <p>
 * History:
 *
 * Date                     Author         Version     Description
 * ---------------------------------------------------------------------------------
 * 2018年5月31日 下午3:25:31          dell        4.1         To create
 * </p>
 *
 * @since
 * @see
 */

@XmlRootElement(name = "process")
@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
public class ProvinceSynchroDemand extends ValidateParameter {
    /**
     *
     */
    private static final long serialVersionUID = -4938109463344767810L;
    /**
     * 主键
     */
    @XmlElement(name = "id")
    private String id;
    /**
     *    '省分工单编号',
     */
    @XmlElement(name = "processNum")
    @ValidateString(required = true, maxLength = 40, description = "省分工单编号", startWith = true)
    private String processNum;
    /**
     *   '省分工单标题',
     */
    @XmlElement(name = "title")
    @ValidateString(required = true, maxLength = 1000, description = "省分工单标题")
    private String title;
    /**
     *    '省分工单描述',
     */
    @XmlElement(name = "content")
    @ValidateString(required = true, maxLength = 4000, description = "省分工单描述")
    private String content;
    /**
     *   '省分编码',
     */
    @XmlElement(name = "provinceCode")
    @ValidateString(required = true, maxLength = 20, description = "省分编码")
    private String provinceCode;
    /**
     *   '省分名称',
     */
    @XmlElement(name = "provinceName")
    @ValidateString(required = true, maxLength = 20, description = "省分名称")
    private String provinceName;
    /**
     *    '工单所属专题',
     */
    @XmlElement(name = "systypeSuper")
    @ValidateString(required = true, maxLength = 400, description = "工单所属专题")
    private String systypeSuper;
    /**
     *    '工单所属一级分类',
     */
    @XmlElement(name = "systype1")
    @ValidateString(required = true, maxLength = 400, description = "工单所属一级分类")
    private String systype1;
    /**
     *   '工单所属二级分类',
     */
    @XmlElement(name = "systype2")
    @ValidateString(required = false, maxLength = 400, description = "工单所属二级分类")
    private String systype2;
    /**
     *   '工单所属三级分类',
     */
    @XmlElement(name = "systype3")
    @ValidateString(required = false, maxLength = 400, description = "工单所属三级分类")
    private String systype3;
    /**
     *    '工单提出时间，格式：yyyy-MM-dd HH:mm:ss',
     */
    @XmlElement(name = "createDate")
    //@ValidateString(required = true, maxLength = 200, description = "工单提出时间")
    @ValidateDate(required = true, dateFormat = DateFormet.YY_MM_DD_HH_MM_SS, description = "工单提出时间")
    private String createDate;
    /**
     *   '工单发起人昵称',
     */
    @XmlElement(name = "creatorNick")
    @ValidateString(required = true, maxLength = 100, description = "工单发起人昵称")
    private String creatorNick;
    /**
     *   '工单发起人帐号',
     */
    @XmlElement(name = "creatorAccount")
    @ValidateString(required = true, maxLength = 100, description = "工单发起人帐号")
    private String creatorAccount;
    /**
     *   '工单发起人邮箱',
     */
    @XmlElement(name = "createEmail")
    @ValidateString(required = true, maxLength = 200, description = "工单发起人邮箱")
    private String createEmail;
    /**
     *   '工单发起人手机号码',
     */
    @XmlElement(name = "createPhone")
    @ValidateString(required = true, maxLength = 100, description = "工单发起人手机号码")
    private String createPhone;
    /**
     *   '工单发起人所属组织机构',
     */
    @XmlElement(name = "createOrgName")
    @ValidateString(required = true, maxLength = 500, description = "工单发起人所属组织机构")
    private String createOrgName;
    /**
     *   '工单所属地市编码',
     */
    @XmlElement(name = "cityCode")
    @ValidateString(required = true, maxLength = 100, description = "工单所属地市编码")
    private String cityCode;
    /**
     *    '工单所属地市名称',
     */
    @XmlElement(name = "cityName")
    @ValidateString(required = true, maxLength = 100, description = "工单所属地市名称")
    private String cityName;
    /**
     *   '管理类工单申请处理层级',
     */
    @XmlElement(name = "managerDemandLevel")
    @ValidateString(required = true, maxLength = 100, description = "管理类工单申请处理层级")
    private String managerDemandLevel;
    /**
     *    '当前任务',
     */
    @XmlElement(name = "currentTask")
    @ValidateString(required = true, maxLength = 100, description = "当前任务")
    private String currentTask;
    /**
     *    '当前处理人名称',
     */
    @XmlElement(name = "currentHandleNick")
    @ValidateString(required = true, maxLength = 100, description = "当前处理人名称")
    private String currentHandleNick;
    /**
     *    '当前处理人帐号',
     */
    @XmlElement(name = "currentHandleAccount")
    @ValidateString(required = true, maxLength = 100, description = "当前处理人帐号")
    private String currentHandleAccount;
    /**
     *    '当前处理人邮箱',
     */
    @XmlElement(name = "currentHandleEmail")
    @ValidateString(required = true, maxLength = 200, description = "当前处理人邮箱")
    private String currentHandleEmail;
    /**
     *   '当前处理人电话',
     */
    @XmlElement(name = "currentHandlePhone")
    @ValidateString(required = true, maxLength = 200, description = "当前处理人电话")
    private String currentHandlePhone;
    /**
     *   '当前处理人组织机构',
     */
    @XmlElement(name = "currentHandleOrgName")
    @ValidateString(required = true, maxLength = 1000, description = "当前处理人组织机构")
    private String currentHandleOrgName;
    /**
     *   '当前处理人层级',
     */
    @XmlElement(name = "currenthandleLevel")
    @ValidateString(required = true, maxLength = 100, description = "当前处理人层级")
    private String currentHandleLevel;
    /**
     *    '到达当前环节时间；格式：yyyy-MM-dd HH:mm:ss',
     */
    @XmlElement(name = "currenthandleStartDate")
    //@ValidateString(required = true, maxLength = 100, description = "到达当前环节时间")
    @ValidateDate(required = true, dateFormat = DateFormet.YY_MM_DD_HH_MM_SS, description = "到达当前环节时间")
    private String currenthandleStartDate;
    /**
     *    '工单处理总时长；单位：分钟',
     */
    @XmlElement(name = "handleAllTime")
    @ValidateString(required = true, maxLength = 100, description = "工单处理总时长")
    private String handleAllTime;
    /**
     *是否关闭 0 未关闭 1 已关闭
     */
    @XmlElement(name = "isColse")
    @ValidateString(required = true, maxLength = 100, description = "是否关闭")
    private String isColse;
    /**
     *   '满意度；数据范围：3~15',
     */
    @XmlElement(name = "staisfyDegree")
    @ValidateString(required = true, maxLength = 100, description = "满意度")
    private String staisfyDegree;
    /**
     *   '是否超时；0，未超时、1，已超时',
     */
    @XmlElement(name = "isOverTime")
    @ValidateString(required = true, maxLength = 10, description = "是否超时")
    private String isOverTime;
    /**
     *   '超时时长',
     */
    @XmlElement(name = "overTime")
    @ValidateString(required = true, maxLength = 10, description = "超时时长")
    private String overTime;
    /**
     *   '反馈确认-问题情况说明',
     */
    @XmlElement(name = "feedBackContent")
    @ValidateString(required = true, maxLength = 4000, description = "反馈确认-问题情况说明")
    private String feedBackContent;
    /*
     *   '工单是否升级总部处理；0、未升级总部；1、升级总部',
     */
    @XmlElement(name = "isUpgrade")
    @ValidateString(required = true, maxLength = 10, description = "工单是否升级总部处理")
    private String isUpgrade;
    /**
     *   '工单升级总部时间，格式：yyyy-MM-dd HH:mm:ss；要求：如果isUpgrade字段为1，此字段必填；否则，不用填写',
     */
    @XmlElement(name = "upgradeDate")
    @ValidateDate(required = false, dateFormat = DateFormet.YY_MM_DD_HH_MM_SS, description = "工单升级总部时间")
    // @ValidateString(required = false, maxLength = 200, description = "工单升级总部时间")
    private String upgradeDate;
    /**
     *   '工单升级描述 要求：如果isUpgrade字段为1，此字段必填；否则，不用填写',
     */
    @XmlElement(name = "upgradeContent")
    @ValidateString(required = false, maxLength = 4000, description = "工单升级描述")
    private String upgradeContent;
    /*
     *   '升级人昵称 要求：如果isUpgrade字段为1，此字段必填；否则，不用填写',
     */
    @XmlElement(name = "upgradeNick")
    @ValidateString(required = false, maxLength = 100, description = "升级人昵称")
    private String upgradeNick;
    /**
     *   '升级人帐号 要求：如果isUpgrade字段为1，此字段必填；否则，不用填写',
     */
    @XmlElement(name = "upgradeAccount")
    @ValidateString(required = false, maxLength = 100, description = "升级人帐号")
    private String upgradeAccount;
    /**
     *    '升级人邮箱 要求：如果isUpgrade字段为1，此字段必填；否则，不用填写',
     */
    @XmlElement(name = "upgradeEmail")
    @ValidateString(required = false, maxLength = 200, description = "升级人邮箱 要求")
    private String upgradeEmail;
    /**
     *   '升级人手机号码 要求：如果isUpgrade字段为1，此字段必填；否则，不用填写',
     */
    @XmlElement(name = "upgradePhone")
    @ValidateString(required = false, maxLength = 200, description = "升级人手机号码")
    private String upgradePhone;
    /**
     *   '升级人组织机构 要求：如果isUpgrade字段为1，此字段必填；否则，不用填写',
     */
    @XmlElement(name = "upgradeOrgName")
    @ValidateString(required = false, maxLength = 1000, description = "升级人组织机构")
    private String upgradeOrgName;
    /**
     *   '升级回复内容；要求：如果isUpgrade字段为1，此字段必填；否则，不用填写',
     */
    @XmlElement(name = "upgradeReplyContent")
    @ValidateString(required = false, maxLength = 4000, description = "升级回复内容")
    private String upgradeReplyContent;
    /**
     *   '升级回复时间  要求：如果isUpgrade字段为1，此字段必填；否则，不用填写',
     */
    @XmlElement(name = "upgradeReplyDate")
    @ValidateDate(required = false, dateFormat = DateFormet.YY_MM_DD_HH_MM_SS, description = "升级回复时间")
    //@ValidateString(required = false, maxLength = 200, description = "升级回复时间 ")
    private String upgradeReplyDate;
    /**
     * 处理记录
     */
    @XmlElementWrapper(name = "handleList")
    @XmlElement(name = "handle")
    private List<ProvinceSynchroHandle> handleList;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
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

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the provinceCode
     */
    @Override
    public String getProvinceCode() {
        return provinceCode;
    }

    /**
     * @param provinceCode the provinceCode to set
     */
    @Override
    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
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
     * @return the systypeSuper
     */
    public String getSystypeSuper() {
        return systypeSuper;
    }

    /**
     * @param systypeSuper the systypeSuper to set
     */
    public void setSystypeSuper(String systypeSuper) {
        this.systypeSuper = systypeSuper;
    }

    /**
     * @return the systype1
     */
    public String getSystype1() {
        return systype1;
    }

    /**
     * @param systype1 the systype1 to set
     */
    public void setSystype1(String systype1) {
        this.systype1 = systype1;
    }

    /**
     * @return the systype2
     */
    public String getSystype2() {
        return systype2;
    }

    /**
     * @param systype2 the systype2 to set
     */
    public void setSystype2(String systype2) {
        this.systype2 = systype2;
    }

    /**
     * @return the systype3
     */
    public String getSystype3() {
        return systype3;
    }

    /**
     * @param systype3 the systype3 to set
     */
    public void setSystype3(String systype3) {
        this.systype3 = systype3;
    }

    /**
     * @return the createDate
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate the createDate to set
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    /**
     * @return the creatorNick
     */
    public String getCreatorNick() {
        return creatorNick;
    }

    /**
     * @param creatorNick the creatorNick to set
     */
    public void setCreatorNick(String creatorNick) {
        this.creatorNick = creatorNick;
    }

    /**
     * @return the creatorAccount
     */
    public String getCreatorAccount() {
        return creatorAccount;
    }

    /**
     * @param creatorAccount the creatorAccount to set
     */
    public void setCreatorAccount(String creatorAccount) {
        this.creatorAccount = creatorAccount;
    }

    /**
     * @return the createEmail
     */
    public String getCreateEmail() {
        return createEmail;
    }

    /**
     * @param createEmail the createEmail to set
     */
    public void setCreateEmail(String createEmail) {
        this.createEmail = createEmail;
    }

    /**
     * @return the createPhone
     */
    public String getCreatePhone() {
        return createPhone;
    }

    /**
     * @param createPhone the createPhone to set
     */
    public void setCreatePhone(String createPhone) {
        this.createPhone = createPhone;
    }

    /**
     * @return the createOrgName
     */
    public String getCreateOrgName() {
        return createOrgName;
    }

    /**
     * @param createOrgName the createOrgName to set
     */
    public void setCreateOrgName(String createOrgName) {
        this.createOrgName = createOrgName;
    }

    /**
     * @return the cityCode
     */
    public String getCityCode() {
        return cityCode;
    }

    /**
     * @param cityCode the cityCode to set
     */
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    /**
     * @return the cityName
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * @param cityName the cityName to set
     */
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    /**
     * @return the managerDemandLevel
     */
    public String getManagerDemandLevel() {
        return managerDemandLevel;
    }

    /**
     * @param managerDemandLevel the managerDemandLevel to set
     */
    public void setManagerDemandLevel(String managerDemandLevel) {
        this.managerDemandLevel = managerDemandLevel;
    }

    /**
     * @return the currentTask
     */
    public String getCurrentTask() {
        return currentTask;
    }

    /**
     * @param currentTask the currentTask to set
     */
    public void setCurrentTask(String currentTask) {
        this.currentTask = currentTask;
    }

    /**
     * @return the currentHandleNick
     */
    public String getCurrentHandleNick() {
        return currentHandleNick;
    }

    /**
     * @param currentHandleNick the currentHandleNick to set
     */
    public void setCurrentHandleNick(String currentHandleNick) {
        this.currentHandleNick = currentHandleNick;
    }

    /**
     * @return the currentHandleAccount
     */
    public String getCurrentHandleAccount() {
        return currentHandleAccount;
    }

    /**
     * @param currentHandleAccount the currentHandleAccount to set
     */
    public void setCurrentHandleAccount(String currentHandleAccount) {
        this.currentHandleAccount = currentHandleAccount;
    }

    /**
     * @return the currentHandleEmail
     */
    public String getCurrentHandleEmail() {
        return currentHandleEmail;
    }

    /**
     * @param currentHandleEmail the currentHandleEmail to set
     */
    public void setCurrentHandleEmail(String currentHandleEmail) {
        this.currentHandleEmail = currentHandleEmail;
    }

    /**
     * @return the currentHandlePhone
     */
    public String getCurrentHandlePhone() {
        return currentHandlePhone;
    }

    /**
     * @param currentHandlePhone the currentHandlePhone to set
     */
    public void setCurrentHandlePhone(String currentHandlePhone) {
        this.currentHandlePhone = currentHandlePhone;
    }

    /**
     * @return the currentHandleOrgName
     */
    public String getCurrentHandleOrgName() {
        return currentHandleOrgName;
    }

    /**
     * @param currentHandleOrgName the currentHandleOrgName to set
     */
    public void setCurrentHandleOrgName(String currentHandleOrgName) {
        this.currentHandleOrgName = currentHandleOrgName;
    }

    /**
     * @return the currentHandleLevel
     */
    public String getCurrentHandleLevel() {
        return currentHandleLevel;
    }

    /**
     * @param currentHandleLevel the currentHandleLevel to set
     */
    public void setCurrentHandleLevel(String currentHandleLevel) {
        this.currentHandleLevel = currentHandleLevel;
    }

    /**
     * @return the currentHandleStartDate
     */
    public String getCurrentHandleStartDate() {
        return currenthandleStartDate;
    }

    /**
     * @param currentHandleStartDate the currentHandleStartDate to set
     */
    public void setCurrentHandleStartDate(String currentHandleStartDate) {
        this.currenthandleStartDate = currentHandleStartDate;
    }

    /**
     * @return the handleAllTime
     */
    public String getHandleAllTime() {
        return handleAllTime;
    }

    /**
     * @param handleAllTime the handleAllTime to set
     */
    public void setHandleAllTime(String handleAllTime) {
        this.handleAllTime = handleAllTime;
    }

    /**
     * @return the staisfyDegree
     */
    public String getStaisfyDegree() {
        return staisfyDegree;
    }

    /**
     * @param staisfyDegree the staisfyDegree to set
     */
    public void setStaisfyDegree(String staisfyDegree) {
        this.staisfyDegree = staisfyDegree;
    }

    /**
     * @return the isOverTime
     */
    public String getIsOverTime() {
        return isOverTime;
    }

    /**
     * @param isOverTime the isOverTime to set
     */
    public void setIsOverTime(String isOverTime) {
        this.isOverTime = isOverTime;
    }

    /**
     * @return the overTime
     */
    public String getOverTime() {
        return overTime;
    }

    /**
     * @param overTime the overTime to set
     */
    public void setOverTime(String overTime) {
        this.overTime = overTime;
    }

    /**
     * @return the feedBackContent
     */
    public String getFeedBackContent() {
        return feedBackContent;
    }

    /**
     * @param feedBackContent the feedBackContent to set
     */
    public void setFeedBackContent(String feedBackContent) {
        this.feedBackContent = feedBackContent;
    }

    /**
     * @return the isUpgrade
     */
    public String getIsUpgrade() {
        return isUpgrade;
    }

    /**
     * @param isUpgrade the isUpgrade to set
     */
    public void setIsUpgrade(String isUpgrade) {
        this.isUpgrade = isUpgrade;
    }

    /**
     * @return the upgradeDate
     */
    public String getUpgradeDate() {
        return upgradeDate;
    }

    /**
     * @param upgradeDate the upgradeDate to set
     */
    public void setUpgradeDate(String upgradeDate) {
        this.upgradeDate = upgradeDate;
    }

    /**
     * @return the upgradeContent
     */
    public String getUpgradeContent() {
        return upgradeContent;
    }

    /**
     * @param upgradeContent the upgradeContent to set
     */
    public void setUpgradeContent(String upgradeContent) {
        this.upgradeContent = upgradeContent;
    }

    /**
     * @return the upgradeNick
     */
    public String getUpgradeNick() {
        return upgradeNick;
    }

    /**
     * @param upgradeNick the upgradeNick to set
     */
    public void setUpgradeNick(String upgradeNick) {
        this.upgradeNick = upgradeNick;
    }

    /**
     * @return the upgradeAccount
     */
    public String getUpgradeAccount() {
        return upgradeAccount;
    }

    /**
     * @param upgradeAccount the upgradeAccount to set
     */
    public void setUpgradeAccount(String upgradeAccount) {
        this.upgradeAccount = upgradeAccount;
    }

    /**
     * @return the upgradeEmail
     */
    public String getUpgradeEmail() {
        return upgradeEmail;
    }

    /**
     * @param upgradeEmail the upgradeEmail to set
     */
    public void setUpgradeEmail(String upgradeEmail) {
        this.upgradeEmail = upgradeEmail;
    }

    /**
     * @return the upgradePhone
     */
    public String getUpgradePhone() {
        return upgradePhone;
    }

    /**
     * @param upgradePhone the upgradePhone to set
     */
    public void setUpgradePhone(String upgradePhone) {
        this.upgradePhone = upgradePhone;
    }

    /**
     * @return the upgradeOrgName
     */
    public String getUpgradeOrgName() {
        return upgradeOrgName;
    }

    /**
     * @param upgradeOrgName the upgradeOrgName to set
     */
    public void setUpgradeOrgName(String upgradeOrgName) {
        this.upgradeOrgName = upgradeOrgName;
    }

    /**
     * @return the upgradeReplyContent
     */
    public String getUpgradeReplyContent() {
        return upgradeReplyContent;
    }

    /**
     * @param upgradeReplyContent the upgradeReplyContent to set
     */
    public void setUpgradeReplyContent(String upgradeReplyContent) {
        this.upgradeReplyContent = upgradeReplyContent;
    }

    /**
     * @return the upgradeReplyDate
     */
    public String getUpgradeReplyDate() {
        return upgradeReplyDate;
    }

    /**
     * @param upgradeReplyDate the upgradeReplyDate to set
     */
    public void setUpgradeReplyDate(String upgradeReplyDate) {
        this.upgradeReplyDate = upgradeReplyDate;
    }

    /**
     * @return the handleList
     */
    public List<ProvinceSynchroHandle> getHandleList() {
        return handleList;
    }

    /**
     * @param handleList the handleList to set
     */
    public void setHandleList(List<ProvinceSynchroHandle> handleList) {
        this.handleList = handleList;
    }
}
