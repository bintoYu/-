package other;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Atomic
{
	private static AtomicInteger ai = new AtomicInteger(0);
	private static volatile int i = 0;
	
	public static void main(String[] args)
	{
		ExecutorService exec = Executors.newCachedThreadPool();
		for(int j = 0; j < 100; j++)
		{
			exec.execute(() ->
			{
				for(int k = 0; k < 100; k++)
				{
					i++;
					ai.incrementAndGet();
				}
			});
		}
		
		try
		{
			Thread.sleep(1000);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		System.out.println("AtomicInteger:"  + ai.get());
		System.out.println("Integer:" + i);
	}
	
}
