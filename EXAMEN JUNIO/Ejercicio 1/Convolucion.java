//Alejandro Serrano Fernández
//DNI: 20501318-S
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.Random;
import java.util.Arrays;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Convolucion implements Runnable {

    private int inicio, fin;
    private static final int nElementos = 1000000;
    private static Random rand = new Random();
    private static int r[] = new int[nElementos];
    private static int aux[] = new int[nElementos]; 
    

    public Convolucion(int inicio_, int fin_)
    {
        this.inicio = inicio_;
        this.fin = fin_;
    }

    public void run() 
    {
        int first, second, third;
        //first = i[i-2]
        //second = i[i-1]
        //third = i[i+1]
        aux = Arrays.copyOf(r,r.length);
        
        for(int i = inicio; i < fin; i++)
        {            
            if(i-2 < 0)
                first = 0;
            else
                first = aux[i-2];

            if(i-1 < 0)
                second = 0;
            else
                second = aux[i-1];

            if(i+1 >= nElementos)
                third = 0;
            else
                third = aux[i+1];

            r[i] = (first-second)*10+third;
        }
    }

    public static void convolucionSecuencial()
    {
        int first, second, third;
        //first = i[i-2]
        //second = i[i-1]
        //third = i[i+1]
        aux = Arrays.copyOf(r,r.length);
        
        for(int i = 0; i < nElementos; i++)
        {           
            //Comprobemos que no salimos de la frontera 
            if(i-2 < 0)
                first = 0;
            else
                first = aux[i-2];

            if(i-1 < 0)
                second = 0;
            else
                second = aux[i-1];

            if(i+1 >= nElementos)
                third = 0;
            else
                third = aux[i+1];

            r[i] = (first-second)*10+third;
        }
    }

    //Rellena el array aleatoriamente
    public static void randomArray()
    {
        for(int i = 0; i < nElementos; i++)
        {
            r[i] = rand.nextInt(5);
        }
    }

    public static void main(String[] args) {
        
        Scanner s = new Scanner(System.in);
        int opc;        
        System.out.println("Seleccione metodo: ");
        System.out.println("1. Secuencial ");
        System.out.println("2. Paralelo ");
        opc = s.nextInt();

        //Rellenamos el array
        randomArray();

        // Usuario elige version secuencial
        if(opc == 1)
        {
            long tiempoInicio = System.nanoTime(), tiempoFinal;            

            for(int nGen = 0; nGen < 200; nGen++)
            {
                convolucionSecuencial();
            }         
            
            tiempoFinal = System.nanoTime() - tiempoInicio;
            System.out.println("Tiempo empleado: "+tiempoFinal+" nanosegundos");
        }

        // Usuario elige version paralela
        else if(opc == 2)
        {
            int ini,fin_,nThreads;
            long tiempoInicioSecuencial, tiempoFinalSecuencial, tiempoInicio, tiempoFinal;
            ThreadPoolExecutor ex;

            //Tomamos tiempos en secuencial

            /*    SECUENCIAL   */            
            tiempoInicioSecuencial = System.nanoTime();
            for(int nGen = 0; nGen < 200; nGen++)
            {
                convolucionSecuencial();
            }  

            tiempoFinalSecuencial = System.nanoTime() - tiempoInicioSecuencial;


            //Tomamos tiempos en Paralelo

            /*      PARALELO    */
            System.out.println("Introduce hilos: ");
            nThreads = s.nextInt();

            tiempoInicio = System.nanoTime();

            //200 generaciones a calcular
            for(int nGen = 0; nGen < 200; nGen++)
            {
                ex = new ThreadPoolExecutor(nThreads,nThreads,0L,TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
                ini = 0;
                fin_ = nElementos/nThreads;

                for(int i = 0; i < nThreads; i++) 
                {
                    ex.execute(new Convolucion(ini,fin_));
                    ini = fin_;
                    if(i == nThreads-2)
                        fin_ = nElementos;
                    else 
                        fin_ += nElementos/nThreads;
                }
                
                ex.shutdown();		
                while(!ex.isTerminated());
            }

            tiempoFinal = System.nanoTime() - tiempoInicio;
            System.out.println("Tiempo en secuencial: "+tiempoFinalSecuencial+" nanosegundos");
            System.out.println("Tiempo en paralelo: "+tiempoFinal+" nanosegundos");
            System.out.println("SpeedUp: "+(double)tiempoFinalSecuencial/tiempoFinal);
        }

        else
        {
            System.out.println("Opcion incorrecta");
        }       
    }
}
