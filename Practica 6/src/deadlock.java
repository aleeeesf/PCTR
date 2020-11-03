
public class deadlock {

	public static int n;
	public static Object objA = new Object();
	public static Object objB = new Object();
		
	public static void main(String args[])
	{
				
		Runnable runnable = new Runnable() {
			public void run()
			{
				synchronized(objA)
				{
					synchronized(objB)
					{
						for(int i = 0; i < 20000; i++)
							n++;
					}
				}
			}	
		};
		
		Runnable runnable2 = new Runnable() {
			public void run()
			{
				synchronized(objB)
				{
					synchronized(objA)
					{
						for(int i = 0; i < 20000; i++)
						n++;
					}
				}
			}	
		};
		
		Thread thread1 = new Thread(runnable);
		Thread thread2 = new Thread(runnable2);
		thread2.start();
		thread1.start();


	}
}


