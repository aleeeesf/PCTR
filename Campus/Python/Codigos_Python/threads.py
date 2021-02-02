from threading import Thread
import time

class myThread(Thread):
	def init (self):
		Thread.init(self)
		self.niter=niter;
		
	def run(self):
		for cont in range(10):
		  print(cont)
		print(self.getName())
		print(self.isDaemon())
		print(self.isAlive())
		  
def main():
	hebra1=myThread()
	hebra2=myThread()
	print('abriendo co-rutina...')
	hebra1.start()
	hebra2.start()
	hebra1.join()
	hebra2.join()
	print('cerrando co-rutina...')
	
if __name__ == '__main__':
    main()
