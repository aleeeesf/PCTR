import java.util.concurrent.locks.*;

public class filoApiAN {

	private static CircularArray<Integer> fork = (CircularArray<Integer>) new CircularArray<Integer>(4);
	private static CircularArray<Boolean> OKtoEat = (CircularArray<Boolean>) new CircularArray<Boolean>(4);
	
	private static ReentrantLock r = new ReentrantLock();
	private static Condition[] notready = new Condition[4];
	
	public filoApiAN()
	{
		for(int i = 0; i < 4; i++)
		{
			fork.change(i, 2);
			notready[i] = r.newCondition();
			if(i % 2 == 0)
				OKtoEat.change(i, true);
				
			else
				OKtoEat.change(i, false);
		}
	}
	
	public synchronized void tomarPalillos(int id) throws InterruptedException
	{
		//System.out.println("hi");
		int aux;		

		if(fork.get(id) != 2)
		{
			while(!OKtoEat.get(id)) notready.wait();
		}


		aux = fork.get(id+1) - 1;
		fork.change(id+1, aux);
			
		aux = fork.get(id-1) - 1;
		fork.change(id-1, aux);

	
	}
	
	public synchronized void soltarPalillos(int id) throws InterruptedException
	{
		r.lock();
		int aux;
		aux = fork.get(id+1) + 1;
		fork.change(id+1, aux);
						
		aux = fork.get(id-1) + 1;
		fork.change(id-1, aux);
			
		if(fork.get(id+1) == 2) OKtoEat.change(id+1,true);
		if(fork.get(id-1) == 2) OKtoEat.change(id-1,true);
		
		
	for(int i = 0; i < 4; i++) notready[i].signalAll();
		r.unlock();
		
	}

}

class CircularArray<T>{
	
	private T[] v;
	private int elements;
	public CircularArray(int elements){
		this.v = (T[]) new Object[elements];
		this.elements = elements;
	}
	
	public T get(int i)
	{
		if(i >= elements)
			return v[0];
		
		else if(i == -1)
			return v[elements-1];
		
		else return v[i];
	}
	
	public void change(int i, T value)
	{
		if(i >= elements)
			v[0] = value;
		
		else if(i == -1)
			v[elements-1] = value;
		
		else v[i] = value;
	}
}