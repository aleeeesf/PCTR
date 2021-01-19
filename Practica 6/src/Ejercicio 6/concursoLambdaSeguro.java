
public class concursoLambdaSeguro {

	private boolean id;
	public static int n;
	public static Object object = new Object();
	public boolean getId() {return id;}
	
	public static void main(String args[])
	{
				
		Runnable runnable = () -> {
			synchronized(object)
			{
				for(int i = 0; i < 20000; i++)
					n++;
			}
		};
		
		Runnable runnable2 = () -> {
			synchronized(object)
			{
				for(int i = 0; i < 20000; i++)
					n--;
			}
		};
		
		Thread thread1 = new Thread(runnable);
		Thread thread2 = new Thread(runnable2);
		thread1.start();
		thread2.start();
		
		try
		{
			thread1.join();
			thread2.join();
			
		}catch(Exception ex) {}
		
		System.out.println(concursoLambdaSeguro.n);
	}
}
