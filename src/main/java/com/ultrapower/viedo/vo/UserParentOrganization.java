/*
 * FileName: UserParentOrganization.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.vo;

/**
 * <p>
 * Description: 
 * </p>
 *
 * @author Administrator
 * @version 4.1

 * <p>
 * History: 
 *
 * Date                     Author         Version     Description
 * ---------------------------------------------------------------------------------
 * 2016-5-30 下午05:37:52          wenzheng        4.1         To create
 * </p>
 *
 * @since 
 * @see     
 */
public class UserParentOrganization {

    private int userId;
    private int userProvinceId;
    private int userCityId;
    private String userProvinceName;
    private String userCityName;

    public UserParentOrganization() {

    }

    public UserParentOrganization(int userId, int userProvinceId, int userCityId, String userProvinceName, String userCityName) {
        super();
        this.userId = userId;
        this.userProvinceId = userProvinceId;
        this.userCityId = userCityId;
        this.userProvinceName = userProvinceName;
        this.userCityName = userCityName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserProvinceId() {
        return userProvinceId;
    }

    public void setUserProvinceId(int userProvinceId) {
        this.userProvinceId = userProvinceId;
    }

    public int getUserCityId() {
        return userCityId;
    }

    public void setUserCityId(int userCityId) {
        this.userCityId = userCityId;
    }

    public String getUserProvinceName() {
        return userProvinceName;
    }

    public void setUserProvinceName(String userProvinceName) {
        this.userProvinceName = userProvinceName;
    }

    public String getUserCityName() {
        return userCityName;
    }

    public void setUserCityName(String userCityName) {
        this.userCityName = userCityName;
    }

}
