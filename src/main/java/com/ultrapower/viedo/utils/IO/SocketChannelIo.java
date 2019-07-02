package com.ultrapower.viedo.utils.IO;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.TimeUnit;

public class SocketChannelIo {
  public static void main(String[] args) {
	client();
	server();
}
  public static void client() {
	ByteBuffer buffer=null;
	SocketChannel socketChannel=null;
	
	try {
		 socketChannel = SocketChannel.open();
		 socketChannel.configureBlocking(false);
		 socketChannel.connect(new InetSocketAddress("127.0.0.1",8080));
		 if (socketChannel.finishConnect()) {
			int i=0;
			while (true) {
				TimeUnit.SECONDS.sleep(1);
				 String info = "I'm "+i+++"-th information from client";
				 buffer.clear();
				 buffer.put(info.getBytes());
				 buffer.flip();
				 while (buffer.hasRemaining()) {
					  System.out.println(buffer);
                      socketChannel.write(buffer);
				}
			}
		}
	}catch (Exception e)
    {
        e.printStackTrace();
    }finally{
        try{
            if(socketChannel!=null){
                socketChannel.close();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
  public static void server() {
	  ServerSocket serverSocket=null;
	  InputStream in = null;
	  try {
		  serverSocket = new ServerSocket(8080);
		  int recvMsgSize = 0;
          byte[] recvBuf = new byte[1024];
          while (true) {
		Socket socket=	serverSocket.accept();
		 SocketAddress clientAddress = socket.getRemoteSocketAddress();
		  System.out.println("Handling client at "+clientAddress);
          in = socket.getInputStream();
          while((recvMsgSize=in.read(recvBuf))!=-1){
              byte[] temp = new byte[recvMsgSize];
              System.arraycopy(recvBuf, 0, temp, 0, recvMsgSize);
              System.out.println(new String(temp));
          }


		}
	} catch (Exception e) {
		// TODO: handle exception
	} finally{
        try{
            if(serverSocket!=null){
                serverSocket.close();
            }
            if(in!=null){
                in.close();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

	
}
}
