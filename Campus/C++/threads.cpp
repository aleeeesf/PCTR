#include <thread>
#include <iostream>
#include <vector>
#include <chrono>
#include <mutex>

using namespace std;

static int cont;
static int cont2;
mutex cerrojo;
once_flag fg;

struct Variable
{
	int inc;
	void incrementar(){inc++;}
};

void run()
{
	std::cout<<"PRIMER METODO"<<std::endl;
}

void run2()
{
	this_thread::sleep_for(std::chrono::milliseconds(2000));
	int i = 0;
	while(i < 4)
	{
		cout<<"La bebesita bb lean: "<<i<<endl;
		i++;
	}
	
}

void run3()
{
	cerrojo.lock();
	for(int i = 0; i < 100000; i++)
	{
		cont++;
	}
	cerrojo.unlock();
}

void run4()
{
	cerrojo.lock();
	for(int i = 0; i < 100000; i++)
	{
		cont--;
	}
	cerrojo.unlock();
}

void run5()
{
	lock_guard<mutex> locker(cerrojo);
	for(int i = 0; i < 100000; i++)
	{
		cont2++;
	}
}

void run6()
{
	lock_guard<mutex> locker(cerrojo);
	for(int i = 0; i < 100000; i++)
	{
		cont2--;
	}
}

void run7()
{
	for(int i = 0; i < 5; i++)
	{
		call_once(fg, []()
		{
			cout<<"Solo se llama una vez"<<endl;
		});
	
		cout<<"Se llama mas"<<endl;
	}

}

int main()
{
	/* Creacion de hilos */
	thread x(run);	
	x.join();
	
	
	
	
	
	
	
	/* Con funcion lambda */
	thread y(thread([]()
	{
		cout<<"Con lambda"<<endl;	
	}
	));	
	y.join();
	
	
	
	
	
	
	/* Con vector */
	vector<thread> Threads;	
	for(int i = 0; i < 4; i++) Threads.push_back(thread(run));
	for(auto& thread : Threads) thread.join();
	
	
	
	
	
	
	
	
	
	
	/*Esperando un ratito*/
	cout<<"Esperamos un ratico:"<<endl;
	thread z(run2);
	z.join();	
	
	
	
	
	
	
	/*Variable compartida*/
	Variable v;
	v.inc = 0;
	vector<thread> hilos;
	for(int i = 0; i < 5; i++)
	{
		hilos.push_back(thread([&v](){
			v.incrementar();
		}		
		));
	}
	
	for(auto& thread: hilos) thread.join();
	
	cout<<"Tras incrementar tenemos que: "<<v.inc<<endl;
	
	
	





	/* Sinchronized con mutex */
	hilos.clear();
	for(int i = 0; i < 5; i++)
	{
		hilos.push_back(thread(run3));
	}
	
	for(int i = 0; i < 5; i++)
	{
		hilos.push_back(thread(run4));
	}
	for(auto& thread: hilos) thread.join();
	
	cout<<"Contenido de x tras utilizar mutex: "<<cont<<endl;
	
	
	
	
	
	
	
	
	/* Sinchronized con mutex y reentrancia */
	hilos.clear();
	for(int i = 0; i < 5; i++)
	{
		hilos.push_back(thread(run5));
	}
	
	for(int i = 0; i < 5; i++)
	{
		hilos.push_back(thread(run6));
	}
	for(auto& thread: hilos) thread.join();
	
	cout<<"Contenido de x tras utilizar mutex: "<<cont2<<endl;
	
	
	
	
	
	thread b(run7);
	b.join();
	
	
	return 0;
}

