/*
 * FileName: test.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.utils;

import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Component;

import com.ultrapower.viedo.utils.mybatis.DataSourceHolder;

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
 * 2018年4月4日 下午5:27:35          dell        4.1         To create
 * </p>
 *
 * @since
 * @see
 */
@Component
public class test {
    /* public static void main(String[] args) throws Exception {
        ClassPool pool = ClassPool.getDefault(); //2.创建类
        CtClass ctClass = pool.makeClass("com.earnest.Emp");
        //3.创建属性
        CtField name = CtField.make("private String name;", ctClass);
        //属性定义 对象
        CtField age = CtField.make("private int age;", ctClass);
        //4.添加属性
        ctClass.addField(name);
        ctClass.addField(age);
        //5.创建方法
        CtMethod make = CtMethod.make("public String getName() {return name;}", ctClass);
        CtMethod make1 = CtMethod.make("public void setName(String name) {this.name = name;}", ctClass);
        //6.添加方法
        ctClass.addMethod(make);
        ctClass.addMethod(make1);
        //7.添加构造器
        CtConstructor ctConstructor = new CtConstructor(new CtClass[] {}, ctClass);
        //无参数构造器
        ctConstructor.setBody("{}");
        CtConstructor ctConstructor1 = new CtConstructor(new CtClass[] { CtClass.intType, pool.get("java.lang.String") }, ctClass);
        //指定参数构造器
        ctConstructor1.setBody("{this.name=$2;this.age=$1;}");
        //$1代表第一个参数，$2代表第二个参数，$0代表this
        //8.添加构造器
        ctClass.addConstructor(ctConstructor);
        ctClass.addConstructor(ctConstructor1);
        //8.写入工作空间c:/myJava
        ctClass.writeFile("c:/myJava");

    }

    public void performance(JoinPoint joinPoint) {
        System.out.println("Spring AOP");
        System.out.println(TransactionSynchronizationManager.isSynchronizationActive());
        System.out.println(joinPoint.getSignature().getName());
        String name = joinPoint.getSignature().getName();
        if (name.startsWith("get")) {
            DataSourceHolder.setMaster();
        }
        if (name.startsWith("update")) {
            DataSourceHolder.setSlave();

        }
    }
    */
    public static void main(String[] args) {
        /* // ClassPool：CtClass对象的容器
        ClassPool pool = ClassPool.getDefault();
        
        // 通过ClassPool生成一个public新类Employee.java
        CtClass ctClass = pool.makeClass("com.vvvtimes.bean.Employee");
        
        // 添加字段
        // 首先添加字段private String ename
        CtField enameField = new CtField(pool.getCtClass("java.lang.String"), "ename", ctClass);
        enameField.setModifiers(Modifier.PRIVATE);
        ctClass.addField(enameField);
        
        // 其次添加字段privtae int eage
        CtField eageField = new CtField(pool.getCtClass("int"), "eage", ctClass);
        eageField.setModifiers(Modifier.PRIVATE);
        ctClass.addField(eageField);
        
        // 其次添加字段privtae int eage
        CtField esexField = new CtField(pool.getCtClass("int"), "esex", ctClass);
        esexField.setModifiers(Modifier.PRIVATE);
        ctClass.addField(esexField);
        
        // 为字段ename和eno添加getter和setter方法
        ctClass.addMethod(CtNewMethod.getter("getEname", enameField));
        ctClass.addMethod(CtNewMethod.setter("setEname", enameField));
        ctClass.addMethod(CtNewMethod.getter("getEage", eageField));
        ctClass.addMethod(CtNewMethod.setter("setEage", eageField));
        ctClass.addMethod(CtNewMethod.getter("getSex", esexField));
        ctClass.addMethod(CtNewMethod.setter("setSex", esexField));
        
        // 添加构造函数
        CtConstructor ctConstructor = new CtConstructor(new CtClass[] {}, ctClass);
        // 为构造函数设置函数体
        StringBuffer buffer = new StringBuffer();
        buffer.append("{\n").append("ename=\"gsls200808\";\n").append("eage=25;\n").append("esex=1;\n}");
        ctConstructor.setBody(buffer.toString());
        // 把构造函数添加到新的类中
        ctClass.addConstructor(ctConstructor);
        
        // 添加自定义方法
        CtMethod ctMethod = new CtMethod(CtClass.voidType, "printInfo", new CtClass[] {}, ctClass);
        // 为自定义方法设置修饰符
        ctMethod.setModifiers(Modifier.PUBLIC);
        // 为自定义方法设置函数体
        StringBuffer buffer2 = new StringBuffer();
        buffer2.append("{\n").append("System.out.println(\"begin!\");\n").append("System.out.println(\"name=\"+ename);\n").append("System.out.println(\"age=\"+eage);\n").append("System.out.println(\"sex=\"+esex);\n").append("System.out.println(\"end!\");\n").append("}");
        ctMethod.setBody(buffer2.toString());
        ctClass.addMethod(ctMethod);
        
        // 为了验证效果，下面使用反射执行方法printInfo
        Class<?> clazz = ctClass.toClass();
        Object obj = clazz.newInstance();
        obj.getClass().getMethod("printInfo", new Class[] {}).invoke(obj, new Object[] {});*/
    }

    public void performance1(JoinPoint joinPoint) {
        System.out.println("clean");
        DataSourceHolder.clean();
    }
}
