package com.ultrapower.viedo.utils.Netty.BIO;import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TimeServe {
  public static void main(String[] args) throws IOException {
	int port=8888;
	if (args!=null&&args.length>0) {
		try {
			port=Integer.valueOf(args[0]);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	ServerSocket server=null;
	try {
		server=new ServerSocket(port);
		System.out.println("The time server is start in port:"+port);
		Socket socket=null;
		while (true) {
			System.out.println("The time server is start in port:"+port);
			socket=server.accept();
			System.out.println("The time server is start in port:"+port);
			new Thread(new TimeServerHandler(socket)).start();
			System.out.println("The time server is start in port:"+port);
		}
	} finally {
		if (server!=null) {
			System.out.println("the time server close");
			server.close();
			server=null;
		}
	}
}
}
