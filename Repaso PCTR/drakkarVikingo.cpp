#include <iostream>
#include <mutex>
#include <thread>
#include <vector>
#include <condition_variable>

using namespace std;

static int marmita = 10;
mutex em;
condition_variable no_vacio;
condition_variable no_lleno;

void comer()
{
	unique_lock<mutex> lock_(em);
	
	while(marmita == 0)
		no_vacio.wait(lock_);
	
	this_thread::sleep_for(std::chrono::milliseconds(500));
	cout<<this_thread::get_id()<<"se ha comido una marmita"<<endl;
	
	marmita--;
	
	no_vacio.notify_all();
	no_lleno.notify_one();
	
	lock_.unlock();
}

void llenar()
{
	unique_lock<mutex> lock_(em);
	
	while(marmita > 0) no_lleno.wait(lock_);
	
	this_thread::sleep_for(std::chrono::milliseconds(500));
	cout<<this_thread::get_id()<<" --- Ha rellenado la marmita ---"<<endl;
	
	marmita = 10;
	
	no_vacio.notify_all();	
	lock_.unlock();	
}


void vikingo()
{
	while(true)
	{
		comer();
	}
}

void cocinero()
{
	while(true)
	{
		llenar();
	}
}


int main()
{
	vector<thread> hilos;

	for(int i = 0; i < 9; i++) hilos.push_back(thread(vikingo));
	hilos.push_back(thread(cocinero));
	
	for(auto& thread: hilos) thread.join();
	
	return 0;
}
