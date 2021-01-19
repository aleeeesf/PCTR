import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class algoLamport implements Runnable{

	private static int N = 6;
	private static boolean[] c = new boolean[N];
	private static int[] numero = new int[N];
	private int id;
	public volatile static int n = 0, it = 0;
	
	public algoLamport(int id)
	{
		this.id = id;
	}
	public int max(int[] numero)
	{
		int max = 0;
		for(int i = 0; i < N; i++)
		{
			if(numero[i] > max)
			{
				max = numero[i];
			}
		}
		
		return max;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub		
			c[id] = true;
			numero[id] = (1+max(numero));
			c[id] = false;
			
			for(int j = 0; j < N; j++)
			{
				while(c[id] == true);
				while((numero[j] != 0) && ((numero[id] > numero[j])));
				
			}			
			/*
			 * 		SECCIÓN CRÍTICA
			 */			
			
			switch(id%2)
			{
				case 0:
					for(int i = 0; i < 10000; i++) n++;
					break;
				case 1:
					for(int i = 0; i < 10000; i++) n--;
					break;
			}

			numero[id] = 0;
			it++;	
		}


	public static void main(String[] args)
	{
		Runnable r1 = new algoLamport(0);
		Runnable r2 = new algoLamport(1);
		Runnable r3 = new algoLamport(2);
		Runnable r4 = new algoLamport(3);
		Runnable r5 = new algoLamport(4);
		Runnable r6 = new algoLamport(5);
		ExecutorService ex= Executors.newFixedThreadPool(6);
		
		ex.execute(r1);
		ex.execute(r2);
		ex.execute(r3);
		ex.execute(r4);
		ex.execute(r5);
		ex.execute(r6);
		ex.shutdown();
		
		while(!ex.isTerminated());

		System.out.println(n);
	}
}
