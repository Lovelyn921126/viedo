package com.ultrapower.viedo.utils.thread;



import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MyService {
	// 实例化一个ReentrantLock对象
    private ReentrantLock lock = new ReentrantLock();
    // 为线程A注册一个Condition
    public Condition conditionA = lock.newCondition();
    // 为线程B注册一个Condition
    public Condition conditionB = lock.newCondition();
  public void waitA() {
	
	
	  try {
          lock.lock();
          System.out.println(Thread.currentThread().getName() + "进入了awaitA方法");
          long timeBefore = System.currentTimeMillis();
          // 执行conditionA等待
          conditionA.await();
          long timeAfter = System.currentTimeMillis();
          System.out.println(Thread.currentThread().getName()+"被唤醒");
          System.out.println(Thread.currentThread().getName() + "等待了: " + (timeAfter - timeBefore)/1000+"s");
      } catch (InterruptedException e) {
          e.printStackTrace();
      } finally {
          lock.unlock();
      }
     
}
  public void waitB() {
		
		
	     try {
	    	 lock.lock();
	    	 System.out.println(Thread.currentThread().getName() + "进入了waitB方法");
	         long timeBefore = System.currentTimeMillis();
	         
			conditionB.await();
			long timeAfter = System.currentTimeMillis();
			System.out.println(Thread.currentThread().getName() + "被唤醒");
			System.out.println(Thread.currentThread().getName() + "等待了: " + (timeAfter - timeBefore)/1000+"s");
			
	
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
	     
	}
	  
  public void singleA() {
	  try {
		  lock.lock();
		  System.out.println("启动唤醒程序");
		  conditionA.signalAll();
	} finally {
		// TODO: handle finally clause
		lock.unlock();
	}
	
}
  public void singleB() {
	  try {
		  lock.lock();
		  System.out.println("启动唤醒程序");
		  conditionB.signalAll();
	} finally {
		// TODO: handle finally clause
		lock.unlock();
	}
	
}
  
}
