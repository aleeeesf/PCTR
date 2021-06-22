//Alejandro Serrano Fernandez
// DNI: 20501318-S
//Situarse en la carpeta mpj/lib 
//javac -cp mpj.jar .\mpjMayor.java
//mpjrun.bat -np 2 mpjMayor

import mpi.*;
import java.util.Random;

public class mpjMayor{
    public static void main(String args[])
    {

        MPI.Init(args);
        int rank = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();
        int emisor = 0, receptor = 1;
        int tag = 100, vectorUnitSize = 5;  
        int vector[] = new int[5];
        int mayor[] = new int[1]; //Vector para almacenar 
        Random rand = new Random();        

        if(rank == 0) //Proceso emisor
        {
              //relleno el vector aleatoriamente
              for(int i = 0; i < vector.length; i++)
              {
                  vector[i] = rand.nextInt(10);
              }

              System.out.print("Emisor envia la lista: ");

              for(int i = 0; i < vector.length; i++)
              {
                  if(i != vector.length-1)
                    System.out.print(vector[i]+", ");
                  else
                    System.out.print(vector[i]);
              }
              System.out.println();
              MPI.COMM_WORLD.Send(vector, 0, vectorUnitSize, MPI.INT, receptor, tag);  // Se envía al proceso 1
              MPI.COMM_WORLD.Recv(mayor, 0, 1, MPI.INT, receptor, tag);  //Recibimos el mayor del proceso 1
              System.out.println("Emisor recibe: "+mayor[0]);
        }

        else  //Proceso receptor
        {
              int max = 0;
              MPI.COMM_WORLD.Recv(vector, 0, vectorUnitSize, MPI.INT, emisor, tag);  //Recibimos el vector del proceso emisor
            
              
              System.out.print("Receptor recibe la lista: ");

              for(int i = 0; i < vector.length; i++)
              {
                  if(i != vector.length-1)
                    System.out.print(vector[i]+", ");
                  else
                  System.out.print(vector[i]);
              }
              System.out.println();
              

              //Buscamos el mayor
              for(int i = 0; i < vector.length; i++)
              {
                  if(vector[i] > max)
                  {
                      max = vector[i];
                  }
              }
              
              mayor[0] = max;
              System.out.println("Receptor devuelve: "+max);
              MPI.COMM_WORLD.Send(mayor, 0, 1, MPI.INT, emisor, tag);          
        }

        MPI.Finalize();   
    }
}