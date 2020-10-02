import java.util.Scanner;
import java.lang.Math;

public class Problema2 {

	private double derivada_f (double x)
	{
		return(-Math.sin(x) - 3*Math.pow(x,2));		// f´(x) = -sin(x) - 3x^2
	}
	
	private double derivada_g (double x)
	{
		return (2*x);		// g´(x) = 2x
	}
	
	private double f (double x)
	{
		return (Math.cos(x)-Math.pow(x,3));			//f(x) = cos(x) - x^3
	}
	
	private double g (double x)
	{
		return (Math.pow(x,2) - 5);			//g(x) = x^2 -5
	}	
	
	public void NewtonRaphson_f(double x, int iteraciones)
	{
		double xn = x, x1;
		
		for(int i = 0; i < iteraciones; i++)
		{
			if(derivada_f(x) != 0)
			{
				x1 = xn - f(xn)/derivada_f(xn);
				System.out.println("Iteracion: "+i+" Aproximación a la raiz: "+x1);
				xn = x1;
			}					
		}
		
		System.out.println("Aproximación final a la raiz: "+xn);	
	}
	
	public void NewtonRaphson_g(double x, int iteraciones)
	{
		double xn = x, x1;
		
		for(int i = 0; i < iteraciones; i++)
		{
			if(derivada_g(x) != 0)
			{
				x1 = xn - g(xn)/derivada_g(xn);
				System.out.println("Iteracion: "+i+" Aproximación a la raiz: "+x1);
				xn = x1;
			}					
		}
		
		System.out.println("Aproximación final a la raiz: "+xn);			
	}
	

	public static void main(String[] args)
	{
		Scanner S = new Scanner(System.in);
		Problema2 p = new Problema2();
		int opc, iteration;
		double x;
		
		do
		{
			System.out.println("Elige funcion: \n 1. f(x) = cos(x) - x^3 [0,1]\n 2. g(x) = x^2 -5[2,3]");
			opc = S.nextInt();
			
		}while(opc != 1 && opc != 2);
			
		switch(opc)
		{
		
			case 1:	
					System.out.println("Introduce valor inicial: ");
					x = S.nextDouble();
					System.out.println("Introduce iteraciones: ");
					iteration = S.nextInt();
					
					p.NewtonRaphson_f(x, iteration);
					break;
		
			case 2:
					
					System.out.println("Introduce valor inicial: ");
					x = S.nextDouble();
					System.out.println("Introduce iteraciones: ");
					iteration = S.nextInt();

					p.NewtonRaphson_g(x, iteration);
					
					break;		
		}		
	}
}
