package juc;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLock1
{
	//Ĭ���Ƿǹ�ƽ��
	ReentrantLock lock = new ReentrantLock();

	public void test(int i)
	{
		lock.lock();
//		lock.tryLock();
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
