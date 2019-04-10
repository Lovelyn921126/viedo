/*
 * FileName: ProductEventHandler.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.disruptor;

import com.lmax.disruptor.EventHandler;

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
 * 2019年4月10日 下午5:15:11          dell        4.1         To create
 * </p>
 *
 * @since
 * @see
 */
public class ProductEventHandler implements EventHandler<EventQueue> {

    /* (non-Javadoc)
     * @see com.lmax.disruptor.EventHandler#onEvent(java.lang.Object, long, boolean)
     */
    @Override
    public void onEvent(EventQueue event, long sequence, boolean endOfBatch) throws Exception {
        // TODO Auto-generated method stub

    }

}
