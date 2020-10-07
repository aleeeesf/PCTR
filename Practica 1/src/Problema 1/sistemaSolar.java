public class sistemaSolar {
	
	public static void main(String args[])
	{
		//Los años del sol en miles de millones y la masa por 10^3 :)
		Estrella Cuerpo1 = new Sol(1989, "sol", 4603, 5778); 
		System.out.print("El "+Cuerpo1.nombre()+" tiene "+Cuerpo1.masa()+"10^3 kg y "+Cuerpo1.edad()+" miles de millones de años de vida" );
		
	}
}
