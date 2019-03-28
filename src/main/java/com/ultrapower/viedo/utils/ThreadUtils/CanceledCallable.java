/*
 * FileName: OneLevelAsynContent.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.utils.ThreadUtils;

import java.util.concurrent.Callable;

import javax.servlet.AsyncContext;

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
 * 2019年3月28日 下午3:55:01          dell        4.1         To create
 * </p>
 *
 * @since
 * @see
 */
@Component
public class CanceledCallable implements Callable<Object> {
    private AsyncContext asyncContext;
    //private int milliseconds;

    /**
     *
     */
    public CanceledCallable(AsyncContext context) {
        this.asyncContext = context;
    }

    /* (non-Javadoc)
     * @see java.lang.Runnable#run()
     */

    /**
     * @return the asyncContext
     */
    public AsyncContext getAsyncContext() {
        return asyncContext;
    }

    /**
     * @param asyncContext the asyncContext to set
     */
    public void setAsyncContext(AsyncContext asyncContext) {
        this.asyncContext = asyncContext;
    }

    /* (non-Javadoc)
     * @see java.util.concurrent.Callable#call()
     */
    @Override
    public Object call() throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

}
