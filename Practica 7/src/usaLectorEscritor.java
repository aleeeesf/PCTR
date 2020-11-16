import java.util.concurrent.*;

	public class usaLectorEscritor
	{
		  public static void main(String[] args) throws Exception
		  {			    
			    int nThreads = Runtime.getRuntime().availableProcessors();
			    lectorEscritor MonitorCompartido = new lectorEscritor();
			    
			    ThreadPoolExecutor ex = new ThreadPoolExecutor(nThreads, nThreads,0L,TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
			   
			    Escritor e = new Escritor(MonitorCompartido);
			    
			    
			    for(int i = 0; i < nThreads; i++)
			    {
			    	if(i != 0) ex.execute(new Lector(MonitorCompartido));
			    	else ex.execute(e);
			    }

			    
			    ex.shutdown();
				  
			    while(!ex.isTerminated());
		    }
	}	
	
	class Escritor extends Thread
	{
		  private lectorEscritor monitorCompartido;
		
		  public Escritor(lectorEscritor monitor)
		  {this.monitorCompartido = monitor;}
		
		  public void run()
		  {
			  try {
				  
			      while(true)
			      {
				        monitorCompartido.inicia_escritura();
				        monitorCompartido.fin_escritura();
				        
			        	Thread.currentThread().sleep(1000);
			      }
			      
		      }catch(Exception e){}
		  }
	}
	
	class Lector extends Thread
	{
		  private lectorEscritor monitorCompartido;
		
		  public Lector(lectorEscritor monitor)
		  {this.monitorCompartido = monitor;}
		
		  @Override
		  public void run()
		  {
			  try
			  {
			      while(true)
			      {
				    	  monitorCompartido.inicia_lectura();
				    	  monitorCompartido.fin_lectura();	
				        
				    	  Thread.currentThread().sleep(1000);		
			  	  }
			  }catch(Exception e){}
		}
	}