import java.util.concurrent.*;
import java.util.ArrayList;

public class productoSumaFilas implements Callable<Integer>{

    private int fila;
    public static final int nColumnas = 100, nFilas = 10;

    public static int[][] M = new int[nFilas][nColumnas];

    public productoSumaFilas(int fila)
    {
        this.fila = fila;
    }

    public static void rellenarMatriz()
    {
        for(int i = 0; i < nFilas; i++)
        {
            for(int j = 0; j < nColumnas; j++)
            {
                M[i][j] = 1;
            }
        }
    }

    public Integer call() throws Exception
    {
        int suma = 0;
        for(int j = 0; j < nColumnas; j++) suma += M[fila][j];
        return suma;
    }

    public static void main(String args[])
    {
        final int nThreads = 10;
        int total = 1;
        productoSumaFilas.rellenarMatriz();
        ExecutorService ex = Executors.newFixedThreadPool(nThreads);

        ArrayList<Future<Integer>> tareas = new ArrayList<Future<Integer>>();

        for(int i = 0; i < nFilas; i++)
        {
            tareas.add(ex.submit(new productoSumaFilas(i)));
        }

        for(Future<Integer> task: tareas) 
        {
            try {
                total *= task.get();
            } catch (Exception e) {}
        }

        ex.shutdown();

        while(!ex.isTerminated());
        System.out.println("total: "+total);
    }
    
}
