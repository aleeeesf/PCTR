
public class monitorImpresion {

	private static int impresoras = 3;
	private static boolean libre[] = new boolean[3];
	
	public monitorImpresion()
	{
		for(int i = 0; i < 3; i++)
		{
			libre[i] = true;
		}
	}
	
	public synchronized int pedir_impresora() throws InterruptedException
	{
		
		while(impresoras == 0) wait();
			
		int n = 0;
		while(n < 3 && !libre[n]) n++;
		
		synchronized(this)
		{
			libre[n] = false;
			impresoras--;
		}
		
		return n;
	}
	
	public synchronized void liberar_impresora(int i)
	{
		synchronized(this)
		{
			libre[i] = true;
			impresoras++;
		}
		
		notifyAll();
	}
}
