package com.ultrapower.viedo.utils.Netty.BIO;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TimeServerHandlerExecutePool {
  
	private ExecutorService executorService;
	
  public TimeServerHandlerExecutePool(int maxPoolSize,int queueSize) {
	executorService=new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), maxPoolSize, 120L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(queueSize));
}
  public void executr(Runnable task) {
	executorService.execute(task);
}
}
