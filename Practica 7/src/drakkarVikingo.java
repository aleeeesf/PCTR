
public class drakkarVikingo {

	private static int marmita = 10;
	
	public synchronized void servir()
	{
		if(marmita > 0)
		{
			synchronized(this) {marmita--;}
			System.out.println("Me he comido una anguila y quedan: "+marmita+" anguilas");
			notifyAll();
		}
		
		else
		{
			try {
				wait();
			}catch(Exception e){}			
		}
	}
	
	public synchronized void llenar()
	{
		if(marmita > 0)
		{
			try
			{
				wait();
			}catch(Exception e) {}			
		}
		
		else
		{
			System.out.println("El cocinero ha rellenado la marmita");
			synchronized(this) { marmita = 10;}
			notifyAll();
		}
	}
}
