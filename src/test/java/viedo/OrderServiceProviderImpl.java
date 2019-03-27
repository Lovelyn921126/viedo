/*
 * FileName: OrderServiceProviderImpl.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package viedo;

import java.util.concurrent.atomic.AtomicInteger;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

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
 * 2019年3月27日 下午4:03:05          dell        4.1         To create
 * </p>
 *
 * @since
 * @see
 */
@Slf4j
public class OrderServiceProviderImpl extends HystrixCommand<Integer> {
    /**
     * @param group
     */
    protected OrderServiceProviderImpl(int key) {
        super(HystrixCommandGroupKey.Factory.asKey(key + ""));
        // TODO Auto-generated constructor stub
    }

    //private final static Logger logger = LoggerFactory.getLogger(OrderServiceProviderImpl.class);
    private AtomicInteger OrderIdCounter = new AtomicInteger(0);

    /* (non-Javadoc)
     * @see com.netflix.hystrix.HystrixCommand#run()
     */
    @Override
    protected Integer run() throws Exception {
        int c = OrderIdCounter.getAndIncrement();
        if (log.isDebugEnabled()) {
            log.debug("OrderIdCounter:{}", c);
        }
        if (c < 10) {
            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {
            }
        }
        return c;
    }

}
