
public class tareaRunnable implements Runnable{
	
	private static int n = 0;
	private int iteraciones;
	private int id;
	public static Object object = new Object();
	
	public tareaRunnable(int id, int iteraciones) {this.id = id; this.iteraciones = iteraciones;}
	
	public void incrementar() {n++;}
	public void decrementar() {n--;}
	public static int dato() {return n;}
	
	public void run() {
		
		synchronized(object)	//Sincronización
		{
			if(isPair(this.id))
			{
				for(int i = 0; i <= iteraciones; i++)	//Si es par aumenta
				{
					incrementar();
				}
			}
			
			else	//Si es impar disminuye
			{
				for(int i = 0; i <= iteraciones; i++)	
				{
					decrementar();				
				}
			}	
		}	
	}
	
	public boolean isPair(int id)
	{
		return (id % 2 == 0);
	}
}
