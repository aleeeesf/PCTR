import java.rmi.server.UnicastRemoteObject;
import java.rmi.*;
import java.util.Random;
import java.lang.Math.*;

public class sPi extends UnicastRemoteObject implements iPi {
    
    private static int nPuntos = 0;    

    public sPi() throws RemoteException{}

    public void masPuntos(int puntos) throws RemoteException
    {
        nPuntos += puntos;
    }

    public double pi() throws RemoteException
    {
        Random r = new Random();
        double x = Math.pow(r.nextDouble(),2),
               y = Math.pow(r.nextDouble(),2), 
               raiz = Math.sqrt((x+y)),
               res = 0;
        int exitos = 0;
        
        if(raiz <= 1)
            exitos++;

        res = (double)((exitos*4)/nPuntos);

        return res;
    } 
    
    public void reset() throws RemoteException
    {
        nPuntos = 0;
    }

    public static void main(String[] args) throws Exception
    {
        iPi servidor = new sPi();
        Naming.bind("servidor",servidor);
        System.out.println("Servidor Listo");
    }
}