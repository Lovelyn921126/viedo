/*
 * FileName: EventPublishThread.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.disruptor;

import com.lmax.disruptor.RingBuffer;

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
 * 2019年4月10日 下午5:50:53          dell        4.1         To create
 * </p>
 *
 * @since
 * @see
 */
public class EventPublishThread extends Thread {

    private EventQueue eventQueue;
    private RingBuffer ringBuffer;

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
                ringBuffer.publishEvent(event_tr, nextKey, e);
            }
        }
    }
}
