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
		
		for(int i = inicio; i <= fin; i++)				
		{
			if(i % 2 == 0)
			{
				aux = 0;
				for(int j = 1; j <= (i/2) ; j++)
				{
					if(i % j == 0)
					{
						aux = aux+j;
					}
				}
				
				if(aux == i && aux != 0) 
				{
					System.out.println(i);
					cont++;
				}
			}
		}

		return cont;		
	}
	
	public static void main(String args[]) throws InterruptedException, ExecutionException
	{
		Runtime r = Runtime.getRuntime();
		int nThreads = 2;
		int rango = Integer.parseInt(args[0]);			
		
		ThreadPoolExecutor ex = new ThreadPoolExecutor(nThreads,nThreads,0L,TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
		ArrayList<Future<Integer>>  v= new ArrayList<Future<Integer>>();
		int ini = 1, fin = rango/nThreads;		
		
		double tiempoInicio = System.nanoTime();
		double tiempoFinal = 0.0;
		
		for(int i = 0; i < nThreads; i++)
		{
			numPerfectos n = new numPerfectos(ini, fin);
			v.add(ex.submit(n));
			ini = fin + 1;
			if(i == nThreads-2)
				fin = rango;
			else
				fin += rango/nThreads;
		}			

		int nPerfectos = 0;
		for(Future<Integer> i: v)
		{
			nPerfectos += i.get();
		}
		
		ex.shutdown();
		
		while(!ex.isTerminated());
		System.out.println("Hay"+nPerfectos+"Numeros perfectos");	
		System.out.println("Hilos usados: "+nThreads);
		tiempoFinal = (System.nanoTime() - tiempoInicio)/1000000000;
		System.out.println("El tiempo total ha sido de: "+tiempoFinal);
	}
}
