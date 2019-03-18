package com.ultrapower.viedo.viladate;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Payload;

import com.ultrapower.viedo.utils.RegexType;

/*
 * FileName: ValidateString.java
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
 * 2018年6月27日 下午1:49:21          dell        4.1         To create
 * </p>
 *
 * @since
 * @see
 */
@Documented()
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Inherited()
public @interface ValidateString {

    //默认错误消息
    String message() default "{forbidden.word}";

    //分组
    Class<?>[] groups() default {};

    //负载
    Class<? extends Payload>[] payload() default {};

    /**
     * 是否必须
     * @return
     */
    boolean required() default false;

    /**
     * 字段的 字节最大长度 即 string.getbytes.length  -1 为不验证
     * @return
     */
    int maxLength() default -1;

    /**
     *字段的 字节最小长度 即 string.getbytes.length
     * @return
     */
    int minLength() default -1;

    /**
    * 是否允许包含特殊字符 默认 允许
    * @return
    */
    boolean hasSpecialChar() default true;

    /**
     * 默认的 正则匹配
     * @return
     */
    RegexType regexType() default RegexType.NONE;

    /**
     * 自定义正则验证
     * @return
     */
    String regexExpression() default "";

    /**
    * 字段描述
    * @return
    */
    String description() default "";

    /**
     *
     */
    boolean startWith() default false;
}
