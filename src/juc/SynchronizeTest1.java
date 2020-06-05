package juc;

//�߳�1��x.isSyncA(), �߳�2: x.isSyncB() ��  
public class SynchronizeTest1
{
	    Something x = new Something();
	    Something y = new Something();

	    private void test1() {
	        // �½�t11, t11����� x.isSyncA()
	        Thread t11 = new Thread(
	                new Runnable() {
	                    @Override
	                    public void run() {
	                        x.isSyncA();
	                    }
	                }, "t11");

	        // �½�t12, t12����� x.isSyncB()
	        Thread t12 = new Thread(
	                new Runnable() {
	                    @Override
	                    public void run() {
	                        x.isSyncB();
	                    }
	                }, "t12");  
	        

	        t11.start();  // ����t11
	        t12.start();  // ����t12
	    }

	    public static void main(String[] args) {
	    	SynchronizeTest1 demo = new SynchronizeTest1();
	        demo.test1();
	    }
	
}

// LockTest1.java��Դ��
class Something {
    public synchronized void isSyncA(){
        try {  
            for (int i = 0; i < 5; i++) {
                Thread.sleep(100); // ����100ms
                System.out.println(Thread.currentThread().getName()+" : isSyncA");
            }
        }catch (InterruptedException ie) {  
        }  
    }
    public synchronized void isSyncB(){
        try {  
            for (int i = 0; i < 5; i++) {
                Thread.sleep(100); // ����100ms
                System.out.println(Thread.currentThread().getName()+" : isSyncB");
            }
        }catch (InterruptedException ie) {  
        }  
    }
}
