#include <iostream>
#include <mutex>
#include <condition_variable>
#include <vector>
#include <thread>
#include <chrono>

using namespace std;

mutex em;
bool escribiendo = false;
condition_variable escritor, lector;
static int n = 0;

void iniciaLectura()
{
	unique_lock<mutex> lock_(em);
	while(escribiendo) lector.wait(lock_);
	
	cout<<this_thread::get_id()<<" esta leyendo"<<endl;
	n++;
	
	lector.notify_all();
}

void finLectura()
{
	unique_lock<mutex> lock_(em);
	n--;
	if(n == 0) escritor.notify_all();	

}

void iniciaEscritura()
{
	unique_lock<mutex> lock_(em);
	while(escribiendo || n > 0) escritor.wait(lock_);
	
	cout<<this_thread::get_id()<<" esta escribiendo"<<endl;
	escribiendo = true;
}

void finEscritura()
{
	unique_lock<mutex> lock_(em);
	
	escribiendo = false;
	lector.notify_all();	
	escritor.notify_all();
}

void lectores()
{
	while(true)
	{
		iniciaLectura();
		finLectura();
		this_thread::sleep_for(chrono::milliseconds(200));
	}
}

void escritores()
{
	while(true)
	{
		iniciaEscritura();
		finEscritura();
		this_thread::sleep_for(chrono::milliseconds(200));		
	}
}

int main()
{
	
	vector<thread> hilos;
	
	for(int i = 0; i < 8; i++)
	{
		hilos.push_back(thread(lectores));
	}
	
	hilos.push_back(thread(escritores));
	
	for(auto& thread: hilos) thread.join();	
	
	return 0;
}
