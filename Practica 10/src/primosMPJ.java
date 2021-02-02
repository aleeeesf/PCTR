//Alejandro Serrano Fernandez
//20501318S

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
    int sumas[] = new int[size];
    int M[][] = new int[4][4];

    double inicTiempo = System.nanoTime();  

    if(rank == 0) //Proceso MASTER
    {
          //Sumar columna
          for(int i = 0; i < 4; i++)
          {
             for(int j = 0; j < 4; j++)
             {
                M[i][j] = r.nextInt();
             }
          }
    }

    MPI.COMM_WORLD.Scatter(M,0,4,MPI.INT,recv,0,4,MPI.INT,0);

    int recv[] = new int[4];
    int suma = 0;

    for(int i = 0; i < 4; i++)
    {
      suma += recv[i];
    }

    recv[4] = suma;

    MPI.COMM_WORLD.Gather(M,0,4,MPI.INT,recv,0,4,MPI.INT,0);

    if(rank == 0) //Proceso MASTER
    {
          int producto = 1;
          //Sumar columna
          for(int i = 0; i < 4; i++)
          {
                producto *= M[i][4];             
          }

          System.out.println("El producto es: "+producto);
    }

    MPI.Finalize();   
  }
}

