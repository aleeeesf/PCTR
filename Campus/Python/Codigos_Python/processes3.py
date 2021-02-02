import multiprocessing
import time

class myCounter:
	def __init__(self):
		self.val=0
	def inc(self):
		self.val+=1
	def value(self):
		return self.val


def myProcess(cont, iters):
	for i in range(iters):
		cont.inc()
    	
	
if __name__ == '__main__':
    myProcs=[]
    cont=myCounter()
    nproc=int(input('tasks?'))
    iters=int(input('iterations?'))
    for i in range(nproc):
        proc= multiprocessing.Process(target=myProcess, args=(cont, iters))
        myProcs.append(proc)
    for i in myProcs: i.start()
    for i in myProcs: i.join()
    print(cont.value())
    