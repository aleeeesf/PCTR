import java.rmi.*;
import java.rmi.registry .*;
import java.util.Scanner;

public class cBonoloto {
    public static void main ( String [] args )
    throws Exception
    {
        Scanner s = new Scanner(System.in);
        int[] papeleta = new int[6];
        boolean jugando = true;
        iBonoLoto RefObRemoto = (iBonoLoto)Naming.lookup("//localhost/servidor") ;

        while(jugando)
        {
            for(int i = 0; i < 6; i++)
            {
                System.out.println("Introduce numero ["+i+"]: ");
                papeleta[i] = s.nextInt();
            }
    
            System.out.println(RefObRemoto.compApuesta(papeleta));            
        }
    }
}
