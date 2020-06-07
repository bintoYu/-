package juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.logging.Logger;

public class SemaphoreExample
{
	private final static int threadCount = 20;

	public static void main(String[] args)
	{
		ExecutorService exec = Executors.newCachedThreadPool();

		//在构造函数中设置好资源数
		final Semaphore semaphore = new Semaphore(3);
		for(int i = 0; i < threadCount; i++){
			final int threadNum = i;
			exec.execute(() -> {
				try{
					semaphore.acquire();   //获取n个许可（1<n<3，默认是1），当资源不够某个线程获取时，便进入堵塞状态
					test(threadNum);
					semaphore.release();	//释放n个许可
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}

			});
		}
		exec.shutdown();
	}

	private static void test(int num)
	{
		try
		{
			Thread.sleep(1000);
		}
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(System.currentTimeMillis() + "\t" + num + " is ready");
	}
}
