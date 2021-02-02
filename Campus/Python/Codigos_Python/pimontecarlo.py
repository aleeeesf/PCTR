import math
import random

dardos=int(input('cuantos dardos? '))
aciertos=0

for cont in range(dardos):
  cx=random.random()
  cy=random.random()
  if math.sqrt((cx**2)+(cy**2))<1:
    aciertos=aciertos+1
    
piaprox=4.0*(aciertos/dardos)
print(piaprox)
