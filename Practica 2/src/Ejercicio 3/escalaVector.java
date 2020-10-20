import java.util.Random;

public class escalaVector {

	private final static int n = 3000000;
	private static int [] vector = new int[n];
	
	public void escalar(int escala)
	{
		for(int i = 0; i < n; i++)
		{
			vector[i] *= escala;
		}		
	}
	
	public void randomVector()
	{
		Random rand = new Random(System.nanoTime());
		for(int i = 0; i < n; i++)
		{
			vector[i] = rand.nextInt(10);
		}
		
	}
	public static void main(String[] args)
	{
		escalaVector P = new escalaVector();
		P.randomVector();
		P.escalar(2);		
	}
}
