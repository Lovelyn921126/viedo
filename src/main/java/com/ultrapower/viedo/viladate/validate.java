/*
 * FileName: validate.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.viladate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.ultrapower.viedo.plugins.AuthChecker;
import com.ultrapower.viedo.vo.UserInfoCacheBean;

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
 * 2018年4月27日 下午4:31:02          dell        4.1         To create
 * </p>
 *
 * @since
 * @see
 */
public class validate implements ConstraintValidator<AuthChecker, UserInfoCacheBean> {

    /* (non-Javadoc)
     * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
     */
    private AuthChecker au;

    @Override
    public void initialize(AuthChecker arg0) {
        // TODO Auto-generated method stub
        this.au = arg0;
    }

    /* (non-Javadoc)
     * @see javax.validation.ConstraintValidator#isValid(java.lang.Object, javax.validation.ConstraintValidatorContext)
     */
    @Override
    public boolean isValid(UserInfoCacheBean bean, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        // context.buildConstraintViolationWithTemplate(arg0)
        return false;
    }

}
