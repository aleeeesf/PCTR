
public class Tierra extends CuerpoPlanetario
{
	private int Habitantes;
	
	public Tierra(double masa, String nombre, boolean vida, int habitantes)
	{
		super(masa, nombre, vida);
		this.Habitantes = habitantes;
	}
	
	public int habitantes()
	{
		return this.Habitantes;
	}
}
