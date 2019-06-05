/*
 * FileName: CompareAndSwap.java
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
 * 2019年6月3日 下午3:45:14          dell        4.1         To create
 * </p>
 *
 * @since
 * @see
 */
public class CompareAndSwap {
    static class CompareAndSwapDemo {
        private int value = 0;

        /**
        * @return the value
        */
        public int getValue() {
            return value;
        }

        /**
        * @param value the value to set
        */
        public void setValue(int value) {
            this.value = value;
        }

        public synchronized int compareAndSwap(int expectValue, int newValue) {
            int oldValue = getValue();
            if (expectValue == oldValue) {
                this.value = newValue;
            }
            return oldValue;
        }

        public boolean compareAndSet(int expectValue, int newValue) {
            return getValue() == compareAndSwap(expectValue, newValue);

        }
    }

    public static void main(String[] args) {
        CompareAndSwapDemo demo = new CompareAndSwapDemo();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                int expectedValue = demo.getValue();
                boolean b = demo.compareAndSet(expectedValue, (int) (Math.random() * 100));
                System.out.println(b);
                System.out.println(demo.getValue());
            }).start();

        }
    }

}
