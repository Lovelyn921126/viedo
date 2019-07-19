package com.ultrapower.viedo.utils.Netty.NIO;

import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import com.mysql.jdbc.DocsConnectionPropsHelper;

public class TimeClientHnder implements Runnable {
  
	private String host;
	private int port;
	private Selector selector;
	private SocketChannel socketChannel;
	private volatile boolean stop;
	
	public TimeClientHnder(String host, int port) {
		super();
		this.host = host;
		this.port = port;
		try {
			socketChannel=SocketChannel.open();
			selector=Selector.open();
			socketChannel.configureBlocking(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			doConnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		while (!stop) {
			try {
				selector.select(1000);
				Set<SelectionKey> selectionKeys=selector.selectedKeys();
				Iterator<SelectionKey> it= selectionKeys.iterator();
				SelectionKey key=null;
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
	}

	private void doConnect() {
		// TODO Auto-generated method stub
		
	}
   
}
