import multiprocessing

#tarea para el proceso 1; se escriben en el pipe numeros 
#del 1 al 10
def create_items(pipe):
    output_pipe, _ = pipe
    for item in range(10):
        output_pipe.send(item)
    output_pipe.close()

#tarea para el proceso 2; se leen los datos del pipe, 
#se elevan al cuadrado, y se vuelven a escribir
def multiply_items(pipe1, pipe2):
    close, input_pipe = pipe1
    close.close()
    output_pipe, _ = pipe2
    try:
        while True:
            item = input_pipe.recv()
            output_pipe.send(item*item)
    except EOFError:
        output_pipe.close()

if __name__ == '__main__':
    #creacion y ejecucion del proceso 1
    pipe1 = multiprocessing.Pipe(True)
    process_pipe1 = multiprocessing.Process(
        target=create_items, args=(pipe1,)
    )

    #creacion y ejecucion del proceso 2
    pipe2 = multiprocessing.Pipe(True)
    process_pipe2 = multiprocessing.Process(
        target=multiply_items, args=(pipe1, pipe2)
    )
    
    process_pipe1.start()
    process_pipe2.start()
    
    pipe1[0].close()
    pipe2[0].close()
    
    #el proceso principal imprime el contenido del pipe
    try:
        while True:
            print(pipe2[1].recv())
    except EOFError:
        print('Programa principal finalizando...')