import threading
import time

class myCounter:
	def __init__(self):
		self.val=0
	def inc(self):
		self.val+=1
	def value(self):
		return self.val

def myThread(ref, iters):
    for cont in range(iters):
        ref.inc()
    	
	
if __name__ == '__main__':
    myThreads=[]
    nproc=int(input('tasks?'))
    iters=int(input('iterations?'))
    cont=myCounter()
    for i in range(nproc):
        th= threading.Thread(target=myThread, args=(cont, iters))
        myThreads.append(th)
    for i in myThreads: i.start()
    for i in myThreads: i.join()
    print(cont.value())
    