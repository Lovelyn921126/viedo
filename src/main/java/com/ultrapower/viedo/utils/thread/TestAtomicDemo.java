/*
 * FileName: TestAtomicDemo.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.utils.thread;

import java.util.concurrent.atomic.AtomicInteger;

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
 * 2019年6月3日 下午3:25:40          dell        4.1         To create
 * </p>
 *
 * @since
 * @see
 */
public class TestAtomicDemo {
    public static void main(String[] args) {
        AtomicDemo ad = new AtomicDemo();
        for (int i = 0; i < 10; i++) {
            new Thread(ad).start();
        }
    }

    static class AtomicDemo implements Runnable {
        //private int serialNumber = 0;
        private AtomicInteger serialNumber = new AtomicInteger();

        @Override
        public void run() {
            try {
                Thread.sleep(200);
                System.out.println(Thread.currentThread().getName() + ":" + getSerialNumber());
            } catch (InterruptedException e) {

            }

        }

        public int getSerialNumber() {
            // return serialNumber++;
            return serialNumber.getAndIncrement();
        }
    }
}
