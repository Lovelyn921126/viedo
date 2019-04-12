/*
 * FileName: EventWorker.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.disruptor;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Executors;

import com.google.common.collect.Maps;
import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.WorkHandler;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import flex.messaging.io.ArrayList;
import lombok.extern.slf4j.Slf4j;

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
@Slf4j
public class EventWorker {

    private Integer threadPoolSize;
    private Integer ringBufferSize;
    private Map<EventQueue, EventHandler<Event>> enentHandleMap;
    private Map<String, EventQueue> eventQueueMap;
    private Disruptor<Event> disruptor;
    private RingBuffer<Event> ringBuffer;
    private List<EventPublishThread> eventPublishThreads = new ArrayList();

    @SuppressWarnings("deprecation")
    public void init() {
        System.out.println("EventWorker---init----");
        log.info("EventWorker---init----");
        //Disruptor 通过 java.util.concurrent.ExecutorService 提供的线程来触发 Consumer 的事件处理
        //指定等待策略
        /*Disruptor 定义了 com.lmax.disruptor.WaitStrategy 接口用于抽象 Consumer 如何等待新事件，这是策略模式的应用。
        Disruptor 提供了多个 WaitStrategy 的实现，每种策略都具有不同性能和优缺点，根据实际运行环境的 CPU 的硬件特点选择恰当的策略，并配合特定的 JVM 的配置参数，能够实现不同的性能提升。
        例如，BlockingWaitStrategy、SleepingWaitStrategy、YieldingWaitStrategy 等，其中，
        BlockingWaitStrategy 是最低效的策略，但其对CPU的消耗最小并且在各种不同部署环境中能提供更加一致的性能表现；
        SleepingWaitStrategy 的性能表现跟 BlockingWaitStrategy 差不多，对 CPU 的消耗也类似，但其对生产者线程的影响最小，适合用于异步日志类似的场景；
        YieldingWaitStrategy 的性能是最好的，适合用于低延迟的系统。在要求极高性能且事件处理线数小于 CPU 逻辑核心数的场景中，推荐使用此策略；例如，CPU开启超线程的特性。*/
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
                String type = event.getEventType();
                EventQueue queue = eventQueueMap.get(type);
                EventHandler<Event> handler = enentHandleMap.get(queue);
                handler.onEvent(event, Long.valueOf(event.getKey()), true);
            };
        };
        WorkHandler<Event>[] workHandlers = new WorkHandler[threadPoolSize];
        for (int i = 0; i < workHandlers.length; i++) {
            workHandlers[i] = workHandler;
        }
        disruptor.handleEventsWithWorkerPool(workHandlers);
        disruptor.start();
        for (Map.Entry<String, EventQueue> entry : eventQueueMap.entrySet()) {
            String eventType = entry.getKey();
            EventQueue eventQueue = entry.getValue();
            EventPublishThread thread = new EventPublishThread(eventType, eventQueue, ringBuffer);
            eventPublishThreads.add(thread);
            thread.start();
        }
    }

    public void stop() {
        for (EventPublishThread eventPublishThread : eventPublishThreads) {
            eventPublishThread.stop();
        }
        disruptor.shutdown();
    }

    /**
     * @param enentHandleMap the enentHandleMap to set
     */
    public void setEnentHandleMap(Map<EventQueue, EventHandler<Event>> enentHandleMap) {
        this.enentHandleMap = enentHandleMap;
        if (enentHandleMap != null) {
            this.eventQueueMap = Maps.newHashMap();
            for (Entry<EventQueue, EventHandler<Event>> entry : enentHandleMap.entrySet()) {
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
    public Map<EventQueue, EventHandler<Event>> getEnentHandleMap() {
        return enentHandleMap;
    }

}
