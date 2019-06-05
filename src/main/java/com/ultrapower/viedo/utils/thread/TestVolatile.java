/*
 * FileName: TestVolatile.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.utils.thread;

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
 * 2019年6月3日 下午2:39:20          dell        4.1         To create
 * </p>
 *
 * @since
 * @see
 */
public class TestVolatile {
    public static void main(String[] args) {
        ThreadDemo demo = new ThreadDemo();
        new Thread(demo).start();
        int n = 8;
        while (true) {
            //synchronized (Integer.class) {
            System.out.println("=====");
            if (demo.isFlag()) {
                System.out.println("########");
                break;
            }
            //}
        }
    }

    static class ThreadDemo implements Runnable {
        public volatile boolean flag = false;

        /* (non-Javadoc)
         * @see java.lang.Runnable#run()
         */
        @Override
        public void run() {
            // TODO Auto-generated method stub
            System.out.println("flag=" + isFlag());
            //flag = true;
            try {
                Thread.sleep(20);
                flag = true;
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("flag=" + isFlag());
        }

        /**
         * @return the flag
         */
        public boolean isFlag() {
            return flag;
        }

        /**
         * @param flag the flag to set
         */
        public void setFlag(boolean flag) {
            this.flag = flag;
        }

    }
}
