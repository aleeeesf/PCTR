
public class Sol extends Estrella
{
	private double temperatura;
	
	public Sol(double masa, String nombre, long edad, double temperatura)
	{
		super(masa, nombre, edad);
		this.temperatura = temperatura;
	}
	
	public double temperatura()
	{
		return this.temperatura;
	}	
}
