import java.util.Scanner;

public class Usa_tareaRunnableSeguro {

	public static void main(String args[])
	{
		final int tam = 4;
		int iteraciones;
		Thread[] threads = new Thread[tam];
		
		try (Scanner S = new Scanner(System.in)) {
			System.out.println("Introduce numero de iteraciones");
			iteraciones = S.nextInt();
		}
		
		for(int i = 0; i < 4; i++)
		{
			tareaRunnableSeguro t = new tareaRunnableSeguro(i,iteraciones);
			threads[i] = new Thread(t);
			threads[i].start();
		}
		
		try
		{
			for(int i = 0; i < 4; i++)
			{
				threads[i].join();
			}
		}catch(Exception ex){}
		
		System.out.print(tareaRunnableSeguro.dato());
	}
}
