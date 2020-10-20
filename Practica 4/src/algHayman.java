import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class algHayman{

	private static boolean c0 = false, c1 = false;
	public volatile static int n = 0;
	public static int turno = 1;
	public static void main()
	{
		Runnable r1 = ()->{
			c0 = true;
			while(turno != 0)
			{
				while(c1);
				turno = 0;
			}
			
			//seccion crítica
			for(int i = 0; i < 100000; i++) n++;
			
			c0 = false;			
		};
		
		Runnable r2 = ()->{
			c1 = true;
			while(turno != 1)
			{
				while(c0);
				turno = 1;
			}
			
			//seccion crítica
			for(int i = 0; i < 100000; i++) n--;
			c1 = false;			
		};
		
		ExecutorService ex= Executors.newFixedThreadPool(2);
		
		ex.submit(r1);
		ex.submit(r2);
		
		ex.shutdown();
		while(!ex.isTerminated());

		System.out.println(n);
	}
}
