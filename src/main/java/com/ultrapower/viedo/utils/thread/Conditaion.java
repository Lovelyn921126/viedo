package com.ultrapower.viedo.utils.thread;

public class Conditaion {
  public static void main(String[] args) throws InterruptedException {
	MyService myService=new MyService();
	MyServiceThread1 myServiceThread1=new MyServiceThread1(myService);
	MyServiceThread2 myServiceThread2=new MyServiceThread2(myService);
	new Thread(myServiceThread1,"a").start();
	new Thread(myServiceThread2,"b").start();  
	// 线程sleep2秒钟
    Thread.sleep(2000);
    // 唤醒所有持有conditionA的线程
    myService.singleA();
    
    Thread.sleep(2000);
    // 唤醒所有持有conditionB的线程
    myService.singleB();
}
}
