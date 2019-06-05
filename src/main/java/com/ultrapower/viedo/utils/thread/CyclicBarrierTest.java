/*
 * FileName: CyclicBarrier.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.utils.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

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
 * 2019年6月3日 下午12:47:51          dell        4.1         To create
 * </p>
 *
 * @since
 * @see
 */
public class CyclicBarrierTest {
    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(4, () -> {
            System.out.println("当前线程" + Thread.currentThread().getName());
        });
        int N = 4;
        for (int i = 0; i < N; i++) {
            /*if (i < N - 1) {
                new Worker(barrier).start();
            } else {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                new Worker(barrier).start();
            }*/
            new Worker(barrier).start();
        }
        try {
            Thread.sleep(25000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("CyclicBarrier重用");

        for (int i = 0; i < N; i++) {
            new Worker(barrier).start();
        }

    }

    static class Worker extends Thread {
        private CyclicBarrier cyclicBarrier;

        /**
         *
         */
        public Worker(CyclicBarrier cyclicBarrier) {
            // TODO Auto-generated constructor stub
            this.cyclicBarrier = cyclicBarrier;
        }

        /* (non-Javadoc)
        * @see java.lang.Thread#run()
        */
        @Override
        public void run() {
            System.out.println("线程" + Thread.currentThread().getName() + "正在写入数据...");
            try {
                Thread.sleep(5000); //以睡眠来模拟写入数据操作
                System.out.println("线程" + Thread.currentThread().getName() + "写入数据完毕，等待其他线程写入完毕");
                try {
                    cyclicBarrier.await(2000, TimeUnit.MILLISECONDS);
                } catch (TimeoutException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("所有线程写入完毕，继续处理其他任务...");
        }
    }
}
