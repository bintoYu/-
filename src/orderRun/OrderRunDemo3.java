package orderRun;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 *  指定顺序执行线程
 *  方式三：使用线程池（推荐）
 */
public class OrderRunDemo3 {
    private static ExecutorService executor = Executors.newSingleThreadExecutor();

    final static Thread thread1 = new Thread(new Runnable() {
        @Override
        public void run() {
            System.out.println("A..................");
        }
    });

    final static Thread thread2 = new Thread(new Runnable() {
        @Override
        public void run() {
            System.out.println("B..........................");
        }
    });

    final static Thread thread3 = new Thread(new Runnable() {
        @Override
        public void run() {
            System.out.println("C......................");
        }
    });

    public static void main(String[] args) throws InterruptedException {
        executor.submit(thread1);
        executor.submit(thread2);
        executor.submit(thread3);

    }
}
