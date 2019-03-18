/*
 * FileName: UserInfoCacheBean.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

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
 * 2017年12月29日 下午5:23:51          dell        4.1         To create
 * </p>
 *
 * @since
 * @see
 */
@ApiModel(value = "用户信息")
public class UserInfoCacheBean implements Serializable {
    private static final long serialVersionUID = -18933071520397356L;
    @ApiModelProperty(value = "用户ID")
    private Integer id;
    private String unicomId;
    private String userName;
    private String userPassword;
    private String userNick;
    private Short userType;
    private Short userGender;
    private Short userAge;
    private String userHeaderImage;
    private String userMidHeaderImage;
    private String userSmallHeaderImage;
    private String userEmail;
    private String userNationId;
    private String userProvinceId;
    private String userCityId;
    private Integer userCompanyId;
    private String userCompanyName;
    private Integer userPositionId;
    private String userPositionName;
    private Integer userExperience;
    private Integer userLevel;
    private Integer userIntegral;
    private Integer userMedalCount;
    private Integer isNewUser;
    private Short status;
    private String userInfoDepartmentName;
    private String userOrgCode;
    private Short userSource;
    private String userPhoneNum;
    private String userPassWordUltrapower;
    private Short userSpecialType;
    private String fullorgname; //组织机构全称
    private String department; //部门名称

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
     * @return the unicomId
     */
    public String getUnicomId() {
        return unicomId;
    }

    /**
     * @param unicomId the unicomId to set
     */
    public void setUnicomId(String unicomId) {
        this.unicomId = unicomId;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the userPassword
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * @param userPassword the userPassword to set
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    /**
     * @return the userNick
     */
    public String getUserNick() {
        return userNick;
    }

    /**
     * @param userNick the userNick to set
     */
    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }

    /**
     * @return the userType
     */
    public Short getUserType() {
        return userType;
    }

    /**
     * @param userType the userType to set
     */
    public void setUserType(Short userType) {
        this.userType = userType;
    }

    /**
     * @return the userGender
     */
    public Short getUserGender() {
        return userGender;
    }

    /**
     * @param userGender the userGender to set
     */
    public void setUserGender(Short userGender) {
        this.userGender = userGender;
    }

    /**
     * @return the userAge
     */
    public Short getUserAge() {
        return userAge;
    }

    /**
     * @param userAge the userAge to set
     */
    public void setUserAge(Short userAge) {
        this.userAge = userAge;
    }

    /**
     * @return the userHeaderImage
     */
    public String getUserHeaderImage() {
        return userHeaderImage;
    }

    /**
     * @param userHeaderImage the userHeaderImage to set
     */
    public void setUserHeaderImage(String userHeaderImage) {
        this.userHeaderImage = userHeaderImage;
    }

    /**
     * @return the userMidHeaderImage
     */
    public String getUserMidHeaderImage() {
        return userMidHeaderImage;
    }

    /**
     * @param userMidHeaderImage the userMidHeaderImage to set
     */
    public void setUserMidHeaderImage(String userMidHeaderImage) {
        this.userMidHeaderImage = userMidHeaderImage;
    }

    /**
     * @return the userSmallHeaderImage
     */
    public String getUserSmallHeaderImage() {
        return userSmallHeaderImage;
    }

    /**
     * @param userSmallHeaderImage the userSmallHeaderImage to set
     */
    public void setUserSmallHeaderImage(String userSmallHeaderImage) {
        this.userSmallHeaderImage = userSmallHeaderImage;
    }

    /**
     * @return the userEmail
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * @param userEmail the userEmail to set
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    /**
     * @return the userNationId
     */
    public String getUserNationId() {
        return userNationId;
    }

    /**
     * @param userNationId the userNationId to set
     */
    public void setUserNationId(String userNationId) {
        this.userNationId = userNationId;
    }

    /**
     * @return the userProvinceId
     */
    public String getUserProvinceId() {
        return userProvinceId;
    }

    /**
     * @param userProvinceId the userProvinceId to set
     */
    public void setUserProvinceId(String userProvinceId) {
        this.userProvinceId = userProvinceId;
    }

    /**
     * @return the userCityId
     */
    public String getUserCityId() {
        return userCityId;
    }

    /**
     * @param userCityId the userCityId to set
     */
    public void setUserCityId(String userCityId) {
        this.userCityId = userCityId;
    }

    /**
     * @return the userCompanyId
     */
    public Integer getUserCompanyId() {
        return userCompanyId;
    }

    /**
     * @param userCompanyId the userCompanyId to set
     */
    public void setUserCompanyId(Integer userCompanyId) {
        this.userCompanyId = userCompanyId;
    }

    /**
     * @return the userCompanyName
     */
    public String getUserCompanyName() {
        return userCompanyName;
    }

    /**
     * @param userCompanyName the userCompanyName to set
     */
    public void setUserCompanyName(String userCompanyName) {
        this.userCompanyName = userCompanyName;
    }

    /**
     * @return the userPositionId
     */
    public Integer getUserPositionId() {
        return userPositionId;
    }

    /**
     * @param userPositionId the userPositionId to set
     */
    public void setUserPositionId(Integer userPositionId) {
        this.userPositionId = userPositionId;
    }

    /**
     * @return the userPositionName
     */
    public String getUserPositionName() {
        return userPositionName;
    }

    /**
     * @param userPositionName the userPositionName to set
     */
    public void setUserPositionName(String userPositionName) {
        this.userPositionName = userPositionName;
    }

    /**
     * @return the userExperience
     */
    public Integer getUserExperience() {
        return userExperience;
    }

    /**
     * @param userExperience the userExperience to set
     */
    public void setUserExperience(Integer userExperience) {
        this.userExperience = userExperience;
    }

    /**
     * @return the userLevel
     */
    public Integer getUserLevel() {
        return userLevel;
    }

    /**
     * @param userLevel the userLevel to set
     */
    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
    }

    /**
     * @return the userIntegral
     */
    public Integer getUserIntegral() {
        return userIntegral;
    }

    /**
     * @param userIntegral the userIntegral to set
     */
    public void setUserIntegral(Integer userIntegral) {
        this.userIntegral = userIntegral;
    }

    /**
     * @return the userMedalCount
     */
    public Integer getUserMedalCount() {
        return userMedalCount;
    }

    /**
     * @param userMedalCount the userMedalCount to set
     */
    public void setUserMedalCount(Integer userMedalCount) {
        this.userMedalCount = userMedalCount;
    }

    /**
     * @return the isNewUser
     */
    public Integer getIsNewUser() {
        return isNewUser;
    }

    /**
     * @param isNewUser the isNewUser to set
     */
    public void setIsNewUser(Integer isNewUser) {
        this.isNewUser = isNewUser;
    }

    /**
     * @return the status
     */
    public Short getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Short status) {
        this.status = status;
    }

    /**
     * @return the userInfoDepartmentName
     */
    public String getUserInfoDepartmentName() {
        return userInfoDepartmentName;
    }

    /**
     * @param userInfoDepartmentName the userInfoDepartmentName to set
     */
    public void setUserInfoDepartmentName(String userInfoDepartmentName) {
        this.userInfoDepartmentName = userInfoDepartmentName;
    }

    /**
     * @return the userOrgCode
     */
    public String getUserOrgCode() {
        return userOrgCode;
    }

    /**
     * @param userOrgCode the userOrgCode to set
     */
    public void setUserOrgCode(String userOrgCode) {
        this.userOrgCode = userOrgCode;
    }

    /**
     * @return the userSource
     */
    public Short getUserSource() {
        return userSource;
    }

    /**
     * @param userSource the userSource to set
     */
    public void setUserSource(Short userSource) {
        this.userSource = userSource;
    }

    /**
     * @return the userPhoneNum
     */
    public String getUserPhoneNum() {
        return userPhoneNum;
    }

    /**
     * @param userPhoneNum the userPhoneNum to set
     */
    public void setUserPhoneNum(String userPhoneNum) {
        this.userPhoneNum = userPhoneNum;
    }

    /**
     * @return the userPassWordUltrapower
     */
    public String getUserPassWordUltrapower() {
        return userPassWordUltrapower;
    }

    /**
     * @param userPassWordUltrapower the userPassWordUltrapower to set
     */
    public void setUserPassWordUltrapower(String userPassWordUltrapower) {
        this.userPassWordUltrapower = userPassWordUltrapower;
    }

    /**
     * @return the userSpecialType
     */
    public Short getUserSpecialType() {
        return userSpecialType;
    }

    /**
     * @param userSpecialType the userSpecialType to set
     */
    public void setUserSpecialType(Short userSpecialType) {
        this.userSpecialType = userSpecialType;
    }

    /**
     * @return the fullorgname
     */
    public String getFullorgname() {
        return fullorgname;
    }

    /**
     * @param fullorgname the fullorgname to set
     */
    public void setFullorgname(String fullorgname) {
        this.fullorgname = fullorgname;
    }

    /**
     * @return the department
     */
    public String getDepartment() {
        return department;
    }

    /**
     * @param department the department to set
     */
    public void setDepartment(String department) {
        this.department = department;
    }

}
