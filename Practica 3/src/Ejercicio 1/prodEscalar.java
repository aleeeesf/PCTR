import java.util.Random;

public class prodEscalar {

	private static final int tam= 1000000;
	private static int[] v1 = new int[tam];
	private static int[] v2 = new int[tam];
	
	public static void main(String[] args)
	{		
		Random r = new Random(System.nanoTime());
		int resultado = 0;
		
		double tiempoFinal;
		
		for(int i = 0; i < 1000000; i++)
		{
			v1[i] = r.nextInt(10);			
			v2[i] = r.nextInt(10);
		}
		
		long tiempoInicio = System.nanoTime();
		
		for(int i = 0; i < 1000000; i++)
		{
			resultado += v1[i] + v2[2];
		}
		
		tiempoFinal = (double)((System.nanoTime() - tiempoInicio)/1000000);
		System.out.println(tiempoFinal+" milisegundos");
		System.out.println("El resultado es: "+resultado);
	}	
}
