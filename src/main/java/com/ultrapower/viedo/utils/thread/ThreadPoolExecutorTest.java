/*
 * FileName: ThreadPoolExecutorTest.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.utils.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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
 * 2019年6月4日 上午10:17:51          dell        4.1         To create
 * </p>
 *
 * @since
 * @see
 */
public class ThreadPoolExecutorTest {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 20, 200, TimeUnit.MICROSECONDS, new ArrayBlockingQueue<>(5));
        for (int i = 0; i < 15; i++) {
            executor.execute(new Worker(i));
            System.out.println("线程池中线程数目：" + executor.getCorePoolSize() + "，队列中等待执行的任务数目：" + executor.getQueue().size() + "，已执行玩别的任务数目：" + executor.getCompletedTaskCount());
        }
        executor.shutdown();
    }

    static class Worker implements Runnable {
        private int taskNum;

        public Worker(int num) {
            this.taskNum = num;
        }

        /* (non-Javadoc)
         * @see java.lang.Runnable#run()
         */
        @Override
        public void run() {

            try {
                System.out.println("正在执行task：" + taskNum);
                Thread.currentThread().sleep(2000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("task " + taskNum + "执行完毕");
        }

    }
}
