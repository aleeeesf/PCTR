import java.util.Random;

//condición de frontera nula
public class resImagen {

	public final static int tam = 1000;
	public static int x[][] = new int[tam][tam];
	
	public static void resaltado()
	{
		int x1,x2,x3,x4;
		
		for(int i = 0; i < tam; i++)
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
		
		double tiempoInicio = System.nanoTime();
		double tiempoFinal = 0.0;
		resaltado();
		tiempoFinal = (System.nanoTime() - tiempoInicio)/1000000;
		System.out.println("El tiempo total ha sido de: "+tiempoFinal);
	}
}
