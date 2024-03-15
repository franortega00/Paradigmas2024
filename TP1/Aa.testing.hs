import Container
import Route
import Stack
import Vessel

{- Dudas!
-- variacion de StackS :: que pasa si el stack ya esta full de peso?? MAX == 20 tons               
--  ̶v̶a̶r̶i̶a̶c̶i̶o̶n̶ ̶d̶e̶ ̶p̶o̶p̶S̶ ̶:̶:̶ ̶q̶u̶e̶ ̶p̶a̶s̶a̶ ̶s̶i̶ ̶h̶a̶y̶ ̶m̶a̶s̶ ̶d̶e̶ ̶u̶n̶ ̶c̶o̶n̶t̶a̶i̶n̶e̶r̶ ̶u̶l̶t̶i̶m̶o̶s̶ ̶e̶n̶ ̶e̶s̶a̶ ̶p̶i̶l̶a̶ ̶c̶o̶n̶ ̶e̶s̶e̶ ̶d̶e̶s̶t̶i̶n̶o̶?̶
-- resolver holdS --AGREGAR SI ESTOY EN EL MEDIO DE LA RUTA (no solo acepta el ultimo)
-}

{- FUNCIONES A HACER:
    Stack --> newS ✓ , freeCellsS ✓ , stackS ✓ , netS ✓ , holdsS (corregir) , popS ✓
    Vessel --> newV ✓, freeCellsV ✓ , loadV ✓, unloadV, netV    
    Container --> newC ✓, destinationC ✓,  netC ✓
    Route --> newR ✓, inOrderR ✓
-}

{- Complejidad::
 Barco <- Stack | <- Container ✓ 
                | <- Ruta ✓
-}


{- MODULO CONTAINER -}
-- newC ✓, destinationC ✓, netC ✓
cont = newC "MDQ" 5
destino = destinationC cont
peso = netC cont
testContainer = [destino == "MDQ", peso == 5]


{- MODULO ROUTE -}
-- newR ✓, inOrderR ✓
ruta = newR ["MDQ", "Bahamas", "Kuwait"] 
ordendestinos0 = inOrderR ruta "MDQ" "Kuwait" -- = True
ordendestinos1 = inOrderR ruta "Kuwait" "MDQ" -- = False
ordendestinos2 = inOrderR ruta "Kuwait" "Ruta inexistente" -- = False
testRoute = [ordendestinos0 == True, ordendestinos1 == False, ordendestinos2 == False]

{- MODULO STACK -}
-- newS, freeCellsS, stackS, netS , holdsS , popS
stack1 = newS 7
stack10 = newS 30
container1 = newC "MDQ" 5
container2 = newC "Bahamas" 5
container3 = newC "Kuwait" 5
container4 = newC "Kuwait" 5

stack20 = stackS stack10 container1 
stack30 = stackS stack20 container2
stack40 = stackS stack30 container3
stack50 = stackS stack40 container4

pesototal = netS stack40

entracontainer = holdsS stack40 container1 ruta
entracontainer2 = holdsS stack40 container3 ruta

stack60 = popS stack50 "Destino inexistente"
stack70= popS stack50 "Kuwait"

barco1 = newV 6 20 ruta 
barco1Cargado = loadV barco1 container2
barco1Recargado = loadV barco1Cargado container1
barco1Requetecargado = loadV barco1Recargado container3
barco1Hipercargado = loadV barco1Requetecargado container4
-- Preguntaza, con que criterio el barco carga???? Como se optimiza?

celdasvaciasbarco1 = freeCellsV barco1
testStack = [pesototal == 15, netS stack50 == 20, freeCellsS stack1 == 7]





testModulos = [testContainer, testRoute, testStack]