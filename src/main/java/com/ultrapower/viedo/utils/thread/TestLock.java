package com.ultrapower.viedo.utils.thread;

import java.util.Calendar;
import java.util.concurrent.locks.ReentrantLock;

public class TestLock {
    
	  private ReentrantLock lock = null;

	public TestLock() {
		lock=new ReentrantLock();
	}
	
	public ReentrantLock getLock() {
		return lock;
	}
	

	 public void testReentry() {
	        lock.lock();

	        Calendar now = Calendar.getInstance();

	        System.out.println(now.getTime() + " " + Thread.currentThread().getName()
	                + " get lock.");
	    }
	 
	 public static void main(String[] args) {
		TestLock testLock=new TestLock();
		try {
			testLock.testReentry();
			testLock.testReentry();
			testLock.testReentry();
		} finally {
			// TODO: handle finally clause
			testLock.getLock().unlock();
			testLock.getLock().unlock();
			testLock.getLock().unlock();
		}
	
	}

}
