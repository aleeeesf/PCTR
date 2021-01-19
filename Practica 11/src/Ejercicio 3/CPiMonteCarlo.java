import java.rmi.*;
import java.rmi.registry .*;

public class CPiMonteCarlo{    
    public static void main(String[] args) throws Exception
    {
        iPiMonteCarlo RefObRemoto2 = (iPiMonteCarlo)Naming.lookup("//localhost/servidore");
        RefObRemoto2.masPuntos(5000000);
        System.out.println("Resultado"+RefObRemoto2.Pi());
    }
}
