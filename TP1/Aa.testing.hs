import Container
{-import Route
import Stack
import Vessel-}

{-
FUNCIONES A HACER:
    Stack --> newS, freeCellsS, stackS, netS, holdsS, popS
    Vessel --> newV, freeCellsV, loadV, unloadV, netV
FUNCIONES A PROBAR:    
    Container --> newC, destinationC, netC 
    Route --> newR
-}
cont = newC "MDQ" 5
destino = destinationC cont
peso = netC cont
testContainer = [destino == "MDQ", peso == 5]
{-barco = cargarV barco cont
otroBarco = cargarV barco cont
dobleBarco = cargarV otroBarco cont

test = [ netV otroBarco == 5,
         netV dobleBarco == 10 ]-}