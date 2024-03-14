module Stack ( Stack, newS, freeCellsS, stackS, netS , holdsS) 
--, holdsS, popS )
 where

import Container

import Route

data Stack = Sta [ Container ] Int deriving (Eq, Show)

newS :: Int -> Stack -- construye una Pila vacia con la capacidad indicada 
newS capacidad = Sta [] capacidad 

stackS :: Stack -> Container -> Stack         -- apila el contenedor indicado en la pila 
stackS (Sta contsS capacidadStack) contenedor = Sta ((++) contsS [contenedor]) capacidadStack

-- variacion de StackS :: que pasa si el stack ya esta full de peso?? MAX == 20 tons                             

freeCellsS :: Stack -> Int -- responde la celdas disponibles en la pila
freeCellsS (Sta contsS capacidadStack) = capacidadStack - length contsS

sumarInts :: [Container] -> Int -- función para sumar todos los Ints dentro de una colección de tipos Container
sumarInts = sum . map netC

netS :: Stack -> Int  -- responde el peso neto de los contenedores en la pila
netS (Sta contsS capacidadStack) = toneladas
    where
        toneladas = sumarInts contsS
                                
ultimoDestinoPila :: [Container] -> String -- función para informar el ultimo destino de una pila de Containers 
ultimoDestinoPila = last . map destinationC

holdsS :: Stack -> Container -> Route -> Bool -- indica si la pila puede aceptar el contenedor considerando las ciudades en la ruta
holdsS (Sta contsS capacidadStack) contenedor ruta | inOrderR ruta ultimo_destino destino_contenedor == True = True
                                                    | otherwise = False
    where
        ultimo_destino = ultimoDestinoPila contsS 
        destino_contenedor = destinationC contenedor


popS :: Stack -> String -> Stack -- quita del tope los contenedores con destino en la ciudad indicada
popS (Sta contsS capacidadStack) ciudad_actual | ultimoDestino == ciudad_actual = stack_nuevo 
                                                | otherwise = (Sta contsS capacidadStack)
    where
        ultimoDestino = ultimoDestinoPila contsS
        stack_nuevo = (Sta (init contsS) capacidadStack)


ruta = newR ["MDQ", "Bahamas", "Kuwait"] 

stack10 = newS 30
container1 = newC "MDQ" 5
container2 = newC "Bahamas" 5
container3 = newC "Kuwait" 5
stack20 = stackS stack10 container1 
stack30 = stackS stack20 container2
stack40 = stackS stack30 container3
pesototal = netS stack40
entracontainer = holdsS stack40 container1 ruta
entracontainer2 = holdsS stack40 container3 ruta

