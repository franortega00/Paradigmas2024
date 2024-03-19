module Stack ( Stack, newS, freeCellsS, stackS, netS , holdsS , popS )
 where

import Container

import Route

data Stack = Sta [ Container ] Int deriving (Eq, Show)

newS :: Int -> Stack -- construye una Pila vacia con la capacidad indicada 
newS capacidad = Sta [] capacidad -- capacidad = celdas disponibles

stackS :: Stack -> Container -> Stack         -- apila el contenedor indicado en la pila 
stackS (Sta contsS capacidadStack) contenedor | capacidadStack == length contsS = Sta contsS capacidadStack --si ya esta a su tope, no lo agrega (freeCells == 0)
                                              | otherwise = Sta ((++) contsS [contenedor]) capacidadStack
                            
freeCellsS :: Stack -> Int -- responde la celdas disponibles en la pila
freeCellsS (Sta contsS capacidadStack) = capacidadStack - length contsS

sumarInts :: [Container] -> Int -- función para sumar todos los Ints (pesos) dentro de una colección de tipos Container
sumarInts = sum . map netC
--otra definicion de sumarInts
{-sumarInts2 :: [Container] -> Int -- función para sumar todos los Ints dentro de una colección de tipos Container
lista_nums contenedores = [pesos | Con nombre pesos <- contenedores]
sumarInts2 = sum lista_nums-}

netS :: Stack -> Int  -- responde el peso neto de los contenedores en la pila
netS (Sta contsS capacidadStack) = toneladas
    where toneladas = sumarInts contsS
                                
ultimoDestinoPila :: [Container] -> String -- función para informar el ultimo destino de una pila de Containers 
ultimoDestinoPila = last . map destinationC

holdsS :: Stack -> Container -> Route -> Bool -- indica si la pila puede aceptar el contenedor considerando las ciudades en la ruta
holdsS (Sta contsS capacidadStack) contenedor ruta  | freeCellsS (Sta contsS capacidadStack) == 0 = False
                                                    | length contsS > 0 && inOrderR ruta ultimo_destino destino_contenedor = False
                                                    | netS (Sta contsS capacidadStack) + netC contenedor > 20 = False
                                                    | otherwise = True 
    where
        ultimo_destino = ultimoDestinoPila contsS 
        destino_contenedor = destinationC contenedor

popS :: Stack -> String -> Stack -- quita del tope los contenedores con destino en la ciudad indicada - funcion recursiva (en caso de haber mas de dos ultimos contenedores con el destino deseado)
popS (Sta contsS capacidadStack) ciudad_actual  | ultimoDestino == ciudad_actual = popS stack_nuevo ciudad_actual
                                                | otherwise = Sta contsS capacidadStack
    where
        ultimoDestino = ultimoDestinoPila contsS
        stack_nuevo = Sta (init contsS) capacidadStack



