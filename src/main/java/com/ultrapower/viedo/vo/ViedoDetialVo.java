/*
 * FileName: ViedoDetialVo.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.vo;

import com.ultrapower.viedo.bean.ViedoDetial;

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
 * 2017年11月30日 上午10:04:46          dell        4.1         To create
 * </p>
 *
 * @since 
 * @see     
 */
public class ViedoDetialVo extends ViedoDetial {
    /**
     * 
     */
    private static final long serialVersionUID = 5183932374920684545L;
    /**
     * 用户昵称
     */
    private String userNick;
    /**
     * 用户部门
     */
    private String userOrg;
    /**
     * 电话
     */
    private String phone;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 用户头像
     */
    private String userImg;

    /**
     * @return the userImg
     */
    public String getUserImg() {
        return userImg;
    }

    /**
     * @param userImg the userImg to set
     */
    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    /**
     * @return the userorg
     */
    public String getUserOrg() {
        return userOrg;
    }

    /**
     * @param userorg the userorg to set
     */
    public void setUserOrg(String userorg) {
        this.userOrg = userorg;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
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

}
