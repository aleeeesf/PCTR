import java.rmi.*;

public class cPi
{
   public static void main(String[] args) throws Exception{
        
        iPi R = (iPi)Naming.lookup("//localhost/servidor");

        try
        {
            R.masPuntos(10000);
            System.out.println("Pi vale: "+R.pi());
        }catch(RemoteException e){}
    }
}
