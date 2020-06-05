package other;

import java.util.Stack;

class Product  
{  
    String id;  
      
    Product(String id)  
    {  
        this.id = id;  
    }  
}  
//仓库类  
public class ProductStack  
{  
    //仓库有个最大存储容量  
    int index = 6;  
      
    //仓库中货架,通过java.util.Stack来实现，主要用到push和pop两个方法  
    Stack<Product> products = new Stack<Product>();  
      
    //生产产品，并放入货架  
    public synchronized void push(Product pc)  
    {  
        //如果货架上的产品已经满了，则停止生产  
        if (products.size() == index)  
        {  
            try  
            {  
                //一旦调用wait方法，当前线程将暂停，同时释放ProductStack的对象锁，  
                //直到其它拥有该对象锁的线程调用notify或者notifyAll,当前线程才会  
                //尝试再次获取ProductStack的对象锁，并从此处继续执行  
                wait();  
            }  
            catch (InterruptedException e)  
            {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
            }  
        }  
          
        //生产一个产品  
        products.push(pc);  
          
        System.out.println("生产了 " + pc.id);  
          
        //此时货架肯定不是空的，通知消费者消费， 之前讲到，notify和notifyAll的区别是  
        //前者随机唤醒一个等待ProductStack对象锁的线程，而后者是唤醒所有线程，此时当前  
        //进程中只有pop一个线程在等待对象锁，因此此处也可以使用notify，  
        //思考一下，如果把notifyAll方法拿到products.push(pc)；前面呢？  
        //其实效果是一样的，上面说过，调用notify或者notifyAll方法虽然唤醒了等待线程，  
        //但并不会释放对象锁，直到当前线程（或者持有对象锁的方法，代码块）结束后才会被释放，  
        //因此pop线程虽然被唤醒，但由于拿不到ProductStack对象锁，依旧无法执行  
        notify();  
    }  
      
    //从货架上取出产品供消费  
    public synchronized void pop()  
    {  
        //如果货架上是空的，则停止消费  
        if (products.isEmpty())  
        {  
            try  
            {  
                wait();  
            }  
            catch (InterruptedException e)  
            {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
            }  
        }  
        //消费一个产品  
        Product pc = products.pop();  
          
        System.out.println("消费了 " + pc.id);  
        //此时货架肯定不是满的，通知生产者生产  
        notify();  
    }  
}  
