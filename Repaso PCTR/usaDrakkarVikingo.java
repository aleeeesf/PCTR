import java.util.concurrent.*;


public class usaDrakkarVikingo extends Thread
{
    private boolean es_cocinero;
    private drakkarVikingoReentrant v; 

    public usaDrakkarVikingo(boolean cocinero, drakkarVikingoReentrant dv)
    {
        this.es_cocinero = cocinero;
        this.v = dv;
    }

    public void run()
    {
        while(true)
        {
            if(!es_cocinero) v.comer();
            else v.cocinar();
            
            try{
                Thread.currentThread().sleep(200);
            }catch(InterruptedException ex){}
        }
    }

    public static void main(String args[])
    {
        ThreadPoolExecutor ex = new ThreadPoolExecutor(10, 10,0L,TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
        drakkarVikingoReentrant v = new drakkarVikingoReentrant(); 

        
        for(int i = 0; i < 9; i++)
        {
            ex.execute(new usaDrakkarVikingo(false,v));
        }
        ex.execute(new usaDrakkarVikingo(true,v));

        ex.shutdown();
        while(!ex.isTerminated());
    }    
}