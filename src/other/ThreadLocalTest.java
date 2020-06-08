package other;

import java.util.concurrent.TimeUnit;

public class ThreadLocalTest {
    private static ThreadLocal threadLocal = new ThreadLocal();
    private static ThreadLocal threadLocal2 = new ThreadLocal();

    public static void main(String[] args) {
        new Thread(() ->{

            //要存两个对象的话就得new两个不同的ThreadLocal
            threadLocal.set(new Person("zhangsan"));
            threadLocal2.set(new Person("lisi"));

            System.out.println(threadLocal.get());
            System.out.println(threadLocal2.get());
        }).start();

        new Thread(() ->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //这里print出来的是null，因为不同线程的ThreadLocal的数据是隔离的，
            System.out.println(threadLocal.get());
            System.out.println(threadLocal2.get());
        }).start();
    }

    static class Person{
        String name ;

        public Person(String name) {
            this.name = name;
        }
    }
}
