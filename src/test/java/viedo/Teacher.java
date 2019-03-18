/*
 * FileName: Teacher.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package viedo;

import java.util.List;

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
 * 2018年5月14日 上午10:14:27          dell        4.1         To create
 * </p>
 *
 * @since
 * @see
 */
public class Teacher {
    private int id;
    private String name;

    private List<User> user;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
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
     * @return the user
     */
    public List<User> getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(List<User> user) {
        this.user = user;
    }

}
