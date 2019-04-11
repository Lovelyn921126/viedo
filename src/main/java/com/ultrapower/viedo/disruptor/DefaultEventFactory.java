/*
 * FileName: DefaultEventFactory.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.disruptor;

import com.lmax.disruptor.EventFactory;

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
 * 2019年4月10日 下午5:35:16          dell        4.1         To create
 * </p>
 *
 * @since
 * @see
 */
public class DefaultEventFactory implements EventFactory<Event> {

    /* (non-Javadoc)
     * @see com.lmax.disruptor.EventFactory#newInstance()
     */
    @Override
    public Event newInstance() {
        // TODO Auto-generated method stub
        return new Event();
    }

}
