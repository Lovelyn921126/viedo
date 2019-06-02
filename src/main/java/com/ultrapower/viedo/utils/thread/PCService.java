package com.ultrapower.viedo.utils.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class PCService {
  
	public ReentrantLock lock=new ReentrantLock();
	public Condition conditaion=lock.newCondition();
	
	public boolean flag=false;
	public int number=0;
	
	public void product() {
		try {
			lock.lock();
			/*while  (flag) {
				conditaion.await();
			}*/
			  System.out.println(Thread.currentThread().getName() + "-----生产-----");
			number++;
			  System.out.println("number: " + number);
	            System.out.println();
	            flag=true;
	            conditaion.signalAll();
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
            lock.unlock();
        }
	}
	public void consume() {
		try {
			lock.lock();
			while (number<=0) {
				conditaion.await();
			}
			System.out.println(Thread.currentThread().getName() + "-----消费-----");
			number--;
			System.out.println("number: " + number);
	        System.out.println();
	        flag=false;
	        conditaion.signalAll();
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
            lock.unlock();
        }
	}
}
