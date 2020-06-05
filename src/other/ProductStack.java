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
//�ֿ���  
public class ProductStack  
{  
    //�ֿ��и����洢����  
    int index = 6;  
      
    //�ֿ��л���,ͨ��java.util.Stack��ʵ�֣���Ҫ�õ�push��pop��������  
    Stack<Product> products = new Stack<Product>();  
      
    //������Ʒ�����������  
    public synchronized void push(Product pc)  
    {  
        //��������ϵĲ�Ʒ�Ѿ����ˣ���ֹͣ����  
        if (products.size() == index)  
        {  
            try  
            {  
                //һ������wait��������ǰ�߳̽���ͣ��ͬʱ�ͷ�ProductStack�Ķ�������  
                //ֱ������ӵ�иö��������̵߳���notify����notifyAll,��ǰ�̲߳Ż�  
                //�����ٴλ�ȡProductStack�Ķ����������Ӵ˴�����ִ��  
                wait();  
            }  
            catch (InterruptedException e)  
            {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
            }  
        }  
          
        //����һ����Ʒ  
        products.push(pc);  
          
        System.out.println("������ " + pc.id);  
          
        //��ʱ���ܿ϶����ǿյģ�֪ͨ���������ѣ� ֮ǰ������notify��notifyAll��������  
        //ǰ���������һ���ȴ�ProductStack���������̣߳��������ǻ��������̣߳���ʱ��ǰ  
        //������ֻ��popһ���߳��ڵȴ�����������˴˴�Ҳ����ʹ��notify��  
        //˼��һ�£������notifyAll�����õ�products.push(pc)��ǰ���أ�  
        //��ʵЧ����һ���ģ�����˵��������notify����notifyAll������Ȼ�����˵ȴ��̣߳�  
        //���������ͷŶ�������ֱ����ǰ�̣߳����߳��ж������ķ���������飩������Żᱻ�ͷţ�  
        //���pop�߳���Ȼ�����ѣ��������ò���ProductStack�������������޷�ִ��  
        notify();  
    }  
      
    //�ӻ�����ȡ����Ʒ������  
    public synchronized void pop()  
    {  
        //����������ǿյģ���ֹͣ����  
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
        //����һ����Ʒ  
        Product pc = products.pop();  
          
        System.out.println("������ " + pc.id);  
        //��ʱ���ܿ϶��������ģ�֪ͨ����������  
        notify();  
    }  
}  
