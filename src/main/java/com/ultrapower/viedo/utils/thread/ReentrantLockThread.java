package com.ultrapower.viedo.utils.thread;

import java.util.concurrent.locks.ReentrantLock;


public class ReentrantLockThread implements Runnable {
   ReentrantLock  lock=new ReentrantLock();
	@Override
	public void run() {
		try {
			lock.lock();
			// TODO Auto-generated method stub
			 for (int i = 0; i < 3; i++) {
				System.out.println("this thread name is :"+Thread.currentThread().getName()+"--"+i);
			}
		} finally {
			lock.unlock();
		}
		
	}

}
