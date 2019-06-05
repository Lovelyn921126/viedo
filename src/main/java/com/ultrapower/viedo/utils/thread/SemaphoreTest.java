/*
 * FileName: SemaphoreTest.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.utils.thread;

import java.util.concurrent.Semaphore;

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
 * 2019年6月3日 下午1:19:39          dell        4.1         To create
 * </p>
 *
 * @since
 * @see
 */
public class SemaphoreTest {
    public static void main(String[] args) {
        int N = 8;
        Semaphore semaphore = new Semaphore(5);
        for (int i = 0; i < N; i++)
            new Worker(semaphore, i).start();
        ;

    }

    static class Worker extends Thread {
        private Semaphore semaphore;
        private int num;

        /**
        *
        */
        public Worker(Semaphore semaphore, int num) {
            // TODO Auto-generated constructor stub
            this.semaphore = semaphore;
            this.num = num;
        }

        /* (non-Javadoc)
         * @see java.lang.Runnable#run()
         */
        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println("工人" + this.num + "占用一个机器在生产...");
                Thread.sleep(2000);
                System.out.println("工人" + this.num + "释放出机器");
                semaphore.release();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }
}
