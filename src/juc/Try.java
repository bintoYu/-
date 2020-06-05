package juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Try
{
	public static void main(String[] args)
	{
		ReentrantLock1 test1 = new ReentrantLock1();
		ExecutorService exec = Executors.newCachedThreadPool();
		for(int i = 0; i < 5; i++)
		{
			int num = i;
			exec.execute(()->
			{
				test1.test(num);
			});
		}
	}
}
