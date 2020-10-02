
public class CuerpoPlanetario extends CuerpoAstrofisico
{
	private boolean Vida;
	
	public CuerpoPlanetario(double masa, String nombre, boolean vida)
	{
		super(masa, nombre);
		this.Vida = vida;
	}
	
	public boolean Vida()
	{
		return this.Vida;
	}
}
