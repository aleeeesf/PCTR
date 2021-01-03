import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.*;

public class conVolParalelo implements Runnable{
    
    private static int N = 5;

    private static int[][] imagen = new int[N][N];
    private static int[][] procesada = new int[N][N];
    private Scanner s = new Scanner(System.in);
    private static int[][] Sharpen = new int[][]{{1,-2,1}, {-2,5,-2}, {1,-2,1}};
    private static int[][] Sobel = new int[][]{{-1, 0, 1}, {-2,0,2}, {-1,0,1}};
    private static int[][] Detectar = new int[][]{{0,1,0}, {1,-4,1}, {0,1,0}};
    private static int[][] Realzar = new int[][]{{0,0,0}, {-1,1,0}, {0,0,0}};
    private static int[][] Enfoque = new int[][]{{0, -1, 0}, {-1, 5, -1}, {0, -1, 0}};
    private int inicio, fin;
    private int[][] mascara = new int[3][3];
    int[][] apuntes = new int[][]{{-2,-1,0},{-1,1,1},{0,1,2}};
    
    public conVolParalelo(){}

    public conVolParalelo(int ini, int fin, int[][] mascara)
    {
        this.inicio = ini;
        this.fin = fin;
        this.mascara = mascara;
    }
    
    public void begin()
    {
        rellenar();
        menu();
    }

    public void run()
    {
        int[][] aux;

        for(int i = inicio; i < fin; i++)
        {
            for(int j = 1; j < N - 1; j++)
            {
                aux = new int[][]{{imagen[i-1][j-1], imagen[i-1][j], imagen[i-1][j+1]},
                        {imagen[i][j-1], imagen[i][j], imagen[i][j+1]},
                        {imagen[i+1][j-1], imagen[i+1][j], imagen[i+1][j+1]}};
                procesada[i][j] = producto(aux, mascara);
            }
        }   

    }
    public void menu()
    {
        int seleccion;
        boolean salir = false;

        imprimir();
        do
        { 
            do{            
                System.out.println("Elija opcion: ");
                System.out.println("1. Enfoque");
                System.out.println("2. Realzar bordes");
                System.out.println("3. Detectar Bordes");
                System.out.println("4. Sobel");
                System.out.println("5. Sharpen");
                System.out.println("6. Salir");
    
                seleccion = s.nextInt();
    
            }while(seleccion > 6 || seleccion < 0);            
            
            switch(seleccion)
            {
                
                case 1: convolucion(Enfoque); imprimir(); break;
    
                case 2: convolucion(Realzar); imprimir(); break;
    
                case 3: convolucion(Detectar); imprimir(); break;
    
                case 4: convolucion(Sobel); imprimir();break;
    
                case 5: convolucion(Sharpen); imprimir(); break;
    
                case 6:    salir = true; break;

                default:    salir = true; break;
    
            }
        }while(!salir);
    }

    public void rellenar()
    {
        Random r = new Random();
        int nRand;

        for(int i = 0; i < N; i++)
        {
            for(int j = 0; j < N; j++)
            {
                nRand = r.nextInt(255);
                imagen[i][j] = nRand;
                procesada[i][j] = nRand;
            }
        }
    }

    public void convolucion(int[][] mascara)
    {
        int nThreads = 4, nMax = N-1;
        int inicio_ = 1, fin_ = N/nThreads;
        ThreadPoolExecutor ex = new ThreadPoolExecutor(nThreads,nThreads,0L,TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
        long tiempo = System.nanoTime();


        for(int i = 0; i < nThreads; i++)
        {
            System.out.println("inicio: "+inicio_+"fin: "+fin_);
			conVolParalelo n = new conVolParalelo(inicio_, fin_, mascara);
			ex.submit(n);
			inicio_ = fin_;
			
			if(i == nThreads-2)
				fin_ = nMax;
			else 
				fin_ += N/nThreads;
        }
        
        ex.shutdown();		
        while(!ex.isTerminated());
        
        tiempo=System.nanoTime()-tiempo;
		System.out.println("t:"+tiempo/1e09);
    }

    private int producto(int[][] a, int[][] b)
    {
        int resultado = 0;
        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                resultado = resultado + (a[i][j] * b[i][j]);
            }
        }        
        return resultado;
    }

    public void imprimir()
    {
        for(int i = 0; i < N; i++)
        {
            for(int j = 0; j < N; j++)
            {
                System.out.print(procesada[i][j]+" ");
            }
            System.out.println("\n");
        }
    }

    private void copiar(int[][] a, int[][] b)
    {
        for(int i = 0; i < N; i++)
        {
            for(int j = 0; j < N; j++)
            {
                a[i][j] = b[i][j];
            }
        }
    }

    public static void main(String[] args)
    {
        conVolParalelo s = new conVolParalelo();
        s.begin();
    }
}
