import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Random;
import java.rmi.RemoteException;
import java.rmi.Naming;

public class sLibros extends UnicastRemoteObject
implements iLibros
{
    private ArrayList<Libro> baseDatos = new ArrayList<Libro>();
    public sLibros() throws RemoteException
    {}
    public void addLibro(Libro L) throws RemoteException
    {
        if(!baseDatos.contains(L))
        {
            baseDatos.add(L);
        }

        //System.out.println(L.Autor());
    }
    public boolean consultar(String titulo, String autor) throws RemoteException
    {
        boolean encontrado = false;

        for(Libro L:baseDatos)
        {
            if(L.Titulo().equals(titulo) && L.Autor().equals(autor))
                encontrado = true;
        }
        System.out.println(encontrado);
        return encontrado;
    }
    public Libro extraer(String titulo, String autor) throws RemoteException
    {
        Libro encontrado = new Libro();

        for(Libro L:baseDatos)
        {
            if(L.Titulo() == titulo && L.Autor() == autor)
                encontrado = L;
        }

        return encontrado;
    }    


    public static void main(String[] args) throws Exception
    {
        iLibros servidor = new sLibros();
        Naming.bind("servidor", servidor);
        System.out.println("Servidor Listo");
    }
}
