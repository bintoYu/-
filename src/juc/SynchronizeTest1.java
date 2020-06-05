package juc;

//线程1：x.isSyncA(), 线程2: x.isSyncB() ：  
public class SynchronizeTest1
{
	    Something x = new Something();
	    Something y = new Something();

	    private void test1() {
	        // 新建t11, t11会调用 x.isSyncA()
	        Thread t11 = new Thread(
	                new Runnable() {
	                    @Override
	                    public void run() {
	                        x.isSyncA();
	                    }
	                }, "t11");

	        // 新建t12, t12会调用 x.isSyncB()
	        Thread t12 = new Thread(
	                new Runnable() {
	                    @Override
	                    public void run() {
	                        x.isSyncB();
	                    }
	                }, "t12");  
	        

	        t11.start();  // 启动t11
	        t12.start();  // 启动t12
	    }

	    public static void main(String[] args) {
	    	SynchronizeTest1 demo = new SynchronizeTest1();
	        demo.test1();
	    }
	
}

// LockTest1.java的源码
class Something {
    public synchronized void isSyncA(){
        try {  
            for (int i = 0; i < 5; i++) {
                Thread.sleep(100); // 休眠100ms
                System.out.println(Thread.currentThread().getName()+" : isSyncA");
            }
        }catch (InterruptedException ie) {  
        }  
    }
    public synchronized void isSyncB(){
        try {  
            for (int i = 0; i < 5; i++) {
                Thread.sleep(100); // 休眠100ms
                System.out.println(Thread.currentThread().getName()+" : isSyncB");
            }
        }catch (InterruptedException ie) {  
        }  
    }
}
