/*
 * FileName: EventPublishThread.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.disruptor;

import com.lmax.disruptor.EventTranslator;
import com.lmax.disruptor.RingBuffer;

/**
 * <p>
 * Description:  定义事件工厂
 *  事件工厂(Event Factory)定义了如何实例化前面第1步中定义的事件(Event)，需要实现接口 com.lmax.disruptor.EventFactory<T>。
 *   Disruptor 通过 EventFactory 在 RingBuffer 中预创建 Event 的实例。
 *   一个 Event 实例实际上被用作一个“数据槽”，发布者发布前，先从 RingBuffer 获得一个 Event 的实例，然后往 Event 实例中填充数据，之后再发布到 RingBuffer 中，之后由 Consumer 获得该 Event 实例并从中读取数据。
 * </p>
 *
 * @author dell
 * @version 4.1

 * <p>
 * History:
 *
 * Date                     Author         Version     Description
 * ---------------------------------------------------------------------------------
 * 2019年4月10日 下午5:50:53          dell        4.1         To create
 * </p>
 *
 * @since
 * @see
 */
public class EventPublishThread extends Thread {

    private EventQueue eventQueue;
    private RingBuffer<Event> ringBuffer;
    private String eventType;

    public EventPublishThread(String eventType, EventQueue eventQueue, RingBuffer<Event> ringBuffer) {
        this.ringBuffer = ringBuffer;
        this.eventQueue = eventQueue;
        this.eventType = eventType;
    }

    /* (non-Javadoc)
    * @see java.lang.Thread#run()
    */
    @Override
    public void run() {
        while (true) {
            String nextKey = null;
            if (nextKey == null) {
                nextKey = eventQueue.next();

            }
            if (nextKey != null) {
                final String key = nextKey;
                EventTranslator<Event> eventTranslator = (EventTranslator<Event>) (event, sequence) -> {
                    event.setEventType(eventType);
                    event.setKey(key);
                };
                //ringBuffer.publishEvent(event_tr, nextKey, eventType);
                ringBuffer.publishEvent(eventTranslator);
            }
        }
    }

  
}
