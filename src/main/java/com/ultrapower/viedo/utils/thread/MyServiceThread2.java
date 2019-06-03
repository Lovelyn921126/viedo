package com.ultrapower.viedo.utils.thread;

public class MyServiceThread2 implements Runnable {
	private MyService service;
	
	public MyServiceThread2(MyService myService) {
		// TODO Auto-generated constructor stub
		this.service=myService;
	}

	@Override
	public void run() {
		service.waitB();
		
	}
  
}
