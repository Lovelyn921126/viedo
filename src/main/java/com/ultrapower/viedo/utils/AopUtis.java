/*
 * FileName: AopUtis.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.utils;

import org.springframework.stereotype.Component;

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
 * 2018年4月4日 上午9:31:09          dell        4.1         To create
 * </p>
 *
 * @since
 * @see
 */
@Component
//@Aspect
//@Order(0)
public class AopUtis {
    /*
    @Pointcut("execution(* com.ultrapower.viedo.service..*(..))")
    public void pointcutName() {
    }

    @Before("pointcutName()")
    public void performance(JoinPoint joinPoint) {
        System.out.println("Spring AOP");
        System.out.println(TransactionSynchronizationManager.isSynchronizationActive());
        System.out.println(joinPoint.getSignature().getName());
        String name = joinPoint.getSignature().getName();
        if (name.startsWith("get")) {
            DataSourceHolder.setMaster();
        }
        if (name.startsWith("update")) {
            DataSourceHolder.setSlave();

        }
    }

    @After("pointcutName()")
    public void performance1(JoinPoint joinPoint) {
        System.out.println("clean");
        DataSourceHolder.clean();
    }*/
}
