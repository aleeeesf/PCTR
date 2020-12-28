import java.rmi.*;
import java.rmi.registry .*;

public class CPiMonteCarlo{
    
    public static void main(String[] args) throws Exception
    {
        iPiMonteCarlo RefObRemoto = (iPiMonteCarlo)Naming.lookup("//localhost/servidore");
        RefObRemoto.masPuntos(5000);
        System.out.println("Resultado"+RefObRemoto.Pi());
    }
}
