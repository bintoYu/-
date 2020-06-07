package juc;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLock2
{
	//使用非公平锁
	ReentrantLock lock = new ReentrantLock(true);

	public void test(int i)
	{
		lock.lock();
		try
		{
			System.out.println("线程" + i +"已锁..............");
			Thread.sleep(2000);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			lock.unlock();
		}
		System.out.println("线程" + i +"解锁...............");
	}
}
