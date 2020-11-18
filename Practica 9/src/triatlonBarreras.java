import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class triatlonBarreras implements Runnable{

	public final static int participantes = 100;
	private static CyclicBarrier c = new CyclicBarrier(participantes);
	private Random r = new Random(System.nanoTime());
	private int id;
	public static int[][] tiempos = new int[3][participantes];
	
	public triatlonBarreras(int id)
	{
		this.id = id;
	}
	
	private void natacion()
	{
		int tiempo = r.nextInt(100);
		try
		{
			Thread.currentThread().sleep(tiempo);
		}catch(InterruptedException e) {}

		//System.out.println(Thread.currentThread().getName()+"Está nadando");
		try {
			c.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		tiempos[0][id] = tiempo;
	}
	
	private void ciclista()
	{
		int tiempo = r.nextInt(100);
		try
		{
			Thread.currentThread().sleep(tiempo);
		}catch(InterruptedException e) {}
		//System.out.println(Thread.currentThread().getName()+"Está pedaleando");
		try {
			c.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		tiempos[1][id] = tiempo;
	}
	
	private void a_pie()
	{
		int tiempo = r.nextInt(100);
		try
		{
			Thread.currentThread().sleep(tiempo);
		}catch(InterruptedException e) {}
		//System.out.println(Thread.currentThread().getName()+"Está corriendo");
		try {
			c.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		tiempos[2][id] = tiempo;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Han empezado a nadar");
		natacion();
		System.out.println("Han empezado a pedalear");
		ciclista();
		System.out.println("Han empezado a correr");
		a_pie();
	}
	
	public static void main(String args[])
	{
		int ganador_natacion = Integer.MAX_VALUE, ganador_ciclista= Integer.MAX_VALUE, ganador_apie= Integer.MAX_VALUE;
		
		final int nThreads = participantes;
		ThreadPoolExecutor ex = new ThreadPoolExecutor(nThreads, nThreads,0L,TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
		
		for(int id = 0; id < participantes; id++)
			ex.execute(new triatlonBarreras(id));
		
		ex.shutdown();
		while(!ex.isTerminated());
		
		
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; i < participantes; i++)
			{
				switch(i)
				{
					case 0:
							if(tiempos[i][j] < ganador_natacion)
							{
								ganador_natacion = tiempos[i][j];
							}
							break;						
						
					case 1:
							if(tiempos[i][j] < ganador_ciclista)
							{
								ganador_ciclista = tiempos[i][j];
							}
							break;	
					case 2:
							if(tiempos[i][j] < ganador_apie)
							{
								ganador_apie = tiempos[i][j];
							}
							break;	
				}

			}
		}
		
		
		System.out.println("El ganador en la carrera de natacion es de: "+ganador_natacion);
		System.out.println("El ganador en la carrera de ciclismo es de: "+ganador_ciclista);
		System.out.println("El ganador en la carrera de a pie es de: "+ganador_apie);
	}
	
}
