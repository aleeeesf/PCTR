
public class hebra extends Thread{

	public static int n = 0;
	private int id;
	private int iteraciones;
	
	public hebra(int id, int iteraciones){this.id = id; this.iteraciones = iteraciones;}
	
	public void run()
	{
		if(isPair(this.id))
		{
			for(int i = 0; i <= iteraciones; i++)	//Si es par aumenta
			{
				n++;
			}
		}
		
		else	//Si es impar disminuye
		{
			for(int i = 0; i <= iteraciones; i++)	
			{
				n--;				
			}
		}		
	}
	
	public boolean isPair(int id)
	{
		return (id % 2 == 0);
	}
}
