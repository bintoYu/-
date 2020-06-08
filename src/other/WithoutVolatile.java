package other;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WithoutVolatile {

    volatile List list = new LinkedList();
    //实际生活中不会使用上面这种形式,并发的list是下面这样：
//    List list = Collections.synchronizedList(new LinkedList<>());

    public void add(Object o) {list.add(o);}

    public int size() {return list.size();}

    public static void main(String[] args) {
        WithoutVolatile c = new WithoutVolatile();

        new Thread(()->{
            for(int i = 0; i < 10; i++) {
                c.add(new Object());
                System.out.println("add " + i);

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        },"t1").start();

        new Thread(()->{
            while(true){
                if(c.size() == 5){
                    System.out.println("reach 5................");
                    break;
                }
            }

        },"t2").start();
    }
}
