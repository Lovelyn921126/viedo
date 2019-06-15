package com.ultrapower.viedo.GC;

public class DisplayTest {
	public static int i=1;
     static {
	  i=0;
	 
    }
    
  static class sub extends DisplayTest{
	  public static int b=i;
  }
 public static void main(String[] args) {
	 System.out.println(sub.i);
	System.out.println(sub.b);
}
 
}
