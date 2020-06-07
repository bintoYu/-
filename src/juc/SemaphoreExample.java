package juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.logging.Logger;

public class SemaphoreExample
{
	private final static int threadCount = 20;

	public static void main(String[] args)
	{
		ExecutorService exec = Executors.newCachedThreadPool();

		//�ڹ��캯�������ú���Դ��
		final Semaphore semaphore = new Semaphore(3);
		for(int i = 0; i < threadCount; i++){
			final int threadNum = i;
			exec.execute(() -> {
				try{
					semaphore.acquire();   //��ȡn����ɣ�1<n<3��Ĭ����1��������Դ����ĳ���̻߳�ȡʱ����������״̬
					test(threadNum);
					semaphore.release();	//�ͷ�n�����
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}

			});
		}
		exec.shutdown();
	}

	private static void test(int num)
	{
		try
		{
			Thread.sleep(1000);
		}
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(System.currentTimeMillis() + "\t" + num + " is ready");
	}
}
