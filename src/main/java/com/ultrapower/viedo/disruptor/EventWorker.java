/*
 * FileName: EventWorker.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.disruptor;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;

import org.springframework.data.redis.core.RedisTemplate;

import com.google.common.collect.Maps;
import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.WorkHandler;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

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
 * 2019年4月10日 下午5:16:41          dell        4.1         To create
 * </p>
 *
 * @since
 * @see
 */
public class EventWorker {

    private Integer threadPoolSize;
    private Integer ringBufferSize;
    private Map<EventQueue, EventHandler> enentHandleMap;
    private Map<String, EventQueue> eventQueueMap;
    private Disruptor<Event> disruptor;
    private RingBuffer ringBuffer;
    private List<EventPublishThread> eventPublishThreads;

    public void init() {
        this.disruptor = new Disruptor<Event>(new DefaultEventFactory(), ringBufferSize, Executors.newFixedThreadPool(threadPoolSize), ProducerType.MULTI, new BlockingWaitStrategy());
        ringBuffer = disruptor.getRingBuffer();
        /* disruptor.handleExceptionsWith(new ExceptionHandler<RedisTemplate<String, Object>>() {
            public void handleEventException(Throwable ex, long sequence, T event) {
            }

            @Override
            public void handleEventException(Throwable ex, long sequence, RedisTemplate<String, Object> event) {
                // TODO Auto-generated method stub

            }

            @Override
            public void handleOnStartException(Throwable ex) {
                // TODO Auto-generated method stub

            }

            @Override
            public void handleOnShutdownException(Throwable ex) {
                // TODO Auto-generated method stub

            };
        });*/

        WorkHandler<Event> workHandler = new WorkHandler<Event>() {
            @Override
            public void onEvent(Event event) throws Exception {
                String type=event.getEventType();
                EventQueue queue = eventQueueMap.get(type);
                EventHandler handler = enentHandleMap.get(queue);
                handler.onEvent(event.getKey(), type, queue);
            };
        };
        WorkHandler[] workHandlers = new WorkHandler[threadPoolSize];
        for (int i = 0; i < workHandlers.length; i++) {
            workHandlers[i] = workHandler;
        }
        disruptor.handleEventsWithWorkerPool(workHandlers);
        disruptor.start();
        for (Map.Entry<String, EventQueue> entry : eventQueueMap.entrySet()) {
            String eventType=entry.getKey();
            EventQueue eventQueue=new
        }
    }

    public void stop() {
        for (iterable_type iterable_element : e) {

        }
    }

    /**
     * @param enentHandleMap the enentHandleMap to set
     */
    public void setEnentHandleMap(Map<EventQueue, EventHandler> enentHandleMap) {
        this.enentHandleMap = enentHandleMap;
        if (enentHandleMap != null) {
            this.eventQueueMap = Maps.newHashMap();
            for (Map.Entry<EventQueue, EventHandler> entry : enentHandleMap.entrySet()) {
                EventQueue queue = entry.getKey();
                this.eventQueueMap.put(queue.getQueueName(), queue);

            }
        }

    }

    /**
     * @return the threadPoolSize
     */
    public Integer getThreadPoolSize() {
        return threadPoolSize;
    }

    /**
     * @param threadPoolSize the threadPoolSize to set
     */
    public void setThreadPoolSize(Integer threadPoolSize) {
        this.threadPoolSize = threadPoolSize;
    }

    /**
     * @return the ringBufferSize
     */
    public Integer getRingBufferSize() {
        return ringBufferSize;
    }

    /**
     * @param ringBufferSize the ringBufferSize to set
     */
    public void setRingBufferSize(Integer ringBufferSize) {
        this.ringBufferSize = ringBufferSize;
    }

    /**
     * @return the enentHandleMap
     */
    public Map<EventQueue, EventHandler> getEnentHandleMap() {
        return enentHandleMap;
    }

}
