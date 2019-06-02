package com.ultrapower.viedo.utils.thread;

import java.util.concurrent.locks.ReentrantLock;

public class isFairReentrantLock {
  
	private ReentrantLock lock =null;
	public isFairReentrantLock(boolean isfair) {
		lock=new ReentrantLock(isfair);
	}
	
	public void isFailrMethod() {
		try {
			lock.lock();
			 System.out.println("ThreadName=" + Thread.currentThread().getName()  
	                 + " 获得锁定");  
		} finally {
			// TODO: handle finally clause
			lock.unlock();
		}
		
	}
	
}
