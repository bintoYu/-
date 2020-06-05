package other;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DeadLock
{
	public static void main(String[] args)
	{
		Object lock1 = new Object();
		Object lock2 = new Object();
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(() ->{
			synchronized(lock1)
			{
				System.out.println("get lock1,want lock2...");
					
				try
				{
					Thread.sleep(1000);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}	//try-catchºöÂÔ
				
				synchronized (lock2)
				{
					System.out.println("get lock2");
				}
			}
		}
		);
		
		exec.execute(()->{
			synchronized (lock2)
			{
				System.out.println("get lock2,want lock1....");
					
				try
				{
					Thread.sleep(1000);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}	//try-catchºöÂÔ

				synchronized (lock1)
				{
					System.out.println("get lock1");
				}
			}
		});
	}
}
