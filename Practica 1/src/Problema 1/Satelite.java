
public class Satelite extends CuerpoAstrofisico
{
	private int distancia_a_planeta;
	
	public Satelite(double masa, String nombre, int distancia_planeta)
	{
		super(masa, nombre);
		this.distancia_a_planeta = distancia_planeta;
	}
	
	public int distancia_a_planeta()
	{
		return this.distancia_a_planeta;
	}	
}
