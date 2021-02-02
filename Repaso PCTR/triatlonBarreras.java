import java.util.concurrent.*;
import java.util.Random;

public class triatlonBarreras implements Runnable{
    
    private static final int nParticipantes = 10;
    public static int[][] tiempos = new int[3][nParticipantes];
    private static CyclicBarrier c = new CyclicBarrier(nParticipantes);
    private Random r = new Random();
    private int id;

    public triatlonBarreras(int id)
    {
        this.id = id;
    }

    public void natacion()
    {
        int tiempo = r.nextInt(100);
/*
        try{
            Thread.currentThread().sleep(200);
        }catch(InterruptedException e){}
*/
        try
        {
            c.await();
        }catch(InterruptedException | BrokenBarrierException e){}

        tiempos[0][id] = tiempo;
    }

    public void ciclista()
    {
        int tiempo = r.nextInt(100);
/*
        try{
            Thread.currentThread().sleep(200);
        }catch(InterruptedException e){}
*/
        try
        {
            c.await();
        }catch(InterruptedException | BrokenBarrierException e){}

        tiempos[1][id] = tiempo;
    }

    public void aPie()
    {
        int tiempo = r.nextInt(100);
/*
        try{
            Thread.currentThread().sleep(200);
        }catch(InterruptedException e){}
*/
        try
        {
            c.await();
        }catch(InterruptedException | BrokenBarrierException e){}

        tiempos[2][id] = tiempo;
    }

    public void run()
    {
        System.out.println("Empezando natacion");
        natacion();
        System.out.println("Empezando ciclista");
        ciclista();
        System.out.println("Empezando a pie");
        aPie();
    }

    public static void main(String[] args)
    {
        final int nThreads = 8;
        ExecutorService ex = Executors.newFixedThreadPool(nThreads);

        for(int i = 0; i < nParticipantes; i++)
        {
            ex.execute(new triatlonBarreras(i));
        }

        ex.shutdown();

        while(!ex.isShutdown());


        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < nParticipantes; j++)
            {
                System.out.print(" "+tiempos[i][j]);
            }
            System.out.println("\n");
        }
    }
}
