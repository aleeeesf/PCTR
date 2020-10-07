import java.util.Random;
import java.util.Scanner;

public class escalaVPar extends Thread{

	public static int n;
	int inicio, fin;
	public static int [] vector;
	
	public escalaVPar(int inicio, int fin)
	{
		this.inicio = inicio;
		this.fin = fin;
	}
	
	public static void randomVector()
	{
		
		Random rand = new Random(System.nanoTime());
		
		for(int i = 0; i < n; i++)
		{
			vector[i] = rand.nextInt(10);			
		}		
	}
	
	public void run()
	{
		for(int i = inicio; i < fin; i++)
		{
			vector[i] *= 100;
		}
	}
	
	public static void main(String[] args)
	{
		
		int nThreads;
		
		try (Scanner S = new Scanner(System.in)) {			
			
			System.out.println("Escriba el número de componentes del vector: ");
			escalaVPar.n = S.nextInt();
			escalaVPar.vector = new int[n];
			
			System.out.println("Escriba el numero de hilos a utilizar: ");
			nThreads = S.nextInt();			
		}		
		
		int inicio_ = 0, fin_ = escalaVPar.n / nThreads;
		escalaVPar[] P = new escalaVPar[nThreads];
		
		escalaVPar.randomVector();
		
		for(int i = 0; i < nThreads; i++)
		{
			P[i] = new escalaVPar(inicio_, fin_);
			inicio_ = fin_;			
			
			if(i == nThreads-2) fin_ = escalaVPar.n; //El ultimo hilo se encarga del resto del vector
			else fin_ += escalaVPar.n / nThreads;	
			
			P[i].start();
		}
		
		try
		{
			for(int i = 0; i < nThreads; i++)
			{
				P[i].join();
			}
			
		}catch(Exception e){}	
	}
}
