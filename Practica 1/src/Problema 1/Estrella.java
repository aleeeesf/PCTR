
public class Estrella extends CuerpoAstrofisico
{
	private long edad;
	
	public Estrella(double masa, String nombre, long edad)
	{
		super(masa, nombre);
		this.edad = edad;
	}
	
	public long edad()
	{
		return this.edad;
	}	
}
