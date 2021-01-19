//Hebra seguro hace uso de ObjetoSeguro.java
public class hebraSeguro extends Thread{

	public static int n = 0;
	private int id;
	private int iteraciones;
	private static ObjetoSeguro obj = new ObjetoSeguro();
	
	public hebraSeguro(int id, int iteraciones){this.id = id; this.iteraciones = iteraciones;}
	
	public void run()
	{
		if(isPair(this.id))
		{
			for(int i = 0; i <= iteraciones; i++)	//Si es par aumenta
			{
				obj.incrementar_n();
			}
		}
		
		else	//Si es impar disminuye
		{
			for(int i = 0; i <= iteraciones; i++)	
			{
				obj.decrementar_n();				
			}
		}		
	}
	
	public boolean isPair(int id)
	{
		return (id % 2 == 0);
	}
}
