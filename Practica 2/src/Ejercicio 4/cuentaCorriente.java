
public class cuentaCorriente {

	private int num_cuenta;
	private double saldo;
	
	public cuentaCorriente(int num_cuenta, double saldo) 
	{	
		this.num_cuenta = num_cuenta;
		this.saldo = saldo;
	}
	
	public void deposito(double cantidad)
	{
		this.saldo += cantidad;
	}
	
	public void reintegro(double cantidad)
	{
		if(cantidad <= this.saldo)
			this.saldo -= cantidad;
		else
			System.out.println("Cantidad introducida mayor que saldo disponible!");
		
	}
	
	public double saldoCuenta()
	{
		return this.saldo;
	}
	
	public int numeroCuenta()
	{
		return this.num_cuenta;
	}
	
}
