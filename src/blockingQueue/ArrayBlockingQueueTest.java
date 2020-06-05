package blockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ArrayBlockingQueueTest
{
	public static ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5);
	
	public static void main(String[] args)
	{
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(()->
		{
			try
			{
				System.out.println(ArrayBlockingQueueTest.queue.hashCode());
				ArrayBlockingQueueTest.queue.take();
			}
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		exec.execute(()->
		{
			try
			{
				System.out.println(ArrayBlockingQueueTest.queue.hashCode());
				Thread.sleep(2000);
				ArrayBlockingQueueTest.queue.put(1);
				System.out.println("put 1");
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		});
	}
}
