package orderRun;

/**
 *  指定顺序执行线程
 *  方式一：子线程join （推荐）
 */
public class OrderRunDemo1 {
    final static Thread thread1 = new Thread(new Runnable() {
        @Override
        public void run() {
            System.out.println("A..................");
        }
    });

    final static Thread thread2 = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                thread1.join();
                System.out.println("B..........................");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    });

    final static Thread thread3 = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                thread2.join();
                System.out.println("C......................");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    });

    public static void main(String[] args) {
        System.out.println("start C...");
        thread3.start();
        System.out.println("start B...");
        thread2.start();
        System.out.println("start A...");
        thread1.start();
    }
}
