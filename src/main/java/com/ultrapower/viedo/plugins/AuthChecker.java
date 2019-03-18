/*
 * FileName: AuthChecker.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.plugins;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;

import com.ultrapower.viedo.viladate.validate;

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
 * 2018年4月4日 下午2:14:40          dell        4.1         To create
 * </p>
 *
 * @since
 * @see
 */
@Target(ElementType.METHOD)
@Constraint(validatedBy = validate.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthChecker {
}
