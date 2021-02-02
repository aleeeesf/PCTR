import multiprocessing
import time

def myProcess(iter):
    global shared_cont
    for cont in range(iter):
        print('say hello...')
    	
	
if __name__ == '__main__':
    myProcs=[]
    nproc=int(input('tasks?'))
    iters=int(input('iterations?'))
    start=time.time()
    for i in range(nproc):
        proc= multiprocessing.Process(target=myProcess, args=(iters,))
        myProcs.append(proc)
    for i in myProcs: i.start()
    for i in myProcs: i.join()
    end=time.time()
    print('end of execution')