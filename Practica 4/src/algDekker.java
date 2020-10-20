
public class algDekker extends Thread
{
	public boolean[] cerrojo = new boolean[2];
	public int turno = 0;
	private int id;
	public static volatile int n = 0;
	
	@Override
	public void run()
	{
		cerrojo[id] = true;
		while(cerrojo[1-id])
		{
			cerrojo[id] = false;
			while(turno == 1 - id) {}
			cerrojo[id] = true;
		}
		
		//Source Code
		switch(id)
		{
			case 1:
				for(int i = 0; i < 100000; i++) n++;
				break;
			case 2:
				for(int i = 0; i < 100000; i++) n++;
				break;
		}
		
		turno = 1 - id;
		cerrojo[id] = false;	
	}	

	public static void main(String[] args)
	{
	      tryFour h1 = new tryFour(1);
	      tryFour h2 = new tryFour(2);
	      h1.start(); h2.start();
	      try
	      {
		      h1.join(); h2.join();
	      }catch(Exception e) {}

	      System.out.println(n);
		
	}
}