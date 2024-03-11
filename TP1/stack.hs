module Stack ( Stack, newS, freeCellsS, stackS, netS, holdsS, popS )
 where

import Container
import Route

data Stack = Sta [ Container ] Int deriving (Eq, Show)

newS :: Int -> Stack                          -- construye una Pila con la capacidad indicada 
freeCellsS :: Stack -> Int                    -- responde la celdas disponibles en la pila
stackS :: Stack -> Container -> Stack         -- apila el contenedor indicado en la pila
netS :: Stack -> Int                          -- responde el peso neto de los contenedores en la pila
holdsS :: Stack -> Container -> Route -> Bool -- indica si la pila puede aceptar el contenedor considerando las ciudades en la ruta
popS :: Stack -> String -> Stack              -- quita del tope los contenedores con destino en la ciudad indicada
