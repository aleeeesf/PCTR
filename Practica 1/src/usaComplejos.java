import java.util.Scanner;

public class usaComplejos {

	public static void main(String args[])
	{
		int opc;
		try (Scanner S = new Scanner(System.in)) {
			do
			{
				System.out.println("Elija una operación a calcular: ");
				System.out.println("1. Suma");
				System.out.println("2. Resta");
				System.out.println("3. Producto");
				System.out.println("4. Cuadrante");
				System.out.println("5. Modulo");
				System.out.println("6. Cociente");
				System.out.println("7. Salir");
				opc = S.nextInt();
				
			}while(opc < 0 || opc > 7);
			
			double real, imaginaria;
			Complejos Resultado = new Complejos();
			Complejos C1 = new Complejos();
			Complejos C2 = new Complejos();
			
			switch(opc)
			{
				case 1:					
						System.out.println("Introduce complejo1: ");
						System.out.println("Parte real: ");
						real = S.nextDouble();
						System.out.println("Parte imaginaria: ");
						imaginaria = S.nextDouble();
						
						C1 = new Complejos(real, imaginaria);
						
						System.out.println("Introduce complejo2: ");
						System.out.println("Parte real: ");
						real = S.nextDouble();
						System.out.println("Parte imaginaria: ");
						imaginaria = S.nextDouble();
						
						C2 = new Complejos(real, imaginaria);
											
						Resultado = C1.suma(C2);
						
						System.out.println("El resultado es "+Resultado.parteReal()+" + "+Resultado.parteImaginaria()+"i");		
						break;
				
				case 2:							
						System.out.println("Introduce complejo1: ");
						System.out.println("Parte real: ");
						real = S.nextDouble();
						System.out.println("Parte imaginaria: ");
						imaginaria = S.nextDouble();
						
						C1 = new Complejos(real, imaginaria);
						
						System.out.println("Introduce complejo2: ");
						System.out.println("Parte real: ");
						real = S.nextDouble();
						System.out.println("Parte imaginaria: ");
						imaginaria = S.nextDouble();
						
						C2 = new Complejos(real, imaginaria);
										
						Resultado = C1.resta(C2);
						
						System.out.println("El resultado es "+Resultado.parteReal()+" + "+Resultado.parteImaginaria()+"i");				
						break;
						
				case 3:					
						System.out.println("Introduce complejo1: ");
						System.out.println("Parte real: ");
						real = S.nextDouble();
						System.out.println("Parte imaginaria: ");
						imaginaria = S.nextDouble();
						
						C1 = new Complejos(real, imaginaria);
						
						System.out.println("Introduce complejo2: ");
						System.out.println("Parte real: ");
						real = S.nextDouble();
						System.out.println("Parte imaginaria: ");
						imaginaria = S.nextDouble();
						
						C2 = new Complejos(real, imaginaria);
											 
						Resultado = C1.Producto(C2);
						
						System.out.println("El resultado es "+Resultado.parteReal()+" + "+Resultado.parteImaginaria()+"i");			
						break;
						
				case 4:
						
						System.out.println("Introduce complejo1: ");
						System.out.println("Parte real: ");
						real = S.nextDouble();
						System.out.println("Parte imaginaria: ");
						imaginaria = S.nextDouble();
						
						C1 = new Complejos(real, imaginaria);
						
						System.out.println("El complejo está en el cuadrante:"+C1.Cuadrante());
						break;
						
				case 5:
											
						System.out.println("Introduce complejo: ");
						System.out.println("Parte real: ");
						real = S.nextDouble();
						System.out.println("Parte imaginaria: ");
						imaginaria = S.nextDouble();
						
						C1 = new Complejos(real, imaginaria);
						
						System.out.println("El modulo es "+C1.Modulo());		
						break;
				
				case 6:
						System.out.println("Introduce complejo1: ");
						System.out.println("Parte real: ");
						real = S.nextDouble();
						System.out.println("Parte imaginaria: ");
						imaginaria = S.nextDouble();
						
						C1 = new Complejos(real, imaginaria);
						
						System.out.println("Introduce complejo2: ");
						System.out.println("Parte real: ");
						real = S.nextDouble();
						System.out.println("Parte imaginaria: ");
						imaginaria = S.nextDouble();
						
						C2 = new Complejos(real, imaginaria);
										
						Resultado = C1.Producto(C2);
						
						System.out.println("El resultado es "+Resultado.parteReal()+" + "+Resultado.parteImaginaria()+"i");				
						break;
					
				case 7:
						System.exit(-1);
						
			}
		}		
	}
}
