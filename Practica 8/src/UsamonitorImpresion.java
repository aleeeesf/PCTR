import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class UsamonitorImpresion {

	public static void main(String args[])
	{
		final int nThreads = 8;
		ThreadPoolExecutor ex = new ThreadPoolExecutor(nThreads, nThreads,0L,TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
		
		for(int i = 0; i < nThreads; i++)
			ex.execute(new Proceso());
		
		ex.shutdown();
		while(!ex.isTerminated());
	}
}

class Proceso extends Thread
{
	public static monitorImpresion m = new monitorImpresion();
	
	@Override
	public void run(){
		// TODO Auto-generated method stub
		int impresora = 0;
		while(true)
		{
			try {
				
				Thread.currentThread().sleep(1000);
				System.out.println(Thread.currentThread().getName()+" Está intentado coger una impresora");
					
				impresora = m.pedir_impresora();
	
				System.out.println(Thread.currentThread().getName()+" Ha cogido una impresora");
				Thread.currentThread().sleep(1000);
				
				m.liberar_impresora(impresora);
				
				System.out.println(Thread.currentThread().getName()+" Ha liberado una impresora");
				Thread.currentThread().sleep(1000);
						
			
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
	}
	
	
}