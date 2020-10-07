public class sistemaSolar {
	
	public static void main(String args[])
	{
		//Los años del sol en miles de millones y la masa por 10^3 :)
		Estrella Cuerpo1 = new Sol(1989, "sol", 4603, 5778); 
		System.out.println("El "+Cuerpo1.nombre()+" tiene "+Cuerpo1.masa()+" x 10^3 kg y "+Cuerpo1.edad()+" miles de millones de años de vida" );
		
		Tierra tierra = new Tierra(5972,"La Tierra", true, 7594);
		System.out.println("La Tierra tiene un masa de: "+tierra.masa()+" x 10^24kg y "+tierra.habitantes()+" miles de millones de habitantes");
		
		Luna luna = new Luna(7349, "luna", 384400,27);
		System.out.println("La Luna tiene un masa de: "+luna.masa()+" × 1022 kg y su orbita dura "+luna.duracion_orbita()+" días");
		
		CuerpoPlanetario Kepler186f = new CuerpoPlanetario(1082,"Kepler-186f", false);
		if(Kepler186f.Vida())
			System.out.println("El planeta "+Kepler186f.nombre()+" es parecido a la Tierra y tiene vida");
		else 
			System.out.println("El planeta "+Kepler186f.nombre()+" es parecido a la Tierra y no tiene vida");
	}
}
