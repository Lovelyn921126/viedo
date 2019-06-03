package com.ultrapower.viedo.utils.thread;

public class TestFair {
  public static void main(String[] args) {
	final isFairReentrantLock lock=new isFairReentrantLock(false);
	Runnable runnable=new Runnable() {
		
		@Override
		public void run() {
			System.out.println("**线程： " + Thread.currentThread().getName()  
                    +  " 运行了 " );  
			lock.isFailrMethod();  
			
		}
	};
	Thread[] threads=new Thread[10];
	for (int i = 0; i < 10; i++) {
		threads[i] = new Thread(runnable);  
	}
	for (int i = 0; i < threads.length; i++) {
		threads[i].start();
	}
}
}
