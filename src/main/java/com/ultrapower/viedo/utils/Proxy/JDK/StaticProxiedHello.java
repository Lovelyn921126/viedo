package com.ultrapower.viedo.utils.Proxy.JDK;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StaticProxiedHello implements Hello {
	private Hello hello = new HellowImp();

	@Override
	public String sayHello(String str) {
		// TODO Auto-generated method stub
		 log.info("You said: " + str);
		return hello.sayHello(str);
	}

}
