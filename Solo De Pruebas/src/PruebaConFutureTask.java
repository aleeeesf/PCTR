import java.util.Vector;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class PruebaConFutureTask implements Callable<Integer>{

	@Override
	public Integer call() {
		// TODO Auto-generated method stub
		int f = 0;
		for(int i = 0; i < 10; i++)
		{
			f++;
		}
		
		return f;
	}

	public static void main(String args[]) throws InterruptedException, ExecutionException
	{
		final int nThreads = 8;
		ExecutorService ex = Executors.newFixedThreadPool(nThreads);
				
		FutureTask<Integer> future = null;
		Vector<FutureTask<Integer>> v = new Vector();
		
		for(int i = 0; i < 200; i++)
		{
			future = new FutureTask(new PruebaConFutureTask());
			v.add(future);
			ex.execute(v.get(i));
		}
		
		
		ex.shutdown();
		
		while(!ex.isTerminated());
		
		int cont = 0;
		for(int i = 0; i < v.size(); i++)
		{
			future = v.get(i);
			cont += future.get();
		}
		System.out.println(cont);
	}
}
