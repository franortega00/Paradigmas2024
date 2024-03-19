import Container
import Route
import Stack
import Vessel

{- Probando modulo Route-}
ruta = newR ["MDQ", "Bahamas", "Kuwait"] 
rutaVacia = newR []
rutaEnteros = newR [1..3]


barco = newV 2 20 ruta 
container = newC "MDQ" 5
barcoCargado = loadV barco container
