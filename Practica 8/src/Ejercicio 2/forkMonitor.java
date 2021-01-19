
public class forkMonitor {

	private static CircularArray<Integer> fork = (CircularArray<Integer>) new CircularArray<Integer>(4);
	private static CircularArray<Boolean> OKtoEat = (CircularArray<Boolean>) new CircularArray<Boolean>(4);
	
	public forkMonitor()
	{
		for(int i = 0; i < 4; i++)
		{
			fork.change(i, 2);
			if(i % 2 == 0)
				OKtoEat.change(i, true);
				
			else
				OKtoEat.change(i, false);
		}
	}
	
	public synchronized void tomarPalillos(int id) throws InterruptedException
	{
		int aux;
		
		if(fork.get(id) != 2)
		{
			while(!OKtoEat.get(id)) wait();
		}
		
		synchronized(this)
		{
			aux = fork.get(id+1) - 1;
			fork.change(id+1, aux);
			
			aux = fork.get(id-1) - 1;
			fork.change(id-1, aux);
		}		
	}
	
	public synchronized void soltarPalillos(int id)
	{
		int aux;
		
		synchronized(this)
		{
			aux = fork.get(id+1) + 1;
			fork.change(id+1, aux);
						
			aux = fork.get(id-1) + 1;
			fork.change(id-1, aux);
			
			if(fork.get(id+1) == 2) OKtoEat.change(id+1,true);
			if(fork.get(id-1) == 2) OKtoEat.change(id-1,true);
		}
		
		notifyAll();
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