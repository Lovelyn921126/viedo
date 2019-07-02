package com.ultrapower.viedo.utils.Proxy.Cglib;

import java.lang.reflect.Method;
import java.util.Arrays;

import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

@Slf4j
public class CgLib {
  public static void main(String[] args) {
	Enhancer enhancer=new Enhancer();
	enhancer.setSuperclass(HelloConcrete.class);
	enhancer.setCallback(new MyMethodInterceptor());
	HelloConcrete concrete=(HelloConcrete) enhancer.create();
	System.out.println(concrete.sayHello("I love you!"));
} 
  static class MyMethodInterceptor  implements MethodInterceptor{

	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		// TODO Auto-generated method stub
		log.info("You said: " + Arrays.toString(args));
		return proxy.invokeSuper(obj, args);
	}
  }
}
