package com.ultrapower.viedo.utils.thread;

public class PCApplication {
  public static void main(String[] args) throws InterruptedException {
	PCService service=new PCService();
	MyThreadConsume consume=new MyThreadConsume(service);
	MyThreadProduce produce=new MyThreadProduce(service);
	new Thread(produce, "生产者  ").start();
    new Thread(consume, "消费者  ").start();
}
}
