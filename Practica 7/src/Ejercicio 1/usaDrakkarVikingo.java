import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class usaDrakkarVikingo {

	  public static void main(String[] args) throws Exception
	  {			    
		    int nThreads = Runtime.getRuntime().availableProcessors();
		    drakkarVikingo MonitorCompartido = new drakkarVikingo();
		    
		    ThreadPoolExecutor ex = new ThreadPoolExecutor(nThreads, nThreads,0L,TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
		   
		    Vikingo e = new Vikingo(MonitorCompartido, true);	//Vikingo cocinero
		    
		    
		    for(int i = 0; i < nThreads; i++)
		    {
		    	if(i != 0) ex.execute(new Vikingo(MonitorCompartido,false));
		    	else ex.execute(e);
		    }

		    
		    ex.shutdown();
			  
		    while(!ex.isTerminated());
	    }
}

class Vikingo extends Thread
{
	  private drakkarVikingo monitorCompartido;
	  boolean cocinero;
	  
	  public Vikingo(drakkarVikingo monitor, boolean cocinero)
	  {
		  this.monitorCompartido = monitor;
		  this.cocinero = cocinero;
	  }
	
	  public void run()
	  {
		  if(!cocinero)
		  {
			  try {
				  
			      while(true)
			      {
				        monitorCompartido.servir();    
			        	Thread.currentThread().sleep(1000);
			      }
			      
		      }catch(Exception e){}
		  }

		  else
		  {
			  try {
				  
			      while(true)
			      {
				        monitorCompartido.llenar();    
			        	Thread.currentThread().sleep(1000);
			      }
			      
		      }catch(Exception e){}
		  }
	  }
}
