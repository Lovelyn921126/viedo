package com.ultrapower.viedo.utils;
/*
 * FileName: RegexType.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */

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
 * 2018年6月27日 下午1:50:56          dell        4.1         To create
 * </p>
 *
 * @since
 * @see
 */
public enum RegexType {
    /**
    * 没有
    */
    NONE,
    /* *//**
         * 字符串截取，1个中文当3个字符（超过部分 用省略号代替）
         *//*
           SPECIALCHAR,*/
    /**
     *是不是全漢字
     */
    CHINESE,
    /**
     *适应CJK（中日韩）字符集，部分中日韩的字是一样的
     */
    CHINESE2,
    /**
     *判断是否有汉字
     */
    ContainChinese,
    /**
     *判断是否是正确的邮箱地址
     */
    EMAIL,
    /**
     *判断是否是正确的IP地址
     */
    IP,
    /* *//**
         *判断是否正整数
         */
    /*
    NUMBER,*/
    /*    *//**
            * 字符串截取，1个中文当3个字符  不补省略号
            *//*
              NO_SPECIALCHAR,*/
    /**
     *判断是否是手机号码
     */
    PHONENUMBER
}
