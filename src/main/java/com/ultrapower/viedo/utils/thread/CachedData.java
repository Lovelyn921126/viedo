package com.ultrapower.viedo.utils.thread;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CachedData {
  
	private boolean isCheck=false;
	public ReentrantReadWriteLock lock=new ReentrantReadWriteLock();
	
	public void processCachedData() {
		lock.readLock().lock(); //获取读锁
		if (!isCheck) {
			 lock.readLock().unlock();  //获取写锁之前 要释放读锁
			 lock.writeLock().lock();
			 if (!isCheck) {
				System.out.println(" write data----------");
				isCheck=true;
			}
			 lock.readLock().lock();  //写锁 降级为 读锁 
			 lock.writeLock().unlock();// 释放写锁
		}
		lock.readLock().unlock();//释放读锁
	}
}
