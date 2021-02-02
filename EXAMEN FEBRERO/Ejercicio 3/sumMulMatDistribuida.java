//Alejandro Serrano Fernandez
//20501318S
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.*;
import java.text.ParsePosition;
import java.util.*;
import mpi.*;

public class sumMulMatDistribuida{
  public static void main(String args[])
  {

    MPI.Init(args);
    int rank = MPI.COMM_WORLD.Rank();
    int size = MPI.COMM_WORLD.Size();
     int M[][] = new int[4][4];

    double inicTiempo = System.nanoTime();  

    if(rank == 0) //Proceso MASTER
    {
          //Rellenar matriz
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

