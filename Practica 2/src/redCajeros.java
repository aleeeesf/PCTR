
public class redCajeros {
//Creo 20000 cajeros, 10000 ingresan y 10000 reintegran
//Saldo teorico final = 0;
	public static void main(String args[])
	{
		cuentaCorriente Cuenta = new cuentaCorriente(97854,0);
		boolean es_ingreso;
		Thread[] threads = new Thread[20000];
		cajero cajero1 = new cajero();
		
		for(int i = 0; i < 20000; i++)
		{
			if(i % 2 == 0)
				es_ingreso = true;
			else
				es_ingreso = false;
			
			cajero1 = new cajero(Cuenta,es_ingreso,4000.00);
			threads[i] = new Thread(cajero1);
			threads[i].start();
		}
		
		try
		{
			for(int i = 0; i < 20000; i++)
			{
				threads[i].join();
			}
		}catch(Exception ex) {}
		
		System.out.println(cajero1.saldo());		
	}
}
