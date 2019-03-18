/*
 * FileName: ValidateParameter.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.viladate;

import java.io.Serializable;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang.StringUtils;

import com.ultrapower.viedo.utils.RegexType;
import com.ultrapower.viedo.utils.RegexUtils;

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
 * 2018年7月2日 上午10:23:08          dell        4.1         To create
 * </p>
 *
 * @since
 * @see
 */
public class ValidateParameter2 implements Serializable, ConstraintValidator<ValidateString, String> {

    ValidateString validateString = null;
    /**
     *
     */
    private static final long serialVersionUID = 2627429090732326340L;

    /**
     *
     */
    public ValidateParameter2() {
        super();
        // TODO Auto-generated constructor stub
    }

    /* (non-Javadoc)
     * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
     */
    @Override
    public void initialize(ValidateString value) {
        this.validateString = value;

    }

    /* (non-Javadoc)
     * @see javax.validation.ConstraintValidator#isValid(java.lang.Object, javax.validation.ConstraintValidatorContext)
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        System.out.println("成功");

        //验证是否必传 如果必传 再做其他操作
        if (validateString.required()) {

            //验证是否为空
            if (StringUtils.isBlank(value)) {
                errorInfo(validateString.description() + "不能为空", context);
                return false;
                // throw new FTPExcpetion(ProvinceSynchroResultEnum.requiredIsNull.getCode(), description.toString() + ProvinceSynchroResultEnum.requiredIsNull.getMsg(), provinceCode);
            }
            //字符串 的最大长度
            int max = validateString.maxLength();

            if (max != -1) {
                int count = RegexUtils.strLen(value);
                if (count > max) {
                    errorInfo("不能为空", context);
                    return false;
                    // throw new FTPExcpetion(ProvinceSynchroResultEnum.characterExceedingLength.getCode(), description.toString() + ProvinceSynchroResultEnum.characterExceedingLength.getMsg(), provinceCode);
                }
            }
            //字符串的自小长度
            int min = validateString.minLength();
            if (min != -1) {
                int count = RegexUtils.strLen(value);
                if (count < min) {
                    errorInfo("不能为空", context);
                    return false;
                    // throw new FTPExcpetion(ProvinceSynchroResultEnum.characterExceedingLength.getCode(), description.toString() + ProvinceSynchroResultEnum.characterExceedingLength.getMsg(), provinceCode);
                }
            }
            //字符串的 自定义正则规则
            String regex = validateString.regexExpression();
            if (StringUtils.isNotBlank(regex)) {
                boolean flag = RegexUtils.isRegex(value, regex);
                if (!flag) {
                    errorInfo("不能为空", context);
                    return false;
                    //throw new FTPExcpetion(ProvinceSynchroResultEnum.invalidFormat.getCode(), description.toString() + ProvinceSynchroResultEnum.invalidFormat.getMsg(), provinceCode);
                }
            }
            //字符串的 是否还有特殊字符
            boolean hasSpecialChar = validateString.hasSpecialChar();
            if (!hasSpecialChar) {
                boolean isSpecialChar = RegexUtils.hasSpecialChar(value);
                if (isSpecialChar) {
                    errorInfo("不能为空", context);
                    return false;
                    // throw new FTPExcpetion(ProvinceSynchroResultEnum.invalidFormat.getCode(), description.toString() + ProvinceSynchroResultEnum.invalidFormat.getMsg(), provinceCode);
                }
            }
            //解析 正则规则
            RegexType regexType = validateString.regexType();
            if (regexType != RegexType.NONE) {
                boolean isFormat = regexType(value, regexType);
                if (!isFormat) {

                    // throw new FTPExcpetion(ProvinceSynchroResultEnum.invalidFormat.getCode(), description.toString() + ProvinceSynchroResultEnum.invalidFormat.getMsg(), provinceCode);
                }
            }

        }

        return true;
    }

    /**
     * 验证字段的格式
     * @param value 要验证的自断案
     * @param regexType  通过枚举 检验要验证的字段格式
     * @return
     */
    private static boolean regexType(String value, RegexType regexType) {
        //验证值是否为 邮箱
        if (regexType == RegexType.EMAIL) {
            return RegexUtils.isEmail(value);
        }
        //验证值是不是全漢字
        if (regexType == RegexType.CHINESE) {
            return RegexUtils.isChinese(value);
        }
        //验证值是不是全漢字 适应CJK（中日韩）字符集，部分中日韩的字是一样的
        if (regexType == RegexType.CHINESE2) {
            return RegexUtils.isChinese2(value);
        }
        //验证值是否有汉字
        if (regexType == RegexType.ContainChinese) {
            return RegexUtils.isContainChinese(value);
        }
        //验证值是否是正确的IP地址
        if (regexType == RegexType.IP) {
            return RegexUtils.isIP(value);
        }
        //验证值是否是手机号码
        if (regexType == RegexType.PHONENUMBER) {
            return RegexUtils.isPhoneNumber(value);
        }

        return false;
    }

    public void errorInfo(String errorInfo, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(errorInfo).addConstraintViolation();
    }
}
