import java.util.concurrent.*;

public class lectorEscritor {

	private static int n = 0;
	private static boolean escribiendo = false;
	
	public synchronized void inicia_lectura() throws InterruptedException
	{
		while(escribiendo)
		{
			wait();
		}
		synchronized(this){ n++;}
		System.out.println("Im reading");
}
	
	public synchronized void fin_lectura() throws Exception
	{
		System.out.println("Estoy terminando de leer");
		synchronized(this) {n--;}
		if(n == 0) notifyAll();
	}
	
	public synchronized void inicia_escritura() throws InterruptedException
	{
		while(escribiendo || n > 0)
		{
			wait();
		}
		System.out.println("im writing");
		synchronized(this) {escribiendo = true;} 		
	}
	
	public synchronized void fin_escritura() throws Exception
	{
		System.out.println("Estoy terminando de escribir");
		synchronized(this) {escribiendo = false;}
		notifyAll();
	}
	
}
