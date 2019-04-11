/*
 * FileName: ProductEventHandler.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.disruptor;

import java.util.concurrent.atomic.AtomicLong;

import com.lmax.disruptor.EventHandler;

/**
 * <p>
 * Description:  定义事件处理的具体实现
 *                   通过实现接口 com.lmax.disruptor.EventHandler<T> 定义事件处理的具体实现
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
public class ProductEventHandler implements EventHandler<Event> {
    AtomicLong atomicLong = new AtomicLong();

    @Override
    public void onEvent(Event event, long sequence, boolean endOfBatch) throws Exception {

    }

}
