#looking for primes with parallel threads and data partition
import math
import threading
import concurrent.futures
import time

shared_cont = 0
mylock      = threading.Lock()

def isPrime(n):
	if(n<=1): return False
	for i in range(2, int(math.sqrt(n)+1)):
		if(n%i==0): 
			return False
			break
	return True	

def codeForThread(linf, lsup):
	global shared_cont
	local_cont = 0
	for cont in range(linf, lsup):
		if isPrime(cont):
			local_cont+=1
	mylock.acquire()	
	shared_cont+=local_cont
	mylock.release()
	
if __name__ == '__main__':
	niter    = int(input('range?'))
	nthreads = int(input('threads?'))
	linf     = int(0)
	#now, the data partition...
	twindow  = int(niter/nthreads)
	lsup     = twindow
	start    = time.time()
	#creatin the thread pool...
	with concurrent.futures.ThreadPoolExecutor(max_workers=nthreads) as myExec:
		for i in range(nthreads):
			myExec.submit(codeForThread, linf, lsup)
			linf = lsup
			lsup = lsup+twindow

	print(shared_cont)
	print(time.time()-start)



