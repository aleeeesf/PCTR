import mpi.*;

  public class difusReduc{
     public static void main(String args[]){
        MPI.Init(args);
        int rank = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();
        double pi=0.0, mypi, h, sum, x;
        int intervalos=50;
        if(rank==0){
        	System.out.println("Fijando 50 intervalos... ");
        	intervalos = 50;
        }	
        MPI.COMM_WORLD.Bcast(intervalos, 0, 1, MPI.INT, 0);
        h=1/(double)intervalos;
        sum=0.0; 
        for(int i=rank;i<=intervalos; i+=size){
        	x=h*((double)i-0.5);
        	sum += 4.0/(1.0+x*x);
        }	
        mypi=h*sum;
        MPI.COMM_WORLD.Reduce(mypi, 0, pi, 0, 1, MPI.DOUBLE, MPI.SUM, 0);
        if(rank==0)System.out.println("La aproximacion a PI es: "+pi);
        MPI.Finalize();
     }
}
