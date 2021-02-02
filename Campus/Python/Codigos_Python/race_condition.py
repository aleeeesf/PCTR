from threading import Thread
import time

#shared_cont will be within a race condition
shared_cont = 0
niter       = 1000000

class myThread(Thread):
	
	def init (self):
		Thread.init(self)
		self.niter=niter;
		
	def run(self):
		#doing shared_cont visible for threads
		global shared_cont
		for cont in range(niter):
		  #the race condition...
		  shared_cont+=1
		  
def main():
	hebra1=myThread()
	hebra2=myThread()
	hebra1.start()
	hebra2.start()
	hebra1.join()
	hebra2.join()
	print(shared_cont)
	
if __name__ == '__main__':
    main()	
