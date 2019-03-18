/*
 * FileName: ProvinceTest.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.bean;

import java.io.Serializable;

import com.ultrapower.viedo.viladate.ValidateString;

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
 * 2018年8月7日 下午4:36:42          dell        4.1         To create
 * </p>
 *
 * @since
 * @see
 */
public class ProvinceTest implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    //@NotNull(message = "{user.id.null}")
    //@ValidateString(required = true, message = "{user.id.null}")
    private Long id;

    @ValidateString(required = true, message = "{user.id.null}", description = "用户名")
    // @NotEmpty(message = "{user.name.null}")
    //@Length(min = 5, max = 20, message = "{user.name.length.illegal}")
    // @Pattern(regexp = "[a-zA-Z]{5,20}", message = "{user.name.illegal}")
    private String name;

    //@NotNull(message = "{user.password.null}")
    private String password;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
