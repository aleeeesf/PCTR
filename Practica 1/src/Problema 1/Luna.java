
public class Luna extends Satelite
{
	private final int duracion_orbita; //En horas
	
	public Luna(double masa, String nombre, int distancia_planeta, int duracion_orbita)
	{
		super(masa, nombre, distancia_planeta);
		this.duracion_orbita = duracion_orbita;
	}
	public int duracion_orbita()
	{
		return this.duracion_orbita;
	}	
}
