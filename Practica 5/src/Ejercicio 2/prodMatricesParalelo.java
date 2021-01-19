import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
	
public class prodMatricesParalelo implements Runnable{
	
	
	public static int nMax = 1000;
	public static int nThreads = 64;
	private static int[][] A = new int[nMax][nMax];
	private static int[][] B = new int[nMax][nMax];
	private static int[][] y = new int[nMax][nMax];
	private int inicio, fin;
	
	public prodMatricesParalelo(int inicio, int fin)
	{
		this.inicio = inicio;
		this.fin = fin;
	}		
	
	public void run()
	{
		for(int i = inicio; i < fin; i++)
		{
			for(int j = 0; j < nMax; j++)
			{
				for(int k = 0; k < nMax; k++)
				y[i][j] += A[i][k] * B[k][j]; 
			}
		}
	}
	
	public static void main(String[] args)
	{
		Random r = new Random(System.nanoTime());
		int inicio_ = 0, fin_ = nMax/nThreads;
		float cb = 0;	//Para tareas de computación numérica, cb = 0	
		int tamPool = (int)(nThreads/(1-cb));
		ThreadPoolExecutor ex = new ThreadPoolExecutor(tamPool,tamPool,0L,TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());

		
		for(int i = 0; i < nMax; i++)		{
						
			for(int j = 0; j < nMax; j++)
			{
				A[i][j] = r.nextInt(10);
				B[i][j] = r.nextInt(10);
			}
		}
		
		long inicioTiempo = System.nanoTime();
		
		for(int i = 0; i < nThreads; i++)
		{
			prodMatricesParalelo n = new prodMatricesParalelo(inicio_, fin_);
			ex.submit(n);
			inicio_ = fin_;
			
			if(i == nThreads-2)
				fin_ = nMax;
			else 
				fin_ += nMax/nThreads;
		}
		
		ex.shutdown();		
		while(!ex.isTerminated());			

		
		double tiempoFinal = (double)((System.nanoTime()-inicioTiempo)/1000000);
		
		System.out.println("El tiempo empleado es: "+tiempoFinal+" milisegundos");	
		System.out.println("Tamaño del pool: "+tamPool);
	}
}