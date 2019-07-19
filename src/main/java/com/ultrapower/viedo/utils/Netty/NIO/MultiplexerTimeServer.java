package com.ultrapower.viedo.utils.Netty.NIO;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import org.elasticsearch.action.DocWriteRequest;

import ch.qos.logback.classic.net.server.HardenedLoggingEventInputStream;

public class MultiplexerTimeServer implements Runnable {
	
	private Selector selector;
	
	private ServerSocketChannel servChannel;
	private volatile boolean stop;
	
	
	
	
	
  public MultiplexerTimeServer(int port) {
		try {
			selector=Selector.open();
			servChannel=ServerSocketChannel.open();
			servChannel.configureBlocking(false);
			servChannel.socket().bind(new InetSocketAddress(port),1024);
			servChannel.register(selector, SelectionKey.OP_ACCEPT);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
public void stop() {
	this.stop=true;
}


@Override
public void run() {
	// TODO Auto-generated method stub
	while (!stop) {
		try {
			//4
			selector.select(1000);
		Set<SelectionKey> sIteratorKeys=	 selector.selectedKeys();
		Iterator<SelectionKey> it=sIteratorKeys.iterator();
		SelectionKey key=null;
		while (it.hasNext()) {
			key=it.next();
			it.remove();
			try {
				handleInput(key);
			} catch (Exception e) {
				if (key!=null) {
					key.cancel();
					if (key.channel()!=null) {
						key.channel().close();
					}
				}
			}
		}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (selector!=null) {
				try {
					selector.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
}
private void handleInput(SelectionKey key) throws IOException {
    if (key.isValid()) {
		//处理请求
    	if (key.isAcceptable()) {
			//accept new connection
    		ServerSocketChannel ssc=(ServerSocketChannel) key.channel();
    		SocketChannel sc=ssc.accept();
    		sc.configureBlocking(false);
    		//add new connection to selectot
    		sc.register(selector, SelectionKey.OP_READ);
		}
	}
    if (key.isReadable()) {
		//read the data
    	SocketChannel sc=(SocketChannel) key.channel();
    	ByteBuffer buffer=ByteBuffer.allocate(1024);
    	int readBytes=sc.read(buffer);
    	if (readBytes>0) {
			buffer.flip();
			byte[] bs=new byte[buffer.remaining()];
			buffer.get(bs);
			String body=new String(bs, "UTF-8");
			System.out.println("The TIME server receive order:"+body);
			String currentTime="QUERY TIME ORDER".equalsIgnoreCase(body)?new Date(System.currentTimeMillis()).toString():"BAD ORDER";
			doWrite(sc,currentTime);
		}else if (readBytes<0) {
			key.cancel();
			sc.close();
		}
    	
	}
	
}
private void doWrite(SocketChannel sc, String currentTime) throws IOException {
	// TODO Auto-generated method stub
	if (currentTime!=null&&currentTime.trim().length()>0) {
	byte[] bs=currentTime.getBytes();
	ByteBuffer writeBuffer=ByteBuffer.allocate(bs.length);
	writeBuffer.put(bs);
	writeBuffer.flip();
	sc.write(writeBuffer);
	}
}
}
