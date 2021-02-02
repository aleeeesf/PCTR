import java.rmi.*;
import java.rmi.Naming;

public interface iPi extends Remote
{
    public void masPuntos(int puntos) throws RemoteException;
    public double pi() throws RemoteException;
    public void reset() throws RemoteException;
}
