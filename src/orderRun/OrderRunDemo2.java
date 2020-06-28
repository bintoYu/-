package orderRun;

/**
 *  指定顺序执行线程
 *  方式二：主线程join
 */
public class OrderRunDemo2 {
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
        thread1.start();
        //在父进程调用子进程的join()方法后，父进程需要等待子进程运行完再继续运行。
        thread1.join();
        thread2.start();
        thread2.join();
        thread3.start();
    }
}
