import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.*;
import java.text.ParsePosition;
import java.util.*;
import mpi.*;

public class primosMPJ{

  public static boolean esPrimo(long n){
    if(n<=1) return(false);
    for(long i=2; i<=Math.sqrt(n); i++)
      if(n%i == 0) return(false);
    return(true);
  }	
  public static void main(String args[])
  {

    MPI.Init(args);
    int rank = MPI.COMM_WORLD.Rank();
    int size = MPI.COMM_WORLD.Size();
    int emisor = 0; int receptor = 1;
    int tag = 100; int unitSize = 1;
    int nPuntos = 1000000;
    long linf        = (nPuntos/(size-1)) * rank;
    long lsup        = (nPuntos/(size-1)) * (rank+1);
    int total = 0;
    int primos[] = new int[size];

    long inicTiempo = System.nanoTime();  

    if(rank != size-1) //Proceso receptor
    {
          System.out.println("linf id"+rank+" "+linf);
          System.out.println("lsup id"+rank+" "+lsup);

          for(long i=(linf+1); i<=lsup;i++)
          {
              if(primosMPJ.esPrimo(i)) total++;
          }

          primos[rank] = total;   
          MPI.COMM_WORLD.Send(primos, rank, 1, MPI.INT, size-1, tag);
    }

    else  //Proceso emisor
    {
          for(int i = 0; i < size-1; i++)
            MPI.COMM_WORLD.Recv(primos, i, 1, MPI.INT, i, tag);
          
            for(int i = 0; i < size; ++i)
            {
              System.out.println("primos["+i+"]"+primos[i]);
            }   

          int nPrimos = 0;
          for(int i = 0; i < size; ++i)
          {
            System.out.println(primos[i]);
            nPrimos += primos[i];
          }          
          System.out.println("Primos hallados: "+nPrimos);          
          
          long tiempoTotal = (System.nanoTime()-inicTiempo)/(long)1.0e9; 
          System.out.println("Calculo finalizado en "+tiempoTotal+" segundos");
    }

    MPI.Finalize();   
  }
}

