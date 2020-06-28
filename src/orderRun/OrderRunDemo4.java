package orderRun;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *  指定顺序执行线程
 *  方式三：使用CountDownLatch (推荐)，n个线程就要使用n-1个锁（同样也可以其他JUC来实现）
 */
public class OrderRunDemo4 {
    private static CountDownLatch countDownLatch1 = new CountDownLatch(1);
    private static CountDownLatch countDownLatch2 = new CountDownLatch(1);

    final static Thread thread1 = new Thread(new Runnable() {
        @Override
        public void run() {
                System.out.println("A..................");
                countDownLatch1.countDown();
        }
    });

    final static Thread thread2 = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                countDownLatch1.await();
                System.out.println("B..................");
                countDownLatch2.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    });

    final static Thread thread3 = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                countDownLatch2.await();
                System.out.println("C..................");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    });

    public static void main(String[] args) throws InterruptedException {
        thread2.start();
        thread3.start();
        thread1.start();

    }
}
