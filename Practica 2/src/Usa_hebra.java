import java.util.Scanner;

public class Usa_hebra {

	public static void main(String args[])
	{
		final int tam = 4;
		int iteraciones;
		hebra[] hebras = new hebra[tam];
		
		try (Scanner S = new Scanner(System.in)) {
			System.out.println("Introduce numero de iteraciones");
			iteraciones = S.nextInt();
		}
		
		for(int i = 0; i < 4; i++)
		{
			hebras[i] = new hebra(i,iteraciones);
			hebras[i].start();
		}
		
		try
		{
			for(int i = 0; i < 4; i++)
			{
				hebras[i].join();
			}
		}catch(Exception ex){}
		
		System.out.print(hebra.n);
	}
}
