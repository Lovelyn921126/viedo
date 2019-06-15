package com.ultrapower.viedo.GC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;



public class CurrentBusy {
  public static void CurrentBusytHhread() {
	  Thread thread=new Thread(()->{
		 while (true) {
			// System.out.println(Thread.currentThread().getName());
			;
		} 
	  },"testBusyThread");
	  thread.start();

}
  public static void CurrentLocktHhread(final Object lock) {
	Thread thread=new Thread(()->{
		
		synchronized (lock) {
			try {
				lock.wait();
				System.out.println(Thread.currentThread().getName());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	},"testLockThread" ) ;
	thread.start();
	
}
  public static void main(String[] args) throws IOException {
	BufferedReader bReader=new BufferedReader(new InputStreamReader(System.in));
	bReader.readLine();
	CurrentBusytHhread();
	bReader.readLine();
	Object object=new Object();
	CurrentLocktHhread(object);
	System.out.println("zhixxx");
}
}
