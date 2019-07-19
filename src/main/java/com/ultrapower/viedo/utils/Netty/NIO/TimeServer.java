package com.ultrapower.viedo.utils.Netty.NIO;

public class TimeServer {
  public static void main(String[] args) {
	int port=8080;
	if (args!=null&&args.length>0) {
		try {
			port=Integer.valueOf(args[0]);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	MultiplexerTimeServer multiplexerTimeServer=new MultiplexerTimeServer(port);
	new Thread(multiplexerTimeServer, "multiplexerTimeServer-001").start();
}
}
