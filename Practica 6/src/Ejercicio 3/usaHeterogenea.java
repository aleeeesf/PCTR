//usaHeterogenea debe de ser compilado junto a Heterogena.java

public class usaHeterogenea implements Runnable{

	public static Heterogenea s = new Heterogenea();
	private int id;
	
	usaHeterogenea(int id)
	{
		this.id = id;
	}
	@Override
	public void run() {

		if(isPair(id))
		{
			for(int i = 0; i < 100000; i++)
			{
				s.incrementar_n();
				s.incrementar_m();
			}
		}
		else
		{
			for(int i = 0; i < 100000; i++)
			{
				s.decrementar_n();
				s.decrementar_m();
			}
		}

	}
	
	public boolean isPair(int id)
	{
		return (id % 2 == 0);
	}
	
	public static void main(String args[])
	{
		Thread hilos[] = new Thread[10];
		
		for(int i = 0; i < 10; i++)
		{
			hilos[i] = new Thread(new usaHeterogenea(i));
			hilos[i].start();
		}
		
		try
		{
			for(int i = 0; i < 10; i++)
			{
				hilos[i].join();
			}
		}catch(Exception ex) {}
		
		System.out.println("El valor de n es: "+s.obtener_n());
		System.out.println("El valor de m es: "+s.obtener_m());
		
	}

	
}
