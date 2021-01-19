#include <iostream>
#include <time.h>
#include <random>
#include <cstdlib>
#include <ctime>
#include <thread>

#define N 1000

using namespace std;

void begin();
void menu();
void rellenar();
int producto(int a[3][3], int b[3][3]);
void convolucion(int mascara[3][3]);
void copiar(int a[N][N], int b[N][N]);
void imprimir();


//private int[][] imagen = new int[N][N];
int imagen[N][N] = {{35,40,41,45,50},{40,40,42,46,52},{42,46,50,55,55},{48,52,56,58,60},{56,60,65,70,75}};
int procesada[N][N] = {{35,40,41,45,50},{40,40,42,46,52},{42,46,50,55,55},{48,52,56,58,60},{56,60,65,70,75}};
static int Sharpen[3][3] = {{1,-2,1}, {-2,5,-2}, {1,-2,1}};
static int Sobel[3][3] = {{-1, 0, 1}, {-2,0,2}, {-1,0,1}};
static int  Detectar[3][3] = {{0,1,0}, {1,-4,1}, {0,1,0}};
static int Realzar[3][3] = {{0,0,0}, {-1,1,0}, {0,0,0}};
static int Enfoque[3][3] = {{0, -1, 0}, {-1, 5, -1}, {0, -1, 0}};
static int apuntes[3][3] = {{-2,-1,0},{-1,1,1},{0,1,2}};


void begin()
{
    rellenar();
    menu();
}

void run(int inicio, int fin, int mascara[3][3])
{
    for(int i = inicio; i < fin; i++)
    {
        for(int j = 1; j < N - 1; j++)
        {
            int aux[3][3] = {{imagen[i-1][j-1], imagen[i-1][j], imagen[i-1][j+1]},
                    {imagen[i][j-1], imagen[i][j], imagen[i][j+1]},
                    {imagen[i+1][j-1], imagen[i+1][j], imagen[i+1][j+1]}};
            procesada[i][j] = producto(aux, mascara);
        }
    }
}

void menu()
{
    int seleccion;
    bool salir = false;

    //imprimir();
    do
    { 
        do{            
            cout<<"Elija opcion: "<<endl;
            cout<<"1. Enfocar"<<endl;
            cout<<"2. Realzar bordes"<<endl;
            cout<<"3. Detectar Bordes"<<endl;
            cout<<"4. Sobel"<<endl;
            cout<<"5. Sharpen"<<endl;
            cout<<"6. Salir"<<endl;

            cin>>seleccion;

        }while(seleccion > 6 || seleccion < 0);
        
        
        switch(seleccion)
        {
            
            case 1: convolucion(apuntes); break;

            case 2: convolucion(Realzar); break;

            case 3: convolucion(Detectar); break;

            case 4: convolucion(Sobel); break;

            case 5: convolucion(Sharpen); break;

            case 6:    salir = true; break;

            default:    salir = true; break;

        }
    }while(!salir);
}

void rellenar()
{
    int nRand;
    for(int i = 0; i < N; i++)
    {
        for(int j = 0; j < N; j++)
        {
            nRand = rand() % 255;
            imagen[i][j] = nRand;
            procesada[i][j] = nRand;
        }
    }
}

void convolucion(int mascara[3][3])
{
    int nThreads = 64, nMax = N-1;
    int inicio_ = 1, fin_ = N/nThreads;
    thread hilos[nThreads];
    
    chrono::time_point<chrono::system_clock> start,end;
	start=chrono::system_clock::now();

    for(int i = 0; i < nThreads; i++)
    {
    	//cout<<"inicio: "<<inicio_<<"fin: "<<fin_<<endl;
			hilos[i] = thread(run,inicio_, fin_, mascara);
			inicio_ = fin_;
            if(i == nThreads-2)
				fin_ = nMax;
			else 
				fin_ += N/nThreads;
    }
    
    
    for(int i = 0; i < nThreads; i++)
    {
    	hilos[i].join();
	}
	
	end=chrono::system_clock::now();
	chrono::duration<double> tiempo = end-start;
	cout<<"t= "<<tiempo.count()<<endl;
}

int producto(int a[3][3], int b[3][3])
{
    int resultado = 0;
    for(int i = 0; i < 3; i++)
    {
        for(int j = 0; j < 3; j++)
        {
            resultado = resultado + (a[i][j] * b[i][j]);
        }
    }        
    return resultado;
}

void imprimir()
{
    for(int i = 0; i < N; i++)
    {
        for(int j = 0; j < N; j++)
        {
            cout<<procesada[i][j]<<" ";
        }
        cout<<"\n"<<endl;
    }
}

void copiar(int a[N][N], int b[N][N])
{
    for(int i = 0; i < N; i++)
    {
        for(int j = 0; j < N; j++)
        {
            a[i][j] = b[i][j];
        }
    }
}


int main()
{
    srand(time(NULL));
    begin();
    return 0;
}
