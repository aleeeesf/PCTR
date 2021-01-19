
public class algDekker extends Thread
{
	public static volatile boolean[] cerrojo = new boolean[2];
	public static volatile int turno = 0;
	private int id;
	public static volatile int n = 0;
	
	public algDekker(int id)
	{
		this.id = id;
	}
	@Override
	public void run()
	{ 		
		//Source Code
		switch(id)
		{
			case 1:
		                cerrojo[0] = true;
		                while(cerrojo[1])
		                {
                                     if(turno == 1)
                                     {
                                         cerrojo[0] = false;
                                         while(turno == 1);
			                 cerrojo[0] = true;
                                     }		             
		                }
				for(int i = 0; i < 10000; i++) n++;				
				turno = 1;
				cerrojo[0] = false;
  				break;
			
                         case 2: 
		                cerrojo[1] = true;
		                while(cerrojo[0])
		                {
                                     if(turno == 0)
                                     {
                                         cerrojo[1] = false;
                                         while(turno == 0);
			                 cerrojo[1] = true;
                                     }		             
		                }
				for(int i = 0; i < 10000; i++) n--;
				turno = 0;
				cerrojo[1] = false;
				break;
		}	
	}	

	public static void main(String[] args)
	{
	      algDekker h1 = new algDekker(1);
	      algDekker h2 = new algDekker(2);
	      h1.start(); h2.start();
	      try
	      {
		      h1.join(); h2.join();
	      }catch(Exception e) {}

	      System.out.println(n);
		
	}
}