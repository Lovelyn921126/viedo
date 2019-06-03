package com.ultrapower.viedo.utils.thread;

public class MyServiceThread1 implements Runnable {
	private MyService service;
	
	public MyServiceThread1(MyService myService) {
		// TODO Auto-generated constructor stub
		this.service=myService;
	}

	@Override
	public void run() {
		service.waitA();
		
	}
  
}
