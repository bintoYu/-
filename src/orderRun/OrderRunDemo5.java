package orderRun;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *  指定顺序执行线程
 *  方式五：使用condition，n个线程就要使用n-1个锁
 *  因为需要添加两个标识状态，所以该场景不建议使用condition
 */
public class OrderRunDemo5 {
    private static Lock lock = new ReentrantLock();
    private static Condition condition1 = lock.newCondition();
    private static Condition condition2 = lock.newCondition();

    /**
     * 为什么要加这两个标识状态?
     * 如果没有状态标识，当t1已经运行完了t2才运行，t2在等待t1唤醒导致t2永远处于等待状态
     */
    private static Boolean t1Run = false;
    private static Boolean t2Run = false;

    final static Thread thread1 = new Thread(new Runnable() {
        @Override
        public void run() {
            lock.lock();
            try {
                System.out.println("A..................");
                t1Run = true;
                condition1.signal();
            }finally {
                lock.unlock();
            }
        }
    });

    final static Thread thread2 = new Thread(new Runnable() {
        @Override
        public void run() {
            lock.lock();
            try {
                if(!t1Run) {
                    condition1.await();
                }
                System.out.println("B..................");
                t2Run = true;
                condition2.signal();
            }catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
    });

    final static Thread thread3 = new Thread(new Runnable() {
        @Override
        public void run() {
            lock.lock();
            try {
                if(!t2Run) {
                    condition2.await();
                }
                System.out.println("C..................");
            }catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
    });

    public static void main(String[] args) throws InterruptedException {
        thread2.start();
        thread3.start();
        thread1.start();
    }
}
