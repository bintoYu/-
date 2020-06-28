package juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class Try
{
	public static void main(String[] args)
	{
		Thread thread = new Thread(()->{
			System.out.println("start thread...");
			while (true)
			{

			}
//			LockSupport.park();
//			System.out.println("unpark.....");
//			Thread.interrupted();
		});

		thread.start();

		thread.interrupt();

		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		LockSupport.unpark(thread);


	}
}
