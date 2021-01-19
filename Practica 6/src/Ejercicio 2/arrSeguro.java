import java.util.Scanner;

public class arrSeguro extends Thread{

	public final static int tam = 100;
	public static Object cerrojo = new Object();
	public static int[] vct = new int[tam];
	private int id;
	
	public arrSeguro(int id)
	{
		this.id = id;
	}
	
	public void run()
	{
		synchronized(cerrojo)
		{
			if(id % 2  == 0)
			{
				for(int i = 0; i < tam; i++)
				{
					vct[i]++;
				}
			}
			
			else
			{
				for(int i = 0; i < tam; i++)
				{
					vct[i]--;
				}
			}
		}
	}
	
	public static void main(String args[])
	{
		int nHilos;
		Scanner s = new Scanner(System.in);
		System.out.println("introduce el numero de hilos: ");
		nHilos = s.nextInt();
		
		arrSeguro hilos[] = new arrSeguro[nHilos];
		
		for(int i = 0; i < nHilos; i++)
		{
			hilos[i] = new arrSeguro(i);
			hilos[i].start();
		}
		
		try
		{
			for(int i = 0; i < nHilos; i++)
			{
				hilos[i].join();
			}
		}catch(Exception e) {};
	
	
		for(int i = 0; i < tam; i++)
		{
			System.out.println(vct[i]);
		}
	}
}
	
