//Alejandro Serrano Fernández  
//DNI: 20501318-S
import java.rmi.*;

public interface interfaz extends Remote
{
    public float integral(int n)  throws RemoteException;
    public void reinicio() throws RemoteException;
}