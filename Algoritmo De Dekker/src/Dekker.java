
public class Dekker extends Thread
{
	public boolean[] cerrojo = new boolean[2];
	public int turno = 0;
	private int id;
	
	@Override
	public void run()
	{
		cerrojo[id] = true;
		while(cerrojo[1-id])
		{
			cerrojo[0] = false;
			while(turno == 1) {}
			cerrojo[id] = true;
		}
		
		//Source Code
		
		turno = 1;
		cerrojo[id] = false;
	}	

	public static void main(String[] args)
	{
		
		
	}
}