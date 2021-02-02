import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class drakkarVikingoReentrant {
    
    private static ReentrantLock lock = new ReentrantLock();
    private static Condition no_vacio = lock.newCondition();
    private static Condition no_lleno = lock.newCondition();

    static int m_anguilas = 10;

    public synchronized void comer() 
    { 
        
        System.out.println("Entra?");
        lock.lock();       
        
        while(m_anguilas == 0)
        {                   
            try {                
                no_vacio.await(); 
            } catch (Exception e) {
                //TODO: handle exception
            }                            
        } 
    
        System.out.println(Thread.currentThread().getName()+"Esta comiendo");

        m_anguilas--;
        
        no_lleno.signal();
    
        lock.unlock();
        
    }

    public synchronized void cocinar()
    {

        System.out.println("Entra?2");
        lock.lock();  
             
            while(m_anguilas > 0)        
            {         
                    try {
                        System.out.println("A esperaa");              
                        no_lleno.await(); 
                    } catch (InterruptedException e) {
                        //TODO: handle exception
                    }                    
           } 
           
        
        System.out.println("Anguilas cocinadas");    

        m_anguilas = 10;

        no_vacio.signalAll();           

        
        lock.unlock();
       
    }
}
