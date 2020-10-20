import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class algEisenbergMcGuire implements Runnable{

	//Algoritmo obtenido del libro de Palma et Al
	//ocioso: el proceso está ocioso con respecto a la sección crítica
	//esperando: el proceso desea entrar en la sección crítica
	//ejecutando: se encuentra dentro de la sección crítica
	public static enum estados {ocioso, esperando, ejecutando}
	private static volatile estados flags[] = new estados[4];
	public static volatile int n = 0;
	private static volatile int indice = 0;
	private int j;
	private int id;	
	public static int N = 4;
	

	public algEisenbergMcGuire(int id)
	{
		this.id = id;
		for(int i = 0; i < N; i++) flags[i] = estados.ocioso;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		do
		{
			flags[id] = estados.esperando;
			
			j = indice;	
			while(j != id)
			{
				if(flags[j] != estados.ocioso)
					j = indice;
				else j = (j+1)%N;		
				
			}
			
			flags[id] = estados.ejecutando;
			j = 0;
			while((j < N) && ((j == id) || (flags[j] != estados.ejecutando)))
				j++;
			
		}while(!((j >= N) && ((indice == id) || (flags[indice] == estados.ocioso))));
		
		indice = id;
		
		/*
 				 SECCION CRÍTICA
		 */
		switch(id%2)
		{
			case 0:
				for(int i = 0; i < 10000; i++) n++;
				break;
			case 1:
				for(int i = 0; i < 9000; i++) n--;
				break;
		}
		
		/*
				FIN	SECCION CRÍTICA
		 */
	
		j = (indice+1) % N;
		while(flags[j] == estados.ocioso)
			j = (j+1) % N;
		
		indice = j;
		flags[id] = estados.ocioso;		
	}
	
	public static void main(String[] args)
	{
		Runnable r1 = new algEisenbergMcGuire(0);
		Runnable r2 = new algEisenbergMcGuire(1);
		Runnable r3 = new algEisenbergMcGuire(2);
		Runnable r4 = new algEisenbergMcGuire(3);
		ExecutorService ex= Executors.newFixedThreadPool(4);
		
		ex.execute(r1);
		ex.execute(r2);
		ex.execute(r3);
		ex.execute(r4);
		ex.shutdown();
		while(!ex.isTerminated());

		System.out.println(n);
	}


}
