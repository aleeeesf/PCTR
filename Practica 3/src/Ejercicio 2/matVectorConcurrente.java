import java.util.Random;
	
public class matVectorConcurrente implements Runnable{
		
	public static int nMax = 1000;
	public static int nThreads = 10;
	private static int[][] A = new int[nMax][nMax];
	private static int[] B = new int[nMax];
	private static int[] y = new int[nMax];
	private int inicio, fin;
	
	matVectorConcurrente(int inicio, int fin)
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
				y[i] += A[i][j] * B[j]; 
			}
		}
	}
	
	public static void main(String[] args)
	{
		Random r = new Random(System.nanoTime());
		int inicio_ = 0, fin_ = nMax/nThreads;
		Thread[] Hilos = new Thread[nThreads];
		
		for(int i = 0; i < nMax; i++)
		{
			B[i] = r.nextInt(10);
			
			for(int j = 0; j < nMax; j++)
			{
				A[i][j] = r.nextInt(10);
			}
		}
		
		long inicioTiempo = System.nanoTime();
		
		for(int i = 0; i < Hilos.length; i++)
		{
			//System.out.println(fin);
			Hilos[i] = new Thread(new matVectorConcurrente(inicio_, fin_));
			inicio_ = fin_;
			
			if(i == Hilos.length-2)
				fin_ = nMax;
			else 
				fin_ += nMax/nThreads;
			
			Hilos[i].start();
		}
		
		try
		{
			for(int i = 0; i < Hilos.length; i++)
			{
				Hilos[i].join();
			}
		}catch(Exception ex) {}			

		
		double tiempoFinal = (double)((System.nanoTime()-inicioTiempo)/1000000);
		
		System.out.println("El tiempo empleado es: "+tiempoFinal+" milisegundos");		
	}
}

