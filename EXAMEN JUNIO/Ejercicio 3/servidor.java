//Alejandro Serrano Fernández
//DNI: 20501318-S
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;
import java.rmi.RemoteException;
import java.rmi.Naming;
import java.util.Random;
 
 public class servidor extends UnicastRemoteObject implements interfaz                                 
 {
	float integral;

	public servidor() throws RemoteException
	{
		this.integral = 0.0f;
	}  
  
	public void reinicio() throws RemoteException
 	{
		this.integral = 0.0f;
	}  
 	
 	public float integral(int n) throws RemoteException
 	{
		float tamano = (float)1/n, inicio = 0, fin = tamano, punto;
		//System.out.println(tamano);

		for(int i = 0; i < n; i++)
		{
			punto = (float)(inicio+fin)/2;
			integral += (punto*punto);
			inicio = fin;
			fin += tamano;
			System.out.println(integral);
		}

		//(b-a)*f(a+b/2)
		integral = (1-0)*integral; 
		
		return integral;
	}

 	public static void main(String[] args)
 	  throws Exception 
 	{
 		interfaz servidor = new servidor(); 		
 		Naming.bind("servidor", servidor); 		  
 		System.out.println("Servidor Listo");
 	}	
 }	      