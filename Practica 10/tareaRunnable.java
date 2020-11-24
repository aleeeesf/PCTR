import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.*;
import java.util.*;

public class tareaRunnable implements Runnable{
   
  private final long linf;
  private final long lsup;
  public static AtomicInteger total = new AtomicInteger(0);
   	
  public tareaRunnable(long linf, long lsup){
    this.linf = linf;
    this.lsup = lsup;
  }
  
  public boolean esPrimo(long n){
    if(n<=1) return(false);
    for(long i=2; i<=Math.sqrt(n); i++)
      if(n%i == 0) return(false);
    return(true);
  }	
		
  public void run(){   
    for(long i=linf; i<=lsup;i++)
      if(esPrimo(i)) total.incrementAndGet();
  }

  public static void main(String args[])
  {

    long nPuntos     = Integer.parseInt(args[0]);
    int  nTareas     = Runtime.getRuntime().availableProcessors();
    System.out.println(nTareas);
    long tVentana    = nPuntos/nTareas;
    long primosTotal = 0;
    long linf        = 0;
    long lsup        = tVentana;

    ThreadPoolExecutor ept = new ThreadPoolExecutor(nTareas,nTareas,0L,TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>());

    long inicTiempo = System.nanoTime();  

    for(int i=0; i<nTareas; i++){
        ept.submit(new tareaRunnable(linf, lsup));
        linf=lsup+1;
        lsup+=tVentana;
      }  

      ept.shutdown();

      while(!ept.isTerminated());
      long tiempoTotal = (System.nanoTime()-inicTiempo)/(long)1.0e9;   
      System.out.println("Primos hallados: "+total.get());
      System.out.println("Calculo finalizado en "+tiempoTotal+" segundos");
  }


}
