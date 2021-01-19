import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class usaFiloApiAn {

	public static void main(String args[])
	{
		final int nThreads = 4;
		ThreadPoolExecutor ex = new ThreadPoolExecutor(nThreads, nThreads,0L,TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
		
		for(int i = 0; i < nThreads; i++)
			ex.execute(new Filosofo(i));
		
		ex.shutdown();
		while(!ex.isTerminated());
	}
}

class Filosofo extends Thread
{
	public static filoApiAN m = new filoApiAN();
	private int id;
	
	public Filosofo(int id)
	{
		this.id = id;
	}
	@Override
	public void run(){
		// TODO Auto-generated method stub
		while(true)
		{
			try {
				
				Thread.currentThread().sleep(2000);
				System.out.println(Thread.currentThread().getName()+" Est� pensando");
					
				m.tomarPalillos(id);
	
				System.out.println(Thread.currentThread().getName()+" Ha cogido los palillos");	
				
				m.soltarPalillos(id);
				
				System.out.println(Thread.currentThread().getName()+" Ha liberado los palillos");
				Thread.currentThread().sleep(2000);
						
			
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
	}
}	