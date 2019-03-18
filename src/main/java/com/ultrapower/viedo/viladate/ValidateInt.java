package com.ultrapower.viedo.viladate;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 * FileName: ValidateInt.java
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
 * 2018年6月27日 下午1:46:53          dell        4.1         To create
 * </p>
 *
 * @since
 * @see
 */
@Documented()
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Inherited()
public @interface ValidateInt {
    /**
     *  最大值
     * @return
     */
    int maxValue() default Integer.MAX_VALUE;

    /**
     * 最小值
     * @return
     */
    int minValue() default Integer.MIN_VALUE;

    /**
     * 是否必须
     * @return
     */
    boolean required() default false;

    /**
    * 字段描述 用于异常处理
    * @return
    */
    String description() default "";
}
