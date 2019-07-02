package com.ultrapower.viedo.utils.Proxy.JDK;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

import org.apache.bcel.generic.NEW;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProxyHellow {
   public static void main(String[] args) {
	Hello hello=(Hello) Proxy.newProxyInstance(HellowImp.class.getClassLoader(), new Class<?>[] {Hello.class}, new LogInvocationHandler(new HellowImp()));
	hello.sayHello("ProxyHellow");
}  
  static class LogInvocationHandler  implements InvocationHandler{
     private Object ob;
     public LogInvocationHandler(Object ob) {
		// TODO Auto-generated constructor stub
    	 this.ob=ob;
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		 System.out.println("start-->>");  
	        for(int i=0;i<args.length;i++){  
	            System.out.println(args[i]);  
	        }  
	        Object ret=null;  
	        try{  
	            /*原对象方法调用前处理日志信息*/  
	            System.out.println("satrt-->>");  
	              
	            //调用目标方法  
	            ret=method.invoke(ob, args);  
	            /*原对象方法调用后处理日志信息*/  
	            System.out.println("success-->>");  
	        }catch(Exception e){  
	            e.printStackTrace();  
	            System.out.println("error-->>");  
	            throw e;  
	        }  
	        return ret;  
	}
	   
   }
}
