import mpi.*;

  public class reduccion {
     public static void main(String args[]){
        MPI.Init(args);
        int rank = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();
        int datos[] = new int[1];
        int mis_datos[] = new int[1];
        
        if(rank==0){
             mis_datos[0]=10;    
            }
        
        MPI.COMM_WORLD.Reduce(mis_datos, 0 ,datos ,0 , 1, MPI.INT, MPI.SUM,0);
        
        if(rank==0){
                System.out.println(mis_datos[0]);
        }
        MPI.Finalize();
     }
}