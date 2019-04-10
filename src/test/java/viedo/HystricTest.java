/*
 * FileName: HystricTest.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package viedo;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import com.netflix.hystrix.HystrixCollapserProperties;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.netflix.hystrix.HystrixThreadPoolProperties;

import lombok.extern.slf4j.Slf4j;
import rx.Observable;
import rx.Observer;

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
 * 2019年3月27日 下午1:23:52          dell        4.1         To create
 * </p>
 *
 * @since
 * @see
 */
@Slf4j
public class HystricTest extends HystrixCommand<String> {
    private final String name;

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    /**
     * @param group
     */
    protected HystricTest(String name) {
        super(setter());

        this.name = name;
        // TODO Auto-generated constructor stub
    }

    /* (non-Javadoc)
     * @see com.netflix.hystrix.HystrixCommand#run()
     */
    @Override
    protected String run() throws Exception {
        int c = atomicInteger.getAndIncrement();
        /*   if (c < 10) {
            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {
            }

        }*/
        System.out.println("Hello " + name + " thread:" + Thread.currentThread().getName());
        // 依赖逻辑封装在run()方法中
        return "Hello " + name + " thread:" + Thread.currentThread().getName();
    }

    /* (non-Javadoc)
     * @see com.netflix.hystrix.HystrixCommand#getFallback()
     */
    @Override
    protected String getFallback() {
        // TODO Auto-generated method stub
        return null;
    }

    public static Setter setter() {
        HystrixCommandGroupKey groupKey = HystrixCommandGroupKey.Factory.asKey("stock");
        HystrixCommandKey commandKey = HystrixCommandKey.Factory.asKey("getStock");
        HystrixThreadPoolKey threadPoolKey = HystrixThreadPoolKey.Factory.asKey("stock-pool");
        HystrixCollapserProperties.Setter collasper = HystrixCollapserProperties.Setter().withCollapsingEnabled(true).withMaxRequestsInBatch(Integer.MAX_VALUE).withTimerDelayInMilliseconds(10).withRequestCacheEnabled(true);
        HystrixThreadPoolProperties.Setter threadProperties = HystrixThreadPoolProperties.Setter().withCoreSize(200).withKeepAliveTimeMinutes(5).withMaxQueueSize(Integer.MAX_VALUE).withQueueSizeRejectionThreshold(10);
        HystrixCommandProperties.Setter commandProperties = HystrixCommandProperties.Setter().withRequestCacheEnabled(true).withExecutionIsolationThreadTimeoutInMilliseconds(500).withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.THREAD).withCircuitBreakerEnabled(true).withCircuitBreakerErrorThresholdPercentage(50).withCircuitBreakerRequestVolumeThreshold(6);
        // return HystrixCommand.Setter.withGroupKey(groupKey).andCommandKey(commandKey).andThreadPoolKey(threadPoolKey).andThreadPoolPropertiesDefaults(threadProperties).andCommandPropertiesDefaults(commandProperties);
        return HystrixCommand.Setter.withGroupKey(groupKey).andCommandKey(commandKey).andCommandPropertiesDefaults(commandProperties);
    }

    public static void main(String[] args) throws InterruptedException {
        try {
            HystricTest hystricTest = new HystricTest("Synchronous-hystrix");
            String result = hystricTest.execute();
            System.out.println("result=" + result);
            hystricTest = new HystricTest("Asynchronous-hystrix");
            Future<String> future = hystricTest.queue();

            result = future.get(100, TimeUnit.MILLISECONDS);
            HystricTest observe = new HystricTest("Synchronous-hystrix--observe");
            Observable<String> ho = observe.observe();
            HystricTest toObservable = new HystricTest("Synchronous-hystrix--toObservable");
            Observable<String> co = toObservable.toObservable();
            ho.subscribe(new Observer<String>() {

                @Override
                public void onNext(String value) {
                    System.out.println("onNext:" + value);
                }

                @Override
                public void onError(Throwable e) {
                    System.out.println("onError=" + e.getMessage());
                }

                /* (non-Javadoc)
                 * @see rx.Observer#onCompleted()
                 */
                @Override
                public void onCompleted() {
                    System.out.println("onComplete()");

                }

            });
            co.subscribe();
            System.out.println("result=" + result);
            System.out.println("mainThread=" + Thread.currentThread().getName());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        /* int i = 1;
        for (; i < 15; i++) {
            HystricTest hystricTest = new HystricTest("Synchronous-hystrix");
            String result = hystricTest.execute();
            System.out.println(String.format("call %s times,result:%s,method:%s,isCircuitBreakerOpen:%s", i, result, "test", hystricTest.isCircuitBreakerOpen()));
            //log.debug("call {} times,result:{},method:{},isCircuitBreakerOpen:{}", i, result, "test", hystricTest.isCircuitBreakerOpen());
        }
        //等待6s，使得熔断器进入半打开状态
        Thread.sleep(6000);
        for (; i < 20; i++) {
            HystricTest hystricTest = new HystricTest("Synchronous-hystrix");
            String result = hystricTest.execute();
            // System.out.println("result=" + result);
            System.out.println(String.format("call %s times,result:%s,method:%s,isCircuitBreakerOpen:%s", i, result, "test", hystricTest.isCircuitBreakerOpen()));
            //log.debug("call {} times,result:{},method:{},isCircuitBreakerOpen:{}", i, result, "test", hystricTest.isCircuitBreakerOpen());
        }*/

    }

}
