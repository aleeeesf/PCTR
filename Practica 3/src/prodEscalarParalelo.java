import java.util.Random;

public class prodEscalarParalelo extends Thread{

	private static int nThreads = 8;
	private static final int tam= 1000000;
	private static int[] v1 = new int[tam];
	private static int[] v2 = new int[tam];
	public static int[] productoParcial = new int[nThreads];
	private int inicio, fin, id;
	
	public prodEscalarParalelo(int idHebra, int incio, int final_)
	{
		this.id = idHebra;
		this.inicio = incio;
		this.fin = final_;
	}
	
	public void run()
	{
		for(int i = inicio; i < fin; i++)
		{
			productoParcial[id] += v1[i] * v2[i];			
		}		
	}
	
	public static void main(String[] args)
	{		
		Random r = new Random(System.nanoTime());
		int resultado = 0;		
		double tiempoFinal;
		prodEscalarParalelo[] Hilos = new prodEscalarParalelo[nThreads];
		int inicio = 0, fin= tam/nThreads;
		
		for(int i = 0; i < 1000000; i++)
		{
			v1[i] = 2;				
			v2[i] = 2;
		}
		long tiempoInicio = System.nanoTime();
		for(int i = 0; i < Hilos.length; i++)
		{
			//System.out.println(fin);
			Hilos[i] = new prodEscalarParalelo(i,inicio, fin);
			inicio = fin;
			
			if(i == Hilos.length-2)
				fin = tam;
			else 
				fin += tam/nThreads;
			
			Hilos[i].start();
		}
		
		try
		{
			for(int i = 0; i < Hilos.length; i++)
			{
				Hilos[i].join();
			}
		}catch(Exception ex) {}
		
		
		for(int i = 0; i < nThreads; i++)		
			resultado +=  productoParcial[i];	
		
		tiempoFinal = (double)((System.nanoTime() - tiempoInicio)/1000000);
		System.out.println(tiempoFinal+" milisegundos");
		System.out.println("El resultado es: "+resultado);
	}
}
