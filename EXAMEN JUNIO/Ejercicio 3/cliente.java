//Alejandro Serrano Fernández
//DNI: 20501318-S
import java.rmi.*;
import java.rmi.registry.*;
import java.util.Scanner;

public class cliente
{
	public static void main(String[] args) throws Exception
	{	  	
		int n, opc;
		float integral = 0.0f;
		Scanner s = new Scanner(System.in);	
		interfaz RefObRemoto = (interfaz)Naming.lookup("//localhost/servidor");

		do{

			System.out.println("1. Calcular Integral");
			System.out.println("2. Resetear Servidor");
			opc = s.nextInt();

			if(opc == 1)
			{
				System.out.println("Introduce numero de subintervalos (n): ");
				
				do{					
					n = s.nextInt();
					if(n < 1)
						System.out.println("ERROR: El subintervalo debe ser mayor que 0");

				}while(n < 1); 
				
				integral = RefObRemoto.integral(n);
				System.out.println("La integral obtenida es: "+integral);
			}

			else if(opc == 2)
			{
				RefObRemoto.reinicio();
			}

			else
				System.out.println("ERROR: Introduce una opcion valida");	
		
		}while(opc < 1 || opc > 2);
	}	
}	