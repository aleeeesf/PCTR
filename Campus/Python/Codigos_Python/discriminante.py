#esto es un comentario
#un modulo se importa asi:
import math

#el tipado de las variables es dinamico...
a=int(input('coeficiente cuadratico? '))
b=int(input('coeficiente lineal? '))
c=int(input('coeficiente libre? '))
discriminante=(b**2)-4*a*c
print('discriminante=', discriminante)
#la indentacion determina el ambito de las estructuras de control
if discriminante>0:
	raiz=math.sqrt(discriminante)
	print(raiz)
else:
	print('discriminante negativo...')