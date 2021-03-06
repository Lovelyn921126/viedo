/*
 * FileName: ProductEventHandler.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.disruptor;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicLong;

import com.lmax.disruptor.EventHandler;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>  业务组件 实际处理任务的组件 任务成功后 会通过 eventQueue 将任务从redis 移除  失败后 会把任务 加入到 失败队列
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
@Slf4j
public class ProductEventHandler implements EventHandler<Event> {
    AtomicLong atomicLong = new AtomicLong();

    private EventQueue eventQueue;

    public EventQueue getEventQueue() {
        return eventQueue;
    }

    public void setEventQueue(EventQueue eventQueue) {
        this.eventQueue = eventQueue;
    }

    @Override
    public void onEvent(Event event, long sequence, boolean endOfBatch) throws ExecutionException {
        log.info("ProductEventHandler---onEvent");
        try {

            eventQueue.success(String.valueOf(sequence));
            log.info("ProductEventHandler---success");
            System.out.println(event.getEventType() + "----" + event.getKey());
        } catch (Exception e) {
            log.info("ProductEventHandler---fail");
            eventQueue.fail(String.valueOf(sequence));
        }

    }

}
