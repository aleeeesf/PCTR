import java.rmi.server.UnicastRemoteObject;
import java.util.Random;
import java.rmi.RemoteException;
import java.rmi.Naming;

public class SPiMonteCarlos extends UnicastRemoteObject
implements iPiMonteCarlo {
    
    private int puntos;
    public SPiMonteCarlos() throws RemoteException
    {}

    public void reset() throws RemoteException
    {
        puntos = 0;
    }
    public void masPuntos(int nPuntos)  throws RemoteException
    {
        this.puntos += nPuntos;
    }

    public int Pi() throws RemoteException
    {
        Random r = new Random();
        int exitos = 0;
        double x, y, z;

        for (int i = 0; i < puntos; i++)
        {
            x = r.nextDouble() * 2 - 1;
            y = r.nextDouble() * 2 - 1;
            z = x*x + y*y;

            if(z <= 1)
                exitos++;
        }
        return ((exitos/ puntos)*4);
    }

    public static void main(String[] args) throws Exception
    {
        iBonoLoto servidor = new sBonoloto();
        Naming.bind("servidore", servidor);
        System.out.println("Servidor Listo");
    }

}
