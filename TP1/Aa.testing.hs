import Container
import Route
import Stack
import Vessel

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

{- data Route = Rou [ String ] deriving (Eq, Show)

newR :: [ String ] -> Route                    -- construye una ruta segun una lista de ciudades
newR listaDestinos = Rou listaDestinos -}

ruta = newR ["MDQ", "Bahamas", "Kuwait"]

{- newS :: Int -> Stack                          -- construye una Pila con la capacidad indicada 
newS capacidad = Sta [] capacidad

freeCellsS :: Stack -> Int                    -- responde la celdas disponibles en la pila
freeCellsS contStack capacidadStack = capacidadStack - Length contStack
stackS :: Stack -> Container -> Stack         -- apila el contenedor indicado en la pila
stackS (Sta contsS capacidadStack) contenedor = Sta ((++) contsS [contenedor]) capacidadStack -}

stack1 = newS 7
testStack = [freeCellsS stack1 == 7]


{-barco = cargarV barco cont
otroBarco = cargarV barco cont
dobleBarco = cargarV otroBarco cont

test = [ netV otroBarco == 5,
         netV dobleBarco == 10 ]-}