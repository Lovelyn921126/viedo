/*
 * FileName: RegexUtils.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

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
 * 2018年6月27日 下午2:04:25          dell        4.1         To create
 * </p>
 *
 * @since
 * @see
 */
public class RegexUtils {
    /**
    *判断是否是正确的IP地址
    * @param ip
    * @return boolean true,通过，false，没通过
    */
    public static boolean isIP(String ip) {
        if (StringUtils.isBlank(ip)) {
            return false;
        }
        String regex = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\." + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\." + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\." + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
        return ip.matches(regex);
    }

    /**
    *判断是否是正确的邮箱地址
    * @param email
    * @return boolean true,通过，false，没通过
    */
    public static boolean isEmail(String email) {
        if (StringUtils.isBlank(email)) {
            return false;
        }
        String regex = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
        return email.matches(regex);
    }

    /**
     * 判断是否有汉字
     * @param text
     * @return boolean true,通过，false，没通过
     */
    public static boolean isContainChinese(String text) {
        if (null == text || "".equals(text))
            return false;
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(text);
        return m.find();
    }

    /**
    * 是不是全漢字
    * @param text
    * @return boolean true,通过，false，没通过
    */
    public static boolean isChinese(String text) {
        if (StringUtils.isBlank(text)) {
            return false;
        }
        char[] chars = text.toCharArray();
        for (char c : chars) {
            if (!isChinese(c)) {
                return false;
            }
        }
        return true;

    }

    /**
     * 是不是漢字(字符)
     * @param text
     * @return boolean true,通过，false，没通过
     */
    private static boolean isChinese(char c) {
        return c >= 0x4E00 && c <= 0x9FA5;
    }

    /**
     *是不是全漢字  适应CJK（中日韩）字符集，部分中日韩的字是一样的
     */
    public static boolean isChinese2(String strName) {
        char[] ch = strName.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            char c = ch[i];
            if (isChinese(c)) {
                return true;
            }
        }
        return false;
    }

    /**
     *获取字符的长度  中文当作2个字符
     * @param text text
     * @return
     */
    public static int strLen(String text) {
        if (StringUtils.isBlank(text)) {
            return 0;
        }
        int len = 0;
        char[] chars = text.toCharArray();
        for (char c : chars) {
            if (isChinese(c)) {
                len++;
            }
        }
        return len * 1 + text.length();
    }

    /**
    *  字符串截取，1个中文当3个字符是否补省略号
    * @param text 要截取的字符串
    * @param len  截取的长度
    * @param ellipsis 是否补省略号
    * @return
    */
    public static String subStr(String text, int len, boolean ellipsis) {
        if (StringUtils.isBlank(text)) {
            return "";
        }
        int index = 0;
        StringBuilder stringBuilder = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (isChinese(c)) {
                index = index + 3;
            } else {
                index++;
            }
            if (index > len) {
                stringBuilder.append(c);
            }
        }
        if (ellipsis) {
            stringBuilder.append("...");
        }
        return stringBuilder.toString();

    }

    /**
     * 字符串截取，1个中文当3个字符   不补省略号
     * @param text 要截取的字符串
     * @param len  截取的长度
     * @return
     */
    public static String subStr(String text, int len) {
        return subStr(text, len, false);

    }

    /**
     * 判断是否正整数
     *
     * @param number
     *            数字
     * @return boolean true,通过，false，没通过
     */
    public static boolean isNumber(String number) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(number).matches();
        /* if (null == number || "".equals(number))
            return false;
        String regex = "[0-9]*";
        return number.matches(regex);*/
    }

    /**
     * 精准判断几位小数(正数)
     * @param decimal  数字
     * @param count 小数位数
     * @return boolean true,通过，false，没通过
     */
    public static boolean isDecimal(String decimal, int count) {
        if (StringUtils.isBlank(decimal)) {
            return false;
        }
        String regex = "^(-)?([1-d]\\w*)|([0])(\\.\\d{" + count + "})?$";
        return decimal.matches(regex);
    }

    /**
     * 必须包含几位小数
     * @param decimal  数字
     * @param count 小数位数
     * @return boolean true,通过，false，没通过
     */
    public static boolean isMustDecimal(String decimal, int count) {
        if (StringUtils.isBlank(decimal)) {
            return false;
        }
        String regex = "^(-)?(([1-9]\\w*)|([0]))(\\.\\d{0," + count + "})?$";
        return decimal.matches(regex);
    }

    /**
     * 验证自定义 正则规则
     *
     * @param value 要验证的值
     * @param regex 自定义 正则规则
     *
     * @return boolean true,通过，false，没通过
    
    */
    public static boolean isRegex(String value, String regex) {
        if (StringUtils.isBlank(value) || StringUtils.isBlank(regex)) {
            return false;
        }
        return value.matches(regex);
    }

    /**
     * 判断是否是手机号码
     *
     * @param phoneNumber
     *            手机号码
     * @return boolean true,通过，false，没通过
    
    */
    public static boolean isPhoneNumber(String phoneNumber) {
        if (StringUtils.isBlank(phoneNumber)) {
            return false;
        }
        String regex = "1[3|4|7|5|8|][0-9]\\d{9}$";
        return phoneNumber.matches(regex);
    }

    /**
     * 判断是否含有特殊字符
     *
     * @param text
     * @return boolean true,通过，false，没通过
     */
    public static boolean hasSpecialChar(String text) {
        if (StringUtils.isBlank(text)) {
            return false;
        }
        if (text.replaceAll("[a-z]*[A-Z]*\\d*-*_*\\s*", "").length() == 0) {
            // 如果不包含特殊字符
            return true;
        }
        return false;

    }

    public static void main(String[] args) {
        String string = "11.11";
        System.out.println(isDecimal(string, 4));
    }

}
