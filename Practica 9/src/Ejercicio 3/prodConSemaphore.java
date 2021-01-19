import java.util.*;
import java.util.concurrent.*;

public class prodCon
  extends Thread
 {
    private static int tamBuffer = 100;
    private static double [] buffer;
    private static int InPtr=0;
    private static int OutPtr=0;
    private int tipoHilo;
    private static Semaphore llenos = new Semaphore(0);
    private static Semaphore mutex = new Semaphore(1);
    private static Semaphore vacio = new Semaphore(tamBuffer);
    
      public prodCon(int tipo) {tipoHilo = tipo;}
      public void run()
      {
      	switch(tipoHilo){
      		case 0:{
      			for(;;)
      			{
      				try
      				{
          				vacio.acquire();
          				mutex.acquire();
      				}catch(InterruptedException e) {}

      				buffer[InPtr]=Math.random();
      				System.out.println("Hilo productor insertando "+buffer[InPtr]+" en buffer");
      				InPtr=(InPtr+1)%tamBuffer;
      				mutex.release();
      				llenos.release();
      				
      			}
      		}
      		case 1:{
      			for(;;)
      			{
          			try
          			{
              			llenos.acquire();
              			mutex.acquire();
          			}catch(InterruptedException e) {}
          			
          			System.out.println("Hilo consumidor extrayendo "+buffer[OutPtr]+" de buffer"); OutPtr=(OutPtr+1)%tamBuffer;
          			mutex.release();
          			vacio.release();
      			}
      		}
      	}
      }


    public static void main(String[] args)
    {  buffer = new double[tamBuffer];
       new prodCon(0).start();
       new prodCon(1).start();
    }
}
