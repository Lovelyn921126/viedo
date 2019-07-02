package com.ultrapower.viedo.utils.IO;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileIo {
public static void main(String[] args) {
	method2();
}
public static void method2() {
	  InputStream in = null;
	  try {
		in=new BufferedInputStream(new FileInputStream("D:\\test\\bulid.xml") );
		 byte [] buf = new byte[1024];
		 int bytesRead = in.read(buf);
		while (bytesRead!=-1) {
			 for(int i=0;i<bytesRead;i++)
                 System.out.print((char)buf[i]);
		}
	} catch (Exception e) {
		// TODO: handle exception
	}finally {
		try{
            if(in != null){
                in.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }

	}
}
public static void method1() {
	 RandomAccessFile file=null;
	 try {
		file=new RandomAccessFile("D:\\test\\bulid.xml","rw");
	ByteBuffer buffer=ByteBuffer.allocate(1024);
	FileChannel channel= file.getChannel();
	
	int bytesRead =	channel.read(buffer);
	while (bytesRead!=-1) {
		buffer.flip();
		while (buffer.hasRemaining()) {
			System.out.println((char)buffer.get());
		}
		buffer.compact();
		bytesRead =	channel.read(buffer);
	}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
        try{
            if(file != null){
            	file.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }

	
	
}
}
}
