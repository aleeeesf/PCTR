
public class deadlock {

	public static int n;
	public static Object objA = new Object();
	public static Object objB = new Object();
	public static Object objC = new Object();
	
	public static void main(String args[])
	{
				
		Runnable runnable = new Runnable() {
			public void run()
			{
				synchronized(objA)
				{
					try {
						Thread.currentThread().sleep(100);
					}catch(Exception e) {}
					synchronized(objB)
					{

						System.out.println("I entered");
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
					try {
						Thread.currentThread().sleep(100);
					}catch(Exception e) {}
					synchronized(objA)
					{
						System.out.println("I entered too");
						for(int i = 0; i < 20000; i++)
						n++;
					}
				}
			}	
		};

		Runnable runnable3 = new Runnable() {
			public void run()
			{
				synchronized(objC)
				{
					try {
						Thread.currentThread().sleep(100);
					}catch(Exception e) {}
					synchronized(objB)
					{
						System.out.println("I entered too");
						for(int i = 0; i < 20000; i++)
						n++;
					}
				}
			}	
		};
		
		Thread thread1 = new Thread(runnable);
		Thread thread2 = new Thread(runnable2);
		Thread thread3 = new Thread(runnable3);
		thread2.start();
		thread1.start();
		thread3.start();


	}
}


