import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
public class tareaPrimos implements Runnable{
   
  private final long linf;
  private final long lsup;
  private AtomicInteger total = new AtomicInteger(0);
   	
  public tareaPrimos(long linf, long lsup){
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
      if(esPrimo(i)) total++;
  }
}
