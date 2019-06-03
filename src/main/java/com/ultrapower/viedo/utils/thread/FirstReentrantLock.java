package com.ultrapower.viedo.utils.thread;

/**
 * @author Administrator
 *
 */
public class FirstReentrantLock {
  
	
	public static void main(String[] args) {
		ReentrantLockThread thread=new ReentrantLockThread();
		new Thread(thread).start();
		new Thread(thread).start();
	}
}
