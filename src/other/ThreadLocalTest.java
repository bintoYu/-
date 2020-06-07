package other;

import java.util.concurrent.TimeUnit;

public class ThreadLocalTest {

    private static ThreadLocal threadLocal = new ThreadLocal();


    public static void main(String[] args) {
        new Thread(() ->{

            threadLocal.set(new Person());
            System.out.println(threadLocal.get());
        }).start();

        new Thread(() ->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(threadLocal.get());
        }).start();
    }

    static class Person{
        String name = "zhangsan";
    }
}
