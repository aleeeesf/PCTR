import java.lang.Math;
import java.util.Scanner;

public class intDefinidaMonteCarlo {

	public double IntegracionMonteCarlo(int n)
	{
		int exitos = 0;				//contador_exitos <- 0			
		double x = 0, y = 0;
		
		for(int i = 0; i < n; i++)
		{
			x = Math.random();		//coordenada_x <- aleatorio (0,1)
			y = Math.random();		//coordenada_y <- aleatorio (0,1)
			
			if(y <= x)
			{
				exitos++;			//constador_exitos <- 
			}
		}	
		
		double integral = (double)exitos/n;
		return integral;
	}
	
	public double IntegracionMonteCarlo_SinFunction(int n)
	{
		int exitos = 0;				//contador_exitos <- 0			
		double x = 0, y = 0;
		
		for(int i = 0; i < n; i++)
		{
			x = Math.random();		//coordenada_x <- aleatorio (0,1)
			y = Math.random();		//coordenada_y <- aleatorio (0,1)
			
			if(y <= Math.sin(x))
			{
				exitos++;
			}
		}	
		
		double integral = (double)exitos/n;
		return integral;
	}
	
	public static void main(String[] args)
	{
		Scanner S = new Scanner(System.in);
		intDefinidaMonteCarlo prueba = new intDefinidaMonteCarlo();
		int n;
		
		System.out.println("(Funcion f(x) = x) Introduce n:" );
		n = S.nextInt();
		
		System.out.println("La aproximación es: "+prueba.IntegracionMonteCarlo(n));
		
		System.out.println("(Funcion f(x) = sin(x)) Introduce n: " );
		n = S.nextInt();
		
		System.out.println("La aproximación es: "+prueba.IntegracionMonteCarlo_SinFunction(n));
		
	}
}
