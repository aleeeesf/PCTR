import java.lang.Math;

public class Complejos {

	//[0] para la parte real y [1] para la parte imaginaria
	private double[] real_imaginario = new double[2];
	
	public Complejos()
	{
		this.real_imaginario[0] = 0.0;
		this.real_imaginario[1] = 0.0;
	}
	
	public Complejos(double real, double imaginario)
	{
		this.real_imaginario[0] = real;
		this.real_imaginario[1] = imaginario;
	}
	
	public double parteReal()
	{
		return this.real_imaginario[0];
	}
	
	public double parteImaginaria()
	{
		return this.real_imaginario[1];
	}
	
	public Complejos suma(Complejos c_)
	{
		Complejos resultado = new Complejos();
		resultado.real_imaginario[0] = this.real_imaginario[0] + c_.parteReal();
		resultado.real_imaginario[1] = this.real_imaginario[1] + c_.parteImaginaria();
		return resultado;
	}
	
	public Complejos resta(Complejos c_)
	{
		Complejos resultado = new Complejos();
		resultado.real_imaginario[0] = this.real_imaginario[0] - c_.parteReal();
		resultado.real_imaginario[1] = this.real_imaginario[1] - c_.parteImaginaria();
		return resultado;
	}
	
	public Complejos Producto(Complejos c_)
	{
		Complejos resultado = new Complejos();
		resultado.real_imaginario[0] = this.real_imaginario[0] * c_.parteReal();
		resultado.real_imaginario[1] = this.real_imaginario[1] * c_.parteImaginaria();
		return resultado;
	}
	
	public double Modulo()
	{
		double mod = 0.0;
		mod = Math.pow(this.parteImaginaria(),2);
		mod += Math.pow(this.parteReal(), 2);
		mod = Math.sqrt(mod);
		return mod;
	}
	
	public Complejos Cociente(Complejos C)
	{
		double numerador_real, denominador_real, numerador_imaginario, denominador_imaginario;
		double a, b, c ,d;
		
		a = this.parteReal();
		b = this.parteImaginaria();
		c = C.parteReal(); 
		d = C.parteImaginaria();
		
		numerador_real = (a*c)+(b*d);
		numerador_imaginario = (b*c) - (a*d);
		denominador_real = Math.pow(c, 2) + Math.pow(d,2);
		denominador_imaginario = Math.pow(c, 2) + Math.pow(d,2);
		
		Complejos Res = new Complejos(numerador_real/denominador_real, numerador_imaginario/denominador_imaginario);
		
		return Res;		
	}
	
	public int Cuadrante()
	{
		if(this.parteReal() > 0 && this.parteImaginaria() > 0)
			return 1;
		else if(this.parteReal() < 0 && this.parteImaginaria() > 0)
			return 2;
		else if(this.parteReal() < 0 && this.parteImaginaria() < 0)
			return 3;
		else return 4;		
	}
}
