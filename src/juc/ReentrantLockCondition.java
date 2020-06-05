package juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

//condition不仅可以实现睡眠/唤醒机制
//还可以实现分组唤醒
public class ReentrantLockCondition
{
	ReentrantLock lock = new ReentrantLock(true);
	Condition conditionA = lock.newCondition();
	Condition conditionB = lock.newCondition();

	public void testWaitA(int i)
	{
		lock.lock();
		try
		{
			System.out.println("线程" + i +"已睡眠..............");
			conditionA.await();
			System.out.println("线程" + i +"已苏醒..............");
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			lock.unlock();
		}
	}


	public void testSignalA(int i)
	{
		lock.lock();
		try
		{
			System.out.println("唤醒操作");
			conditionA.signalAll();
		}
		finally
		{
			lock.unlock();
		}
	}

	public void testWaitB(int i)
	{
		lock.lock();
		try
		{
			System.out.println("线程" + i +"已睡眠..............");
			conditionB.await();
			System.out.println("线程" + i +"已苏醒..............");
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			lock.unlock();
		}
	}


	public void testSignalB(int i)
	{
		lock.lock();
		try
		{
			System.out.println("唤醒操作");
			conditionB.signalAll();
		}
		finally
		{
			lock.unlock();
		}
	}


	public static void main(String[] args)
	{
		ReentrantLockCondition test1 = new ReentrantLockCondition();
		ExecutorService exec = Executors.newCachedThreadPool();
		for(int i = 0; i < 10;i++)
		{
			int num = i;
			if(num < 5)
			{
				exec.execute(()->
				{
					test1.testWaitA(num);
				});
			}
			else
			{
				exec.execute(()->
				{
					test1.testWaitB(num);
				});
			}
		}

		try
		{
			Thread.sleep(1000);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}

		exec.execute(()->
		{
			test1.testSignalA(10);
		});

		try
		{
			Thread.sleep(1000);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}

		exec.execute(()->
		{
			test1.testSignalB(11);
		});
	}
}
