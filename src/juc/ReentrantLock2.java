package juc;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLock2
{
	//���ù�ƽ��
	ReentrantLock lock = new ReentrantLock(true);
	
	public void test(int i)
	{
		lock.lock();
		try
		{
			System.out.println("�߳�" + i +"����..............");
			Thread.sleep(2000);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			lock.unlock();
		}
		System.out.println("�߳�" + i +"����...............");
	}
}
