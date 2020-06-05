package juc;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class CyclicBarrierExample
{
	private final static int threadCount = 20;
	
	private static CyclicBarrier cyclicBarrier = new CyclicBarrier(5,()->{
		System.out.println("callback is running");
	});
	
	public static void main(String[] args) throws InterruptedException
	{
		ExecutorService exec = Executors.newCachedThreadPool();
		
		for(int i = 0; i < threadCount; i++){
			final int threadNum = i;
			Thread.sleep(1000);
			exec.execute(() -> {
				try
				{
					test(threadNum);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			});
		}
		exec.shutdown();
	}
	
	private static void test(int num) throws Exception
	{
		System.out.println(System.currentTimeMillis() + "\t" + num + " is ready");
		cyclicBarrier.await();
		System.out.println(System.currentTimeMillis() + "\t" + num + " is continue");
	}
}
