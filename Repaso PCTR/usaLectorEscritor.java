import java.util.concurrent.*;



public class usaLectorEscritor {
    
    public static void main(String args[])
    {
        final int nThreads = 8;
        ExecutorService ex = Executors.newFixedThreadPool(nThreads);
        lectorEscritor e = new lectorEscritor();


        ex.execute(new Escritor(e));
        for(int i = 0; i < 8; i++)
        {
            ex.execute(new Lector(e));
        }



        ex.shutdown();

        while(!ex.isTerminated());
    }
}

class Lector implements Runnable
{
    lectorEscritor monitor;

    public Lector(lectorEscritor e)
    {
        this.monitor = e;
    }

    public void run()
    {
        while(true)
        {
            monitor.iniciaLectura();
            monitor.finLectura();
            
            try {
                Thread.currentThread().sleep(700);
            } catch (InterruptedException e) {}
        }
    }
}

class Escritor implements Runnable
{
    lectorEscritor monitor;

    public Escritor(lectorEscritor e)
    {
        this.monitor = e;
    }

    public void run()
    {
        while(true)
        {
            monitor.iniciaEscritura();
            monitor.finEscritura();
            
            try {
                Thread.currentThread().sleep(700);
            } catch (InterruptedException e) {}
        }
    }
}