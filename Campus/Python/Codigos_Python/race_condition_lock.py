#necessary packages are imported
from threading import Thread
from threading import Lock
import time

shared_cont = 0
lock        = Lock()

#code for threads...
def myThread(iter):
    global shared_cont
    for i in range(iter):
        #increment is done with locks...
        lock.acquire()
        shared_cont+=1
        lock.release()

if	__name__ == '__main__':        
    iter     = int(input('iterations?'))
    myThread1 = Thread(target=myThread, args=(iter,))
    myThread2 = Thread(target=myThread, args=(iter,))
    start=time.time()
    #now, the race condition...
    myThread1.start()        
    myThread2.start()        
    myThread1.join()
    myThread2.join()
    end=time.time()-start
    print('final value: ',shared_cont)
    print('seconds... : ',end)
    