package other;


class ThreadA extends Thread{
    public ThreadA(String name){ 
        super(name); 
    } 
    public  void run(){ 
        for(int i=0; i <10; i++){ 
            try
			{
				Thread.sleep(10);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			} // ����100ms
            System.out.printf("%s [%d]:%d\n", this.getName(), this.getPriority(), i); 
            // i����4ʱ������yield
            if (i%3 == 0)
                Thread.yield();
        } 
    } 
    
    
} 

public class YieldTest{ 
    public static void main(String[] args){ 
        ThreadA t1 = new ThreadA("t1"); 
        ThreadA t2 = new ThreadA("t2"); 
        t1.start(); 
        t2.start();
    } 
}