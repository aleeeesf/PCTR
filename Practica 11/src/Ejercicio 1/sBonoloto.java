import java.rmi.server.UnicastRemoteObject;
import java.util.Random;
import java.rmi.RemoteException;
import java.rmi.Naming;

public class sBonoloto extends UnicastRemoteObject
    implements iBonoLoto
{
    int[] generados = new int[6];

    public sBonoloto() throws RemoteException
    {}
    public void resetServidor() throws RemoteException
    {
        boolean repetido;
        int n = 0, nRandom;
        Random r = new Random();

        while(n < 6)
        {
            repetido = false;
            nRandom = r.nextInt(50);            
            
            for(int i = 0; i < n; i++)
            {
                if(nRandom == generados[i])
                    repetido = true;
            }

            if(!repetido)
            {
                generados[n] = nRandom;
                n++;
            }
        }

        for(int i = 0; i < 6; i++) System.out.println(generados[i]+" ");

    }
    public boolean compApuesta(int[] apuesta)  throws RemoteException
    {
        boolean ganador = false;
        int cont = 0;
        for(int i = 0; i < 6; i++)
        {
            for(int j = 0; j < 6; j++)
            {
                if(apuesta[i] == generados[j])
                    cont++;
            }       
        }

        if(cont < 6) return false;
        else return true;
    }

    public static void main(String[] args) throws Exception
    {
        iBonoLoto servidor = new sBonoloto();
        Naming.bind("servidor", servidor);
        System.out.println("Servidor Listo");
    }

}
