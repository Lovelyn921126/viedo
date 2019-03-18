/*
 * FileName: CLogger.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.utils;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

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
 * 2018年4月24日 下午4:19:36          dell        4.1         To create
 * </p>
 *
 * @since
 * @see
 */
public class CLogger {

    private static String methodName = null;
    private static String className = null;

    public static Logger getLogger(String name) {
        return LoggerFactory.getLogger(name);
    }

    public static void error(String msg) {
        getLogger(getCurrentClass()).error(getCurrentMethod() + "====" + msg);
    }

    public static void error(String msg, Throwable e) {
        getLogger(getCurrentClass()).error(getCurrentMethod() + "====" + msg, e);
    }

    public static void info(String msg) {
        getLogger(getCurrentClass()).info(msg);
    }

    public static void info(String msg, Throwable e) {
        getLogger(getCurrentClass()).info(msg, e);
    }

    public static void info(Class<?> object, String msg, Throwable e) {
        String obj = JSON.toJSONString(object);
        info(obj + "*****msg====" + msg, e);
    }

    public static void error(Object object, String msg) {
        String obj = JSON.toJSONString(object);
        error(obj + "*****msg====" + msg);
    }

    public static void error(Object object, String msg, Throwable e) {
        String obj = JSON.toJSONString(object);
        error(obj + "*****msg====" + msg, e);
    }

    private static String getCurrentClass() {
        StackTraceElement[] elements = Thread.currentThread().getStackTrace();
        for (int i = 0; i < elements.length; i++) {
            StackTraceElement stackTraceElement = elements[i];
            if (stackTraceElement.getClassName().equals(CLogger.class.getName())) {
                if (!elements[i + 1].getClassName().equals(CLogger.class.getName())) {
                    className = elements[i + 1].getClassName();
                    methodName = elements[i + 1].getMethodName();
                }
            }

        }

        return className;
    }

    private static String getCurrentMethod() {
        return methodName;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> class1 = Class.forName("com.ultrapower.viedo.service.UserInfoService");
        Method[] methods = class1.getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().equals("getUserByUserName")) {
                Parameter[] parameters = method.getParameters();
                Class<?>[] pClass = method.getParameterTypes();
                for (Class<?> p : pClass) {
                    System.out.println(JSON.toJSON(p));
                }
                for (Parameter parameter : parameters) {
                    System.out.println(parameter.getName());
                }
            }
        }

    }

}
