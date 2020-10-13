import java.util.Random;

public class matVector {
	
	public static int nMax = 1000;
	private static int[][] A = new int[nMax][nMax];
	private static int[] B = new int[nMax];
	private static int[] y = new int[nMax];
	
	public static void main(String[] args)
	{
		Random r = new Random(System.nanoTime());
		for(int i = 0; i < nMax; i++)
		{
			B[i] = r.nextInt(10);
			
			for(int j = 0; j < nMax; j++)
			{
				A[i][j] = r.nextInt(10);
			}
		}
		
		long inicioTiempo = System.nanoTime();
		
		for(int i = 0; i < nMax; i++)
		{
			for(int j = 0; j < nMax; j++)
			{
				y[i] += A[i][j] * B[j]; 
			}
		}
		
		double tiempoFinal = (double)((System.nanoTime()-inicioTiempo)/1000000);
		
		System.out.println("El tiempo empleado es: "+tiempoFinal+" milisegundos");	
	}
}
