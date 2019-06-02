package com.ultrapower.viedo.utils.thread;

public class MyThreadConsume implements Runnable {
	
	public PCService service;
	public MyThreadConsume(PCService service) {
		// TODO Auto-generated constructor stub
		this.service=service;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		 for (;;) {
		service.consume();
		 }
	}

}
