package com.ultrapower.viedo.utils.Netty.BIO;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TimeClient {
  public static void main(String[] args) {
	int port=8888;
	if (args!=null&&args.length>0) {
		port=Integer.valueOf(port);
	}
	Socket socket=null;
	BufferedReader in=null;
	PrintWriter out=null;
	try {
		socket=new Socket("127.0.0.1", port);
		in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out=new PrintWriter(socket.getOutputStream());
		out.println("QUERY TIME ORDER");
		System.out.println("send order 2 server succed");
		String resp=in.readLine();
		System.out.println("now is:"+resp);
	}catch (IOException e) {
		if (in!=null) {
			try {
				in.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		if (out!=null) {
			out.close();
			out=null;
		}
		if (socket!=null) {
			try {
				socket.close();
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			socket=null;
		}
	}
}
}
