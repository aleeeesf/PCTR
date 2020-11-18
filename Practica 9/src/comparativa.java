import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class comparativa {
	
	private ReentrantLock r = new ReentrantLock();
	private Semaphore s = new Semaphore(1);
	
	public long tarea_synchronized(long iter)
	{
		long ini = System.nanoTime();
		int n = 0;
		
		for(int i = 0; i < iter; i++)
		{
			synchronized(this)
			{
				n++;
			}
		}
		
		return (System.nanoTime() - ini);
	}
	public long tarea_atomic(long iter)
	{
		long ini = System.nanoTime();
		int n = 0;
		AtomicInteger n_atomico = new AtomicInteger(n);
		
		for(int i = 0; i < iter; i++)
		{
				n_atomico.incrementAndGet();
		}
		
		return (System.nanoTime() - ini);
	}
	
	public long tarea_reentrant(long iter)
	{
		long ini = System.nanoTime();
		int n = 0;
		
		for(int i = 0; i < iter; i++)
		{
			r.lock();		
				n++;
			r.unlock();
		}
		
		return (System.nanoTime() - ini);
	}
	
	public long tarea_semaforo(long iter) throws InterruptedException
	{
		long ini = System.nanoTime();
		int n = 0;
		
		for(int i = 0; i < iter; i++)
		{
			s.acquire();	
				n++;
			s.release();
		}
		
		return (System.nanoTime() - ini);
	}
	
	public static void main(String args[]) {
		
		comparativa c = new comparativa();
		System.out.println("El tiempo con atomic es: "+c.tarea_atomic(5000000)+" nanosegundos");
		System.out.println("El tiempo con synchronized es: "+c.tarea_synchronized(5000000)+" nanosegundos");
		try
		{
			System.out.println("El tiempo con semaforo es: "+c.tarea_semaforo(5000000)+" nanosegundos");
			
		}catch(InterruptedException e) {}
		
		System.out.println("El tiempo con ReentrantLock es: "+c.tarea_reentrant(5000000)+" nanosegundos");
	}
}
