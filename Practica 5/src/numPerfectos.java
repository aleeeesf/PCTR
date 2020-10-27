import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class numPerfectos implements Callable<Integer>{

	private int inicio, fin;
	
	public numPerfectos(int inicio, int fin)
	{
		this.inicio = inicio;
		this.fin = fin;
	}
	
	public Integer call()
	{
		int aux = 0, cont = 0;
		
		for(int i = inicio; i < fin; i++)				
		{
			if(i % 2 == 0)
			{
				for(int j = (i/2); j >= 1; j++)
				{
					if(i % j == 0)
					{
						aux = aux+j;
					}
				}	
				
				if(aux == 1) cont++;
			}
		}
		
		return cont;
		
	}
	
	public Integer perfectos(int fin)
	{
		int aux = 0, cont = 0;
		
		for(int i = 0; i <= fin; i++)				
		{
			if(i % 2 == 0)
			{
				for(int j = (i/2); j >= 1; j++)
				{
					if(i % j == 0)
					{
						aux = aux+j;
					}
				}	
				
				if(aux == 1) cont++;
			}
		}
		
		return cont;
		
	}
	public static void main(String args[]) throws InterruptedException, ExecutionException
	{
		Runtime r = Runtime.getRuntime();
		int nThreads = r.availableProcessors();
		int rango = 30;			
		
		ThreadPoolExecutor ex = new ThreadPoolExecutor(nThreads,nThreads,0L,TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
		ArrayList<Future<Integer>>  v= new ArrayList<Future<Integer>>();
		int ini = 0, fin = rango/nThreads;
		
		
		for(int i = 0; i < nThreads; i++)
		{
			numPerfectos n = new numPerfectos(ini, fin);
			v.add(ex.submit(n));
			ini = fin;
			fin += rango/nThreads;

		}			

		int nPerfectos = 0;
		for(Future<Integer> i: v)
		{
			System.out.println("mm");
			nPerfectos += i.get();
		}
		
		ex.shutdown();
		
		while(!ex.isTerminated());
		System.out.println("Hay"+nPerfectos+"Numeros perfectos");
		numPerfectos n = new numPerfectos(ini, fin);
		System.out.println(n.perfectos(7));
	}
}
