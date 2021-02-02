import javax.naming.InterruptedNamingException;

public class drakkarVikingo
{
    static int m_anguilas = 10;

    public synchronized void comer() 
    {
        while(m_anguilas == 0)
        {
            try{
                wait();
            }catch(InterruptedException ex){}
        } 

        System.out.println(Thread.currentThread().getName()+"Esta comiendo");
/*
        try{
            Thread.currentThread().sleep(2000);
        }catch(InterruptedException ex){}
*/
        m_anguilas--;

        notifyAll();
    }

    public synchronized void cocinar()
    {
        while(m_anguilas > 0)        
        {
            try{
                wait();
            }catch(InterruptedException ex){}
        } 

        System.out.println("Anguilas cocinadas");


        m_anguilas = 10;

        notifyAll();
    }
}