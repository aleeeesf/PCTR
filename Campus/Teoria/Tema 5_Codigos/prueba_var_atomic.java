/**
 * @(#)prueba_var_atomic.java
 *
 *
 * @author Antonio Tomeu
 * @version 1.00 2011/5/2
 */


import java.util.concurrent.atomic.*;

class Hilo extends Thread
{
    AtomicInteger cont = null;

    public Hilo(AtomicInteger c) {this.cont = c;}
	public void run()
	{
		//se increment cont, retornando el valor
		int valor = this.cont.incrementAndGet();
		System.out.println(valor);
	}

}

public class prueba_var_atomic {

    public static void main(String[] args) {

        AtomicInteger cnt = new AtomicInteger(0);

        for(int i=0; i<100; i++)
         {
           Hilo h = new Hilo(cnt);
           h.start();
		}
	}
}