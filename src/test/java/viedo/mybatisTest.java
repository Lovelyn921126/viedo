/*
 * FileName: mybatisTest.java
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
 * 2018年5月14日 上午10:13:19          dell        4.1         To create
 * </p>
 *
 * @since
 * @see
 */

public class mybatisTest {
    /*  @Test
    public void test() {
        String resource = "viedo/mybatis-config.xml";
        InputStream is = mybatisTest.class.getClassLoader().getResourceAsStream(resource);
        //构建sqlSession的工厂
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession session = sessionFactory.openSession();
        String statement = "com.ultrapower.viedo.dao.TeacherTestDaoMapper.getTeacher";
        Teacher t = (Teacher) session.selectList(statement, 2).get(0);
        session.commit();
        System.out.println(t.getName());
        // List<User> users = t.getUser();
        // System.out.println(users.get(0).getNames());
    
    }
    
    public static void main(String[] args) {
        String resource = "viedo/mybatis-config.xml";
        InputStream is = mybatisTest.class.getClassLoader().getResourceAsStream(resource);
        //构建sqlSession的工厂
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession session = sessionFactory.openSession();
        String statement = "com.ultrapower.viedo.dao.UserTestDaoMapper.getById";
        User user = session.selectOne(statement, 2);
        session.commit();
        System.out.println(user.getNames());
        // Teacher teacher = user.getTeacher();
        // System.err.println(teacher.getName());
    
    }*/
}
