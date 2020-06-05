package other;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

//调用任务，要求200ms内返回结果或者空
public class 两百秒内返回结果或者空
{
	public static void main(String[] args)
	{
		ExecutorService exec = Executors.newCachedThreadPool();
		
		Future<Integer> future1 = exec.submit(new MyTask());

		Integer i1 = null;
		try
		{
			i1 = future1.get(200,TimeUnit.MILLISECONDS);

		}
		catch (InterruptedException | ExecutionException | TimeoutException e)
		{
			e.printStackTrace();
		}
		
		if(i1 == null)
			System.out.println("null");
		else
			System.out.println(i1);
	}

}


class MyTask implements Callable<Integer>
{
	@Override
	public Integer call() throws Exception
	{
		int sum = 0;
		for(int i = 0;i < 100; i++)
		{
			sum+=i;
		}
		Thread.sleep(300);
		return sum;
	}	
}