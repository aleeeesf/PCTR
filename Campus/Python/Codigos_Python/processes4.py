import multiprocessing
import time

def myProcess(myQueue, iters):
	for i in range(iters):
		myQueue.put(myQueue.get())
    	
	
if __name__ == '__main__':
    myProcs=[]
    myQueue=multiprocessing.Queue()
    myQueue.put(0)
    nproc=int(input('tasks?'))
    iters=int(input('iterations?'))
    for i in range(nproc):
        proc= multiprocessing.Process(target=myProcess, args=(myQueue, iters))
        myProcs.append(proc)
    for i in myProcs: i.start()
    for i in myProcs: i.join()
    print(myQueue.get())
    