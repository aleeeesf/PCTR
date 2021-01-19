import java.rmi.*;

public interface iLibros extends Remote 
{
    public void addLibro(Libro L) throws RemoteException; 
    public boolean consultar(String titulo, String autor) throws RemoteException;
    public Libro extraer(String titulo, String autor) throws RemoteException;
}
