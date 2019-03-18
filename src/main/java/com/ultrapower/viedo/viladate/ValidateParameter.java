/*
 * FileName: ValidateParameter.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.viladate;

import java.io.Serializable;
import java.lang.reflect.Field;

import org.apache.commons.lang.StringUtils;

import com.ultrapower.viedo.bean.ProvinceSynchroResult;
import com.ultrapower.viedo.utils.ProvinceSynchroResultEnum;
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
public class ValidateParameter implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 2627429090732326340L;

    private static String provinceCode = "WD";

    private static String provinceNum = "0000";

    /**
     *
     */
    public ValidateParameter() {
        super();
        // TODO Auto-generated constructor stub
    }

    public ProvinceSynchroResult validate(String provinceCode, String provinceNum) {
        ValidateParameter.provinceCode = provinceCode;
        ValidateParameter.provinceNum = provinceNum;
        return valid(this);

    }

    /**
     * @param validateParameter
     * @throws Exception
     */
    public static ProvinceSynchroResult valid(Object object) {
        // 获取object的类型
        Class<? extends Object> clazz = object.getClass();
        ProvinceSynchroResult result = new ProvinceSynchroResult();
        // 获取该类型声明的成员
        Field[] fields = clazz.getDeclaredFields();
        // 遍历属性
        for (Field field : fields) {
            // 对于private私有化的成员变量，通过setAccessible来修改器访问权限
            field.setAccessible(true);
            result = Validate(field, object);
            // 重新设置会私有权限
            field.setAccessible(false);
            if (!result.getCode().equals("000")) {
                return result;
            }
        }

        return result;

    }

    /**
     * @param field
     * @param object
     */
    public static ProvinceSynchroResult Validate(Field field, Object object) {

        //字段的描述
        StringBuilder description = new StringBuilder();
        description.append(provinceNum).append(":");
        //String description = "";
        ProvinceSynchroResult result = new ProvinceSynchroResult();
        result.setCode(ProvinceSynchroResultEnum.readNormal.getCode());
        result.setMsg(ProvinceSynchroResultEnum.readNormal.getMsg());
        //字段  的值
        String value;
        ValidateDate validateDate = field.getAnnotation(ValidateDate.class);
        ValidateInt validateInt = field.getAnnotation(ValidateInt.class);
        ValidateString validateString = field.getAnnotation(ValidateString.class);
        //
        if (validateDate != null) {
            //description = validateDate.description();

            if (validateDate.required()) {
                try {
                    value = (String) field.get(object);
                } catch (Exception e) {
                    description.append(validateDate.description());
                    result.setCode(ProvinceSynchroResultEnum.fileError.getCode());
                    result.setMsg(description.toString() + ProvinceSynchroResultEnum.fileError.getMsg());
                    return result;
                    //throw new FTPExcpetion(ProvinceSynchroResultEnum.fileError.getCode(), description.toString() + ProvinceSynchroResultEnum.fileError.getMsg(), provinceCode);
                }

            }

        } else if (validateInt != null) {
            //description = validateDate.description();

            //验证是否必传 如果必传 再做其他操作
            if (validateInt.required()) {
                try {
                    value = (String) field.get(object);
                } catch (Exception e) {
                    description.append(validateInt.description());
                    result.setCode(ProvinceSynchroResultEnum.fileError.getCode());
                    result.setMsg(description.toString() + ProvinceSynchroResultEnum.fileError.getMsg());
                    return result;
                    // throw new FTPExcpetion(ProvinceSynchroResultEnum.fileError.getCode(), description.toString() + ProvinceSynchroResultEnum.fileError.getMsg());
                }
                //验证是否为空
                if (StringUtils.isBlank(value)) {
                    description.append(validateInt.description());
                    result.setCode(ProvinceSynchroResultEnum.requiredIsNull.getCode());
                    result.setMsg(description.toString() + ProvinceSynchroResultEnum.requiredIsNull.getMsg());
                    return result;
                    // throw new FTPExcpetion(ProvinceSynchroResultEnum.requiredIsNull.getCode(), description.toString() + ProvinceSynchroResultEnum.requiredIsNull.getMsg(), provinceCode);
                }
                //通过正则判断是不是 数字
                if (RegexUtils.isNumber(value)) {
                    int max = validateInt.maxValue();
                    int min = validateInt.minValue();
                    Integer number = Integer.valueOf(value);
                    //判断数字的 大小范围
                    if (number < min || number > max) {
                        description.append(validateInt.description());
                        result.setCode(ProvinceSynchroResultEnum.illegalNumber.getCode());
                        result.setMsg(description.toString() + ProvinceSynchroResultEnum.illegalNumber.getMsg());
                        return result;
                        //throw new FTPExcpetion(ProvinceSynchroResultEnum.illegalNumber.getCode(), description.toString() + ProvinceSynchroResultEnum.illegalNumber.getMsg(), provinceCode);
                    }
                } else {
                    description.append(validateInt.description());
                    result.setCode(ProvinceSynchroResultEnum.illegalNumber.getCode());
                    result.setMsg(description.toString() + ProvinceSynchroResultEnum.illegalNumber.getMsg());
                    return result;
                    // throw new FTPExcpetion(ProvinceSynchroResultEnum.illegalNumber.getCode(), description.toString() + ProvinceSynchroResultEnum.illegalNumber.getMsg(), provinceCode);
                }
            }

        } else if (validateString != null) {
            //description = validateDate.description();

            //验证是否必传 如果必传 再做其他操作
            if (validateString.required()) {
                try {
                    value = (String) field.get(object);
                } catch (Exception e) {
                    description.append(validateString.description());
                    result.setCode(ProvinceSynchroResultEnum.fileError.getCode());
                    result.setMsg(description.toString() + ProvinceSynchroResultEnum.fileError.getMsg());
                    return result;
                    // throw new FTPExcpetion(ProvinceSynchroResultEnum.fileError.getCode(), description.toString() + ProvinceSynchroResultEnum.fileError.getMsg(), provinceCode);
                }
                //验证是否为空
                if (StringUtils.isBlank(value)) {
                    description.append(validateString.description());
                    result.setCode(ProvinceSynchroResultEnum.requiredIsNull.getCode());
                    result.setMsg(description.toString() + ProvinceSynchroResultEnum.requiredIsNull.getMsg());
                    return result;
                    // throw new FTPExcpetion(ProvinceSynchroResultEnum.requiredIsNull.getCode(), description.toString() + ProvinceSynchroResultEnum.requiredIsNull.getMsg(), provinceCode);
                }
                //字符串 的最大长度
                int max = validateString.maxLength();

                if (max != -1) {
                    int count = RegexUtils.strLen(value);
                    if (count > max) {
                        description.append(validateString.description());
                        result.setCode(ProvinceSynchroResultEnum.characterExceedingLength.getCode());
                        result.setMsg(description.toString() + ProvinceSynchroResultEnum.characterExceedingLength.getMsg());
                        return result;
                        // throw new FTPExcpetion(ProvinceSynchroResultEnum.characterExceedingLength.getCode(), description.toString() + ProvinceSynchroResultEnum.characterExceedingLength.getMsg(), provinceCode);
                    }
                }
                //字符串的自小长度
                int min = validateString.minLength();
                if (min != -1) {
                    int count = RegexUtils.strLen(value);
                    if (count < min) {
                        description.append(validateString.description());
                        result.setCode(ProvinceSynchroResultEnum.characterExceedingLength.getCode());
                        result.setMsg(description.toString() + ProvinceSynchroResultEnum.characterExceedingLength.getMsg());
                        return result;
                        // throw new FTPExcpetion(ProvinceSynchroResultEnum.characterExceedingLength.getCode(), description.toString() + ProvinceSynchroResultEnum.characterExceedingLength.getMsg(), provinceCode);
                    }
                }
                //字符串的 自定义正则规则
                String regex = validateString.regexExpression();
                if (StringUtils.isNotBlank(regex)) {
                    boolean flag = RegexUtils.isRegex(value, regex);
                    if (!flag) {
                        description.append(validateString.description());
                        result.setCode(ProvinceSynchroResultEnum.invalidFormat.getCode());
                        result.setMsg(description.toString() + ProvinceSynchroResultEnum.invalidFormat.getMsg());
                        return result;
                        //throw new FTPExcpetion(ProvinceSynchroResultEnum.invalidFormat.getCode(), description.toString() + ProvinceSynchroResultEnum.invalidFormat.getMsg(), provinceCode);
                    }
                }
                //字符串的 是否还有特殊字符
                boolean hasSpecialChar = validateString.hasSpecialChar();
                if (!hasSpecialChar) {
                    boolean isSpecialChar = RegexUtils.hasSpecialChar(value);
                    if (isSpecialChar) {
                        description.append(validateString.description());
                        result.setCode(ProvinceSynchroResultEnum.invalidFormat.getCode());
                        result.setMsg(description.toString() + ProvinceSynchroResultEnum.invalidFormat.getMsg());
                        return result;
                        // throw new FTPExcpetion(ProvinceSynchroResultEnum.invalidFormat.getCode(), description.toString() + ProvinceSynchroResultEnum.invalidFormat.getMsg(), provinceCode);
                    }
                }
                //解析 正则规则
                RegexType regexType = validateString.regexType();
                if (regexType != RegexType.NONE) {
                    boolean isFormat = regexType(value, regexType);
                    if (!isFormat) {
                        description.append(validateString.description());
                        result.setCode(ProvinceSynchroResultEnum.invalidFormat.getCode());
                        result.setMsg(description.toString() + ProvinceSynchroResultEnum.invalidFormat.getMsg());
                        return result;
                        // throw new FTPExcpetion(ProvinceSynchroResultEnum.invalidFormat.getCode(), description.toString() + ProvinceSynchroResultEnum.invalidFormat.getMsg(), provinceCode);
                    }
                }

            }

        }
        return result;

    }

    public ProvinceSynchroResult validateUpgrade(String provinceCode, String provinceNum) {
        ValidateParameter.provinceCode = provinceCode;
        ValidateParameter.provinceNum = provinceNum;
        return validUpgrade(this);

    }

    /**  验证关于升级的参数
     * @param validateParameter
     * @throws Exception
     */
    public static ProvinceSynchroResult validUpgrade(Object object) {
        // 获取object的类型
        Class<? extends Object> clazz = object.getClass();
        // 获取该类型声明的成员
        Field[] fields = clazz.getDeclaredFields();
        ProvinceSynchroResult result = new ProvinceSynchroResult();
        // 遍历属性
        for (Field field : fields) {
            // 对于private私有化的成员变量，通过setAccessible来修改器访问权限
            field.setAccessible(true);
            result = ValidateUpgrade(field, object);
            // 重新设置会私有权限
            field.setAccessible(false);
            if (!result.getCode().equals("000")) {
                return result;
            }
        }
        return result;
    }

    /**
     * @param field
     * @param object
     */
    public static ProvinceSynchroResult ValidateUpgrade(Field field, Object object) {

        //字段的描述
        StringBuilder description = new StringBuilder();
        description.append(provinceNum).append(":");
        //String description = "";
        ProvinceSynchroResult result = new ProvinceSynchroResult();
        result.setCode(ProvinceSynchroResultEnum.readNormal.getCode());
        result.setMsg(ProvinceSynchroResultEnum.readNormal.getMsg());
        //字段  的值
        String value;
        ValidateDate validateDate = field.getAnnotation(ValidateDate.class);
        ValidateInt validateInt = field.getAnnotation(ValidateInt.class);
        ValidateString validateString = field.getAnnotation(ValidateString.class);
        //
        if (validateDate != null) {
            //description = validateDate.description();
            if (field.getName().startsWith("upgrade")) {
                try {
                    value = (String) field.get(object);
                } catch (Exception e) {
                    description.append(validateDate.description());
                    result.setCode(ProvinceSynchroResultEnum.fileError.getCode());
                    result.setMsg(description.toString() + ProvinceSynchroResultEnum.fileError.getMsg());
                    return result;
                    //throw new FTPExcpetion(ProvinceSynchroResultEnum.fileError.getCode(), description.toString() + ProvinceSynchroResultEnum.fileError.getMsg(), provinceCode);
                }
            }

        } else if (validateInt != null) {
            //description = validateDate.description();
            if (field.getName().startsWith("upgrade")) {
                //验证是否必传 如果必传 再做其他操作
                if (field.getName().startsWith("upgrade")) {
                    try {
                        value = (String) field.get(object);
                    } catch (Exception e) {
                        description.append(validateInt.description());
                        result.setCode(ProvinceSynchroResultEnum.fileError.getCode());
                        result.setMsg(description.toString() + ProvinceSynchroResultEnum.fileError.getMsg());
                        return result;
                        // throw new FTPExcpetion(ProvinceSynchroResultEnum.fileError.getCode(), description.toString() + ProvinceSynchroResultEnum.fileError.getMsg());
                    }
                    //验证是否为空
                    if (StringUtils.isBlank(value)) {
                        description.append(validateInt.description());
                        result.setCode(ProvinceSynchroResultEnum.requiredIsNull.getCode());
                        result.setMsg(description.toString() + ProvinceSynchroResultEnum.requiredIsNull.getMsg());
                        return result;
                        // throw new FTPExcpetion(ProvinceSynchroResultEnum.requiredIsNull.getCode(), description.toString() + ProvinceSynchroResultEnum.requiredIsNull.getMsg(), provinceCode);
                    }
                    //通过正则判断是不是 数字
                    if (RegexUtils.isNumber(value)) {
                        int max = validateInt.maxValue();
                        int min = validateInt.minValue();
                        Integer number = Integer.valueOf(value);
                        //判断数字的 大小范围
                        if (number < min || number > max) {
                            description.append(validateInt.description());
                            result.setCode(ProvinceSynchroResultEnum.illegalNumber.getCode());
                            result.setMsg(description.toString() + ProvinceSynchroResultEnum.illegalNumber.getMsg());
                            return result;
                            //throw new FTPExcpetion(ProvinceSynchroResultEnum.illegalNumber.getCode(), description.toString() + ProvinceSynchroResultEnum.illegalNumber.getMsg(), provinceCode);
                        }
                    } else {
                        description.append(validateInt.description());
                        result.setCode(ProvinceSynchroResultEnum.illegalNumber.getCode());
                        result.setMsg(description.toString() + ProvinceSynchroResultEnum.illegalNumber.getMsg());
                        return result;
                        // throw new FTPExcpetion(ProvinceSynchroResultEnum.illegalNumber.getCode(), description.toString() + ProvinceSynchroResultEnum.illegalNumber.getMsg(), provinceCode);
                    }
                }
            } else if (validateString != null) {
                //description = validateDate.description();

                //验证是否必传 如果必传 再做其他操作

                try {
                    value = (String) field.get(object);
                } catch (Exception e) {
                    description.append(validateString.description());
                    result.setCode(ProvinceSynchroResultEnum.fileError.getCode());
                    result.setMsg(description.toString() + ProvinceSynchroResultEnum.fileError.getMsg());
                    return result;
                    // throw new FTPExcpetion(ProvinceSynchroResultEnum.fileError.getCode(), description.toString() + ProvinceSynchroResultEnum.fileError.getMsg(), provinceCode);
                }
                //验证是否为空
                if (StringUtils.isBlank(value)) {
                    description.append(validateString.description());
                    result.setCode(ProvinceSynchroResultEnum.requiredIsNull.getCode());
                    result.setMsg(description.toString() + ProvinceSynchroResultEnum.requiredIsNull.getMsg());
                    return result;
                    // throw new FTPExcpetion(ProvinceSynchroResultEnum.requiredIsNull.getCode(), description.toString() + ProvinceSynchroResultEnum.requiredIsNull.getMsg(), provinceCode);
                }
                //字符串 的最大长度
                int max = validateString.maxLength();

                if (max != -1) {
                    int count = RegexUtils.strLen(value);
                    if (count > max) {
                        description.append(validateString.description());
                        result.setCode(ProvinceSynchroResultEnum.characterExceedingLength.getCode());
                        result.setMsg(description.toString() + ProvinceSynchroResultEnum.characterExceedingLength.getMsg());
                        return result;
                        // throw new FTPExcpetion(ProvinceSynchroResultEnum.characterExceedingLength.getCode(), description.toString() + ProvinceSynchroResultEnum.characterExceedingLength.getMsg(), provinceCode);
                    }
                }
                //字符串的自小长度
                int min = validateString.minLength();
                if (min != -1) {
                    int count = RegexUtils.strLen(value);
                    if (count < min) {
                        description.append(validateString.description());
                        result.setCode(ProvinceSynchroResultEnum.characterExceedingLength.getCode());
                        result.setMsg(description.toString() + ProvinceSynchroResultEnum.characterExceedingLength.getMsg());
                        return result;
                        // throw new FTPExcpetion(ProvinceSynchroResultEnum.characterExceedingLength.getCode(), description.toString() + ProvinceSynchroResultEnum.characterExceedingLength.getMsg(), provinceCode);
                    }
                }
                //字符串的 自定义正则规则
                String regex = validateString.regexExpression();
                if (StringUtils.isNotBlank(regex)) {
                    boolean flag = RegexUtils.isRegex(value, regex);
                    if (!flag) {
                        description.append(validateString.description());
                        result.setCode(ProvinceSynchroResultEnum.invalidFormat.getCode());
                        result.setMsg(description.toString() + ProvinceSynchroResultEnum.invalidFormat.getMsg());
                        return result;
                        //throw new FTPExcpetion(ProvinceSynchroResultEnum.invalidFormat.getCode(), description.toString() + ProvinceSynchroResultEnum.invalidFormat.getMsg(), provinceCode);
                    }
                }
                //字符串的 是否还有特殊字符
                boolean hasSpecialChar = validateString.hasSpecialChar();
                if (!hasSpecialChar) {
                    boolean isSpecialChar = RegexUtils.hasSpecialChar(value);
                    if (isSpecialChar) {
                        description.append(validateString.description());
                        result.setCode(ProvinceSynchroResultEnum.invalidFormat.getCode());
                        result.setMsg(description.toString() + ProvinceSynchroResultEnum.invalidFormat.getMsg());
                        return result;
                        // throw new FTPExcpetion(ProvinceSynchroResultEnum.invalidFormat.getCode(), description.toString() + ProvinceSynchroResultEnum.invalidFormat.getMsg(), provinceCode);
                    }
                }
                //解析 正则规则
                RegexType regexType = validateString.regexType();
                if (regexType != RegexType.NONE) {
                    boolean isFormat = regexType(value, regexType);
                    if (!isFormat) {
                        description.append(validateString.description());
                        result.setCode(ProvinceSynchroResultEnum.invalidFormat.getCode());
                        result.setMsg(description.toString() + ProvinceSynchroResultEnum.invalidFormat.getMsg());
                        return result;
                        // throw new FTPExcpetion(ProvinceSynchroResultEnum.invalidFormat.getCode(), description.toString() + ProvinceSynchroResultEnum.invalidFormat.getMsg(), provinceCode);
                    }
                }

            }

        }
        return result;

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

    /**
     * @return the provinceCode
     */
    public String getProvinceCode() {
        return provinceCode;
    }

    /**
     * @param provinceCode the provinceCode to set
     */
    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    /*    @Override
    public String toString() {
        Class<? extends Object> clazz = this.getClass();
        StringBuilder sbuilder = new StringBuilder();
        Field[] fields = clazz.getDeclaredFields();
        sbuilder.append(clazz.getName() + "[");
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            fields[i].getName();
            if (fields[i].getName() != "serialVersionUID") {
                try {
                    if (i == fields.length - 1) {
                        sbuilder.append(fields[i].getName() + ":" + fields[i].get(this).toString());
                    } else {
                        sbuilder.append(fields[i].getName() + ":" + fields[i].get(this).toString() + ",");
                    }
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

        }
        sbuilder.append("]");
        return sbuilder.toString();
    }
    */
}
