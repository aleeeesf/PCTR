import java.util.Scanner;

public class Estadistica {
	private double[] eltos;

	public Estadistica(int n)
	{
		this.eltos = new double[n];
	}
	
	public void introducir_numeros()
	{
		Scanner S = new Scanner(System.in);
		
		for(int i = 0; i < eltos.length; i++)
		{
			System.out.println("Introduce el elemento "+(i+1)+": ");
			eltos[i] = S.nextDouble();
		}	
	}	
	
	public double media()
	{
		double suma = 0.0;
		
		for(int i = 0; i < eltos.length; i++)
		{
			suma += eltos[i];
		}
		
		return (suma/eltos.length);
	}
	
	public double moda()
	{
		int frecuencia = 0, mayorFrecuencia = -1;
		double moda = 0.0;
		
		for(int i = 0; i < eltos.length; i++)
		{
			for(int j = 0; j < eltos.length; j++)
			{
				if(i != j)
				{
					if(eltos[i] == eltos[j])
						frecuencia++;
				}
			}
			
			if(frecuencia > mayorFrecuencia)
			{
				mayorFrecuencia = frecuencia;
				moda = eltos[i];
			}
		}
			
		return moda;
	}
	
	public double varianza()
	{
		double suma = 0.0, media = this.media();
		
		for(int i = 0; i < eltos.length; i++)
		{
			suma += Math.pow(eltos[i] - media, 2);
		}
		
		return (suma/eltos.length);
	}
	
	public double desviacion_tipica()
	{
		double desviacion = Math.sqrt(this.varianza());
		return desviacion;
	}
	
	public static void main(String[] args)
	{
		int nEltos, opc;
		Scanner S = new Scanner(System.in);
		
		if(args.length == 0)
		{
			System.out.println("No se ha introducido el tamaño");
			System.exit(-1);
		}
		
		nEltos = Integer.parseInt(args[0]);
		Estadistica e = new Estadistica(nEltos);
		
		System.out.println("Introduce los elementos: ");
		e.introducir_numeros();
		
		do
		{
			System.out.println("1. Media");
			System.out.println("2. Moda");
			System.out.println("3. Varianza");
			System.out.println("4. Desviación Típica");
			System.out.println("Su respuesta: ");
			
			opc = S.nextInt();
			
		}while(opc < 1 || opc > 4);
		
		switch(opc)
		{
			case 1:	
					System.out.println("La media es: "+e.media());				
					break;
					
			case 2:	
					System.out.println("La moda es: "+e.moda());
					
					break;
			
			case 3:	
					System.out.println("La varianza es: "+e.varianza());				
					break;
				
			case 4:	
					System.out.println("La desviación típica es: "+e.desviacion_tipica());				
					break;
		}
	}	
}
