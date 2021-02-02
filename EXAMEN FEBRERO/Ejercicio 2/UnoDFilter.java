//Alejandro Serrano Fernandez
//20501318S

import java.util.Scanner;
import java.util.Random;
import java.util.concurrent.*;

class UnoDFilter implements Runnable
{
    public static int nElementos;
    public static int[] w;
    public static int[] x;
    public static int[] l = new int[3];
    private int inicio, fin;

    public UnoDFilter(int inicio, int fin)
    {
        this.inicio = inicio;
        this.fin = fin;
    }

    public void run()
    {

        for(int i = inicio; i < fin; i++)
        {

            if(i-3 > 0 && i+3 < nElementos)
                w[i] = (x[i-3]*l[0]) + (x[i]*l[1]) + (x[i+3]*l[2]);

            else{

                int minpos = i-3, maxpos = i+3;

                if(i-3 < 0)
                {
                    minpos = nElementos - (3-i);
                }

                if(i+3 >= nElementos)
                {
                    maxpos = (i+3) % nElementos;
                }

                w[i] = (x[minpos]*l[0]) + (x[i]*l[1]) + (x[maxpos]*l[2]);
            }

            if(w[i] > 4) w[i] = 4;
            if(w[i] < -4) w[i] = -4;
        }
    }

    public static boolean correcto()
    {
        boolean correcto = true;

        for(int elto: l)
        {
            if(elto < -1 || elto > 1) correcto = false;
        }

        return correcto;
    }

    public static void main(String args[])
    {
        final int nThreads = 8;
        int opc = 0, elementos = 0, ini = 0, fini;
        double tiempo;


        Scanner S = new Scanner(System.in);
        Random r = new Random();

        ThreadPoolExecutor ex = new ThreadPoolExecutor(nThreads, nThreads,0L,TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
        

        System.out.println("Introduzca el filtro: ");

        do
        {
            for(int i = 0; i < 3; i++)
            {
                System.out.println("Introduzca elemento "+i+": ");
                l[i] = S.nextInt();
            }

        }while(!correcto());


        System.out.println("Elija una opción: ");
        System.out.println("1. Modo de ejecución automática ");
        System.out.println("2. Modo de ejecución manual ");

        do
        {
            opc = S.nextInt();
        }while(opc < 1 || opc > 2);

        switch(opc)
        {
            case 1:
                System.out.println("Modo de ejecución automatica ");
                nElementos = 1000;
                w = new int[nElementos];
                x = new int[nElementos];



                for(int i = 0; i < nElementos; i++)
                {
                    x[i] = r.nextInt(5);
                }

            break;
            case 2:
                 int n, i = 0;

                 System.out.println("Modo de ejecución manual seleccionada, introd num elementos ");
                 nElementos = S.nextInt();

                 w = new int[nElementos];
                 x = new int[nElementos];

                 System.out.println("Modo de ejecución manual seleccionada, introd elementos ");
                 
                 do
                 {
                    System.out.println("introd elemento "+i);
                    n = S.nextInt();

                    if(n < -4 || n > 4 ) System.out.println("No valido");
                    else
                    {
                        x[i] = n;
                        i++;
                    }

                 }while(i < nElementos);

            break;
        }


        tiempo = System.nanoTime(); //Incio del tiempo
        fini = nElementos/nThreads;


        for(int i = 0; i < nThreads; i++)
        {
            ex.execute(new UnoDFilter(ini, fini));
            ini = fini;

            if(i == nThreads-2) fini = nElementos;
            else fini += (nElementos/nThreads);
        }

        ex.shutdown();

        while(!ex.isTerminated());


        if(opc == 1)
        {
            tiempo = (double)((System.nanoTime()-tiempo)/1000000000);
            System.out.println("El tiempo ha sido de: "+ tiempo);
        }

        else{
            System.out.println("Tamaño señal: "+nElementos);

            System.out.println("Señal Original");
            for(int i = 0; i < nElementos; i++)
            {
                System.out.println(x[i]);
            }

            System.out.println("Señal Convoluta");
            for(int i = 0; i < nElementos; i++)
            {
                System.out.println(w[i]);
            }
        }
    }
}