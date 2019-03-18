package com.ultrapower.viedo.viladate;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.ultrapower.viedo.utils.DateFormet;

/*
 * FileName: CheckInt.java
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
 * 2018年6月27日 上午9:00:39          dell        4.1         To create
 * </p>
 *
 * @since
 * @see
 */
@Documented()
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Inherited()
public @interface ValidateDate {
    /**
     *时间参数的  格式 参考 cn.com.ultrapower.zcwg.helpyourself.extend.provinceInterface.utils.DateFormet
     */
    DateFormet dateFormat() default DateFormet.YY_MM_DD_HH_MM_SS;

    /**
     * 时间参数的 截止时间 格式 参考cn.com.ultrapower.zcwg.helpyourself.extend.provinceInterface.utils.DateFormet 中的格式 如果没有请自行添加
     * @return
     */
    String endTime() default "";

    /**
     *时间参数的 起始时间 格式 参考cn.com.ultrapower.zcwg.helpyourself.extend.provinceInterface.utils.DateFormet 中的格式 如果没有请自行添加
     * @return
     */
    String startTime() default "";

    /**
     * 是否为必须
     * @return
     */
    boolean required() default false;

    /**
     * 字段的描述 用于 异常处理
     * @return
     */
    String description() default "";

}
