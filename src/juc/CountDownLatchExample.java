package juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchExample
{
	private final static int threadNum = 20;
	
	public static void main(String[] args) throws InterruptedException
	{
		ExecutorService exec = Executors.newCachedThreadPool();
		
		CountDownLatch latch = new CountDownLatch(threadNum);
		
		for(int i = 0; i < threadNum; i++)
		{
			final int threadNo = i;
			exec.execute(() -> {
				test(threadNo);
				latch.countDown();	
			});
		}
		
		latch.await();
		System.out.println("Finished");
	}
	
	private static void test(int num)
	{	
		try
		{
			Thread.sleep(1000);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		System.out.println(System.currentTimeMillis() + "\t" + num + " is ready");
	}
}
