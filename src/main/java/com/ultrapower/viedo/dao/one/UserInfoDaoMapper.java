/*
 * FileName: UserInfoDaoMapper.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.dao.one;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ultrapower.viedo.bean.UserInfo;
import com.ultrapower.viedo.vo.UserInfoCacheBean;
import com.ultrapower.viedo.vo.UserParentOrganization;

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
 * 2017年12月29日 下午5:13:19          dell        4.1         To create
 * </p>
 *
 * @since
 * @see
 */
@Component
public interface UserInfoDaoMapper {
    //getUserNameByUserId
    public String getUserNameByUserId(String id);

    public List<UserInfoCacheBean> getUserInfoById(String id);

    /**通过用户名获取用户信息
     * @param userName
     * @return
     */
    public UserInfo getUserByUserName(String userName);

    /**通过用户ID 获取用户省分信息
     * @param id
     * @return
     */
    public UserParentOrganization getParentOrganization(Integer id);

    /**
     * @param id
     * @return
     */
    public int updateUserInfo();

}
