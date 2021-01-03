#include <iostream>
#include <time.h>
#include <random>
#include <cstdlib>
#include <ctime>
#include <thread>
#include <ratio>
#include <chrono>

#define N 100

using namespace std;
using namespace std::chrono;

void begin();
void menu();
void rellenar();
int producto(int a[3][3], int b[3][3]);
void convolucion(int mascara[3][3]);
void copiar(int a[N][N], int b[N][N]);
void imprimir();


//private int[][] imagen = new int[N][N];
int imagen[N][N];
int procesada[N][N];
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

void menu()
{
    int seleccion;
    bool salir = false;

    imprimir();
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
            
            case 1: convolucion(Enfoque); imprimir(); break;

            case 2: convolucion(Realzar); imprimir(); break;

            case 3: convolucion(Detectar); imprimir(); break;

            case 4: convolucion(Sobel); imprimir();break;

            case 5: convolucion(Sharpen); imprimir(); break;

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
	high_resolution_clock::time_point t1 = high_resolution_clock::now();

    for(int i = 1; i < N - 1; i++)
    {
        for(int j = 1; j < N - 1; j++)
        {
            int aux[3][3] = {{imagen[i-1][j-1], imagen[i-1][j], imagen[i-1][j+1]},
                    {imagen[i][j-1], imagen[i][j], imagen[i][j+1]},
                    {imagen[i+1][j-1], imagen[i+1][j], imagen[i+1][j+1]}};
            procesada[i][j] = producto(aux, mascara);
        }
    }
  	
	high_resolution_clock::time_point t2 = high_resolution_clock::now();
  	duration<double> time_span = duration_cast<duration<double>>(t2 - t1);
	cout<<"t= "<<time_span.count()<<endl;
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
