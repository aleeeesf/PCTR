//Alejandro Serrano Fernandez
//20501318S

#include <iostream>
#include <queue>
#include <mutex>
#include <thread>
#include <vector>
#include <ctime>
#include <cstdlib>

using namespace std;


template <typename T>
struct ConcurrentQueue
{	
	queue<T> Cola;
	mutex mtx;	
	void push(const T& elto)
	{
		unique_lock<mutex> lck(mtx);
		cout<<"Insertando "<<elto<<endl;
		Cola.push(elto);
		lck.unlock();
	}
	
	void pop()
	{
		unique_lock<mutex> lck(mtx);
		
		if(!Cola.empty())
		{
			cout<<"Eliminando "<<Cola.front()<<endl;
			Cola.pop();
		}		

		lck.unlock();
	}
	
	const T& front()
	{
		unique_lock<mutex> lck(mtx);
		
		if(!Cola.empty())
		{
			return Cola.front();			
		}	

		lck.unlock();
	}
	
	void imprimirCola()
	{
		unique_lock<mutex> lck(mtx);
		
		queue<T> aux(Cola);
		
		cout<<"Elementos: "<<endl;
		while(!aux.empty())
		{
			cout<<aux.front()<<endl;
			aux.pop();
		}
		
		lck.unlock();		
	}
};


static ConcurrentQueue<int> v;

void insertar()
{
	srand(time(NULL));
	int rNumber;

	while(true)
	{
		rNumber = rand() % 20 + 1;
		v.push(rNumber);
		this_thread::sleep_for(std::chrono::milliseconds(500));
	}	

}

void eliminar()
{		
	while(true)
	{
		v.pop();
		this_thread::sleep_for(std::chrono::milliseconds(500));
	}
	
	
}

int main()
{	
	thread x(insertar);
	thread y(eliminar);
	
	x.join(); y.join();

	
	return 0;	
}
