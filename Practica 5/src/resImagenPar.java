import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

//condición de frontera nula
public class resImagenPar implements Runnable{

	public final static int tam = 1000;
	public static int x[][] = new int[tam][tam];
	private int inicio, fin;
	
	public resImagenPar(int inicio, int fin)
	{
		this.inicio = inicio;
		this.fin = fin;
	}
	
	public void run()
	{
		int x1,x2,x3,x4;
		
		for(int i = inicio; i < fin; i++)
		{
			for(int j = 0; j < tam; j++)
			{
				
				if(i+1 == tam)
					x1 = 0;
				else
					x1 = x[i+1][j];
				
				if(j+1 == tam)
					x2 = 0;
				else
					x2 = x[i][j+1];
				
				if(i-1 < 0)
					x3 = 0;
				else
					x3 = x[i-1][j];				
						
				if(j-1 < 0)
					x4 = 0;
				else 
					x4 = x[i][j-1];
					
				x[i][j] = (4*x[i][j]- x1 - x2 - x3 - x4)/8;
			}
		}
	}
	
	public static void main(String args[])
	{
		Random r = new Random();
		
		for(int i = 0; i < tam; i++)
		{
			for(int j = 0; j < tam; j++)
			{
				x[i][j] = r.nextInt(255);
			}
		}
		
		
		Runtime r1 = Runtime.getRuntime();
		int nThreads = r1.availableProcessors();
		int rango = 30;			
		
		ThreadPoolExecutor ex = new ThreadPoolExecutor(nThreads,nThreads,0L,TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
		int ini = 0, fin = rango/nThreads;
		
		double tiempoInicio = System.nanoTime();
		double tiempoFinal = 0.0;
		
		for(int i = 0; i < nThreads; i++)
		{
			resImagenPar n = new resImagenPar(ini, fin);
			ex.submit(n);
			ini = fin;
			fin += rango/nThreads;

		}			

		ex.shutdown();		
		while(!ex.isTerminated());		
	

		tiempoFinal = (System.nanoTime() - tiempoInicio)/1000000;
		System.out.println("El tiempo total ha sido de: "+tiempoFinal);
	}
}

