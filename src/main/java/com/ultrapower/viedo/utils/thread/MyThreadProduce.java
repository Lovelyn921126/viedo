package com.ultrapower.viedo.utils.thread;

public class MyThreadProduce implements Runnable {
	
	public PCService service;
	public MyThreadProduce(PCService service) {
		// TODO Auto-generated constructor stub
		this.service=service;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		 for (;;) {
	            service.product();
	        }
		
	}

}
