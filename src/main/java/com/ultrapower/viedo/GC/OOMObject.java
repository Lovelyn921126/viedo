package com.ultrapower.viedo.GC;

import java.util.ArrayList;
import java.util.List;

public class OOMObject {
	public byte[] placeholder=new byte[64*1024];
  public static void main(String[] args) throws InterruptedException {
	 fillHeap(100);
	 System.gc();
	 System.out.println("执行完成");
	
	
}
  public static void fillHeap(int num) throws InterruptedException {
	  List<OOMObject> list=new ArrayList<>();
	  for (int i = 0; i < num; i++) {
			Thread.sleep(50);
			list.add(new OOMObject());
			
		}
	 
}
}
