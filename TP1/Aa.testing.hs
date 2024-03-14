import Container
import Route
import Stack
import Vessel

{-
FUNCIONES A HACER:
    Stack --> newS ✓ , freeCellsS ✓ , stackS ✓ , netS ✓ , holdsS ✓ , popS
    Vessel --> newV, freeCellsV, loadV, unloadV, netV
FUNCIONES A PROBAR:    
    Container --> newC, destinationC, netC 
    Route --> newR
-}

{-
Complejidad::
 Barco <- Stack | <- Container ✓ 
                | <- Ruta ✓
-}

cont = newC "MDQ" 5
destino = destinationC cont
peso = netC cont
testContainer = [destino == "MDQ", peso == 5]

{- data Route = Rou [ String ] deriving (Eq, Show)

newR :: [ String ] -> Route                    -- construye una ruta segun una lista de ciudades
newR listaDestinos = Rou listaDestinos -}

ruta = newR ["MDQ", "Bahamas", "Kuwait"] 
ordendestinos0 = inOrderR ruta "MDQ" "Kuwait" -- = True
ordendestinos1 = inOrderR ruta "Kuwait" "MDQ" -- = False
ordendestinos2 = inOrderR ruta "Kuwait" "Ruta inexistente" -- = False


{- newS :: Int -> Stack                          -- construye una Pila con la capacidad indicada 
newS capacidad = Sta [] capacidad

freeCellsS :: Stack -> Int                    -- responde la celdas disponibles en la pila
freeCellsS contStack capacidadStack = capacidadStack - Length contStack
stackS :: Stack -> Container -> Stack         -- apila el contenedor indicado en la pila
stackS (Sta contsS capacidadStack) contenedor = Sta ((++) contsS [contenedor]) capacidadStack -}

stack1 = newS 7
testStack = [freeCellsS stack1 == 7]

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
{-barco = cargarV barco cont
otroBarco = cargarV barco cont
dobleBarco = cargarV otroBarco cont

test = [ netV otroBarco == 5,
         netV dobleBarco == 10 ]-}