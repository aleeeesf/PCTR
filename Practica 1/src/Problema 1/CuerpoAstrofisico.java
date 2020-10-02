
public class CuerpoAstrofisico {
	private double masa;
	private String nombre;
	
	public CuerpoAstrofisico(double masa, String nombre)
	{
		this.masa = masa;
		this.nombre = nombre;
	}
	
	public double masa()
	{
		return this.masa;
	}
	
	public String nombre()
	{
		return this.nombre;
	}
}
