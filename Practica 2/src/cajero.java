
public class cajero implements Runnable {

	private cuentaCorriente c;
	private boolean es_ingreso;
	private double cantidad;
	
	public cajero() {}
	
	public cajero(cuentaCorriente c, boolean es_ingreso, double cantidad)
	{
		this.c = c;
		this.es_ingreso = es_ingreso;
		this.cantidad = cantidad;
	}
	
	public double saldo()
	{
		return c.saldoCuenta();
	}
	@Override
	public void run() {
		
		if(es_ingreso)
		{
			c.deposito(this.cantidad);
		}
		else
		{
			c.reintegro(this.cantidad);
		}		
	}	
}
