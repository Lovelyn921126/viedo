/*
 * FileName: User.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package viedo;

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
 * 2018年5月14日 上午10:14:13          dell        4.1         To create
 * </p>
 *
 * @since
 * @see
 */
public class User {
    private int id;
    private String names;
    private int age;
    // private Teacher teacher;

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
     * @return the names
     */
    public String getNames() {
        return names;
    }

    /**
     * @param names the names to set
     */
    public void setNames(String names) {
        this.names = names;
    }

    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

    /* *//**
         * @return the teacher
         */
    /*
    public Teacher getTeacher() {
     return teacher;
    }
    
    *//**
      * @param teacher the teacher to set
      *//*
        public void setTeacher(Teacher teacher) {
         this.teacher = teacher;
        }*/
}
