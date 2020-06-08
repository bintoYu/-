package juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockUnfailTest {

    public static void main(String[] args) {
        //默认是非公平锁
        ReentrantLock lock = new ReentrantLock();

        //先生成一个线程1，先把锁抢了不放
        new Thread(()->{
            System.out.println("new Thread: " + 1);
            lock.lock();
            try {
                TimeUnit.SECONDS.sleep(10);
                System.out.println("print 1,,,,,,,,,,,,," );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            finally {
                lock.unlock();
            }
        },"t1").start();

        //生成线程t2->t10，会依次进入同步队列中等待（一般情况下不会依次进入，这里是刻意为之）。
        for(int i = 2; i <= 10; i++){
            final int num = i;
            new Thread(()->{
                System.out.println("new Thread: " + num);
                lock.lock();

                System.out.println("print " +  num  +",,,,,,,,,,,,," );

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    lock.unlock();
                }
            }).start();

            //暂停100ms，确保2-10这9个线程顺序进入AQS的同步队列中。
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
