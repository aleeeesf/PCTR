
class lectorEscritor
{
    static int nLectores = 0;
    static boolean escribiendo = false;

    public synchronized void iniciaLectura()
    {
        while(escribiendo)
        {
            try{
                wait();
            }catch(InterruptedException ex){}
        }

        System.out.println(Thread.currentThread().getId()+" está leyendo");
        nLectores++;

        notifyAll();
    }

    public synchronized void finLectura()
    {
        nLectores--;
        if(nLectores == 0) notifyAll();
    }

    public synchronized void iniciaEscritura()
    {
        while(nLectores > 0 || escribiendo)
        {
            try{
                wait();
            }catch(InterruptedException ex){}
        }

        System.out.println(Thread.currentThread().getId()+" está escribiendo");
        escribiendo = true;
    }

    public synchronized void finEscritura()
    {
        escribiendo = false;
        notifyAll();
    }
}