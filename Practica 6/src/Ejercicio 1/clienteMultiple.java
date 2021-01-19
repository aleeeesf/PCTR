import java.net.*;
import java.util.concurrent.ThreadPoolExecutor;
import java.io.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class clienteMultiple extends Thread
{
	public void run()
	{
        int i = (int)(Math.random()*10);
        int puerto = 2001;
        try{        	
            System.out.println("Realizando conexion...");
            Socket cable = new Socket("localhost", 2001);
		//Socket cable = new Socket("192.168.1.136", 2001);
            System.out.println("Realizada conexion a "+cable);
            PrintWriter salida= new PrintWriter(
                                    new BufferedWriter(
                                        new OutputStreamWriter(
            cable.getOutputStream())));
            salida.println(i);
            salida.flush();
            System.out.println("Cerrando conexion...");
            cable.close();

            }//try
                catch (Exception e)
        {System.out.println("Error en sockets...");}
	}
    public static void main (String[] args)
    {
    	int nThreads = 10, solicitudes = 0;
        ThreadPoolExecutor ex = new ThreadPoolExecutor(nThreads,nThreads,0L,TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
        
	while(solicitudes < 1000)	//Para intentar tumbar el servidor
	{
	      	for(int i = 0; i < nThreads; i++)
        	{
        		ex.execute(new clienteMultiple());
        	} 
		solicitudes++;
	}

        while(!ex.isTerminated());
    }//main
	
}