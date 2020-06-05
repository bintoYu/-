package juc;

import java.util.LinkedList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Depot
{
	ReentrantLock lock = new ReentrantLock();
	Condition notFull = lock.newCondition();
	Condition notEmpty = lock.newCondition();

	LinkedList<Integer> queue;
	int limit;

	public Depot(int limit)
	{
		queue = new LinkedList<>();
		this.limit = limit;
	}

	public void produce(int i)
	{
		lock.lock();
		try
		{
//			System.out.println("����" + i);

			//��������
			if(queue.size() == limit)
//			{
//				System.out.println("������������������");
				notFull.await();
//				System.out.println(i+ "�ѱ�����");

//			}

			queue.offer(i);
			notEmpty.signal();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			lock.unlock();
		}
	}

	public int consume()
	{
		int num = -1;
		lock.lock();
		try
		{
			//��������
			if(queue.size() == 0)
//			{
//				System.out.println("�����ѿգ���������");
				notEmpty.await();
//				System.out.println("�ѱ�����");
//			}

			num = queue.poll();
//			System.out.println("���ѣ�"+num);
			notFull.signal();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			lock.unlock();
		}
		return num;
	}

	public static void main(String[] args)
	{
		ExecutorService exec = Executors.newCachedThreadPool();
		Depot depot = new Depot(5);
		for(int i = 0; i < 3; i++)
		{
			final int num = i;
			exec.execute(()->
			{

				depot.produce(num);
			});
		}

		try
		{
			Thread.sleep(200);
		}
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for(int i = 0; i < 7; i++)
		{
			final int num = i;
			exec.execute(()->
			{
				depot.consume();
			});
		}

		try
		{
			Thread.sleep(200);
		}catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for(int i = 0; i < 3; i++)
		{
			final int num = i;
			exec.execute(()->
			{

				depot.produce(num);
			});
		}
	}
}
