import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.Random;

public class productoSumaFilas implements Callable<Integer>{
	private int fila;
	public static int nFilas = 10;
	public static int nColumnas = 100;
	public static int[][] M= new int[nFilas][nColumnas];
	
	public productoSumaFilas(int fila)
	{
		this.fila = fila;
	}
	
	public Integer call()
	{
		int cont = 0;
		
		for(int i = 0; i < nColumnas; i++)
		{
			cont += M[fila][i];
		}

		return cont;		
	}
	public static void main(String args[]) throws InterruptedException, ExecutionException
	{
		Random ran = new Random(System.nanoTime());
		int nThreads = 10;
		ThreadPoolExecutor ex = new ThreadPoolExecutor(nThreads,nThreads,0L,TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
		ArrayList<Future<Integer>>  v = new ArrayList<Future<Integer>>();
		long producto = 1;


		for(int i = 0; i < nFilas; i++)		{
						
			for(int j = 0; j < nColumnas; j++)
			{
				M[i][j] = 1;
			}
		}
		
		for(int i = 0; i < nThreads; i++)
		{
			productoSumaFilas n = new productoSumaFilas(i);
			v.add(ex.submit(n));
		}			


		for(Future<Integer> i: v)
		{
			producto = producto * i.get();
		}
		
		ex.shutdown();		
		while(!ex.isTerminated());		
		System.out.println(producto);
	}
}
