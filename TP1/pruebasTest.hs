module PruebasTest (testF)
 where
import Container
import Route
import Stack
import Vessel
import Distribution.Simple.Test (test)
import Control.Exception
import System.IO.Unsafe
import Language.Haskell.TH.Ppr (bar)

{-Funcion de testear-}
testF :: Show a => a -> Bool
testF action = unsafePerformIO $ do
    result <- tryJust isException (evaluate action)
    return $ case result of
        Left _ -> True
        Right _ -> False
    where
        isException :: SomeException -> Maybe ()
        isException _ = Just ()

{- Routes -}
-- newR ✓, inOrderR ✓
rutaABC = newR ["Armenia", "Brunei", "Comoros"]
testRoute = [   inOrderR rutaABC "Armenia" "Brunei", -- = True
                not(inOrderR rutaABC "Comoros" "Brunei"), -- not(False)
                not(inOrderR rutaABC "Armenia" "Ruta inexistente") {-not(False)-},
                testF (newR []), -- prueba de ruta vacia [] (levanta error --> True)
                True ] -- todo deberia dar True

{- Containers -}
-- newC ✓, destinationC ✓, netC ✓
containerVacio = foldr (&&) True [testF (newC "Brunei" 0), testF (newC "Brunei" (-10))] --Pruebas de contenedores vacios o inconsistentes
contA = newC "Armenia" 10
contB = newC "Brunei" 10
destinoContA = destinationC contA
pesoContB = netC contB
testContainer = [
                destinoContA == "Armenia", 
                pesoContB == 10,
                containerVacio, -- levanta error --> True
                True ] -- todo deberia dar True

{- Stack -}
-- newS ✓, freeCellsS ✓, stackS ✓, netS ✓, holdsS ✓, popS ✓
stackVacio = foldr (&&) True [ testF (newS 0), testF (newS (-10))] --Pruebas de stacks vacios o inconsistentes
stack1 = newS 2
stack2 = newS 3
contC = newC "Comoros" 5 --contA = newC "Armenia" 10, contB = newC "Brunei" 10
stack1A = stackS stack1 contA
stack1AB = stackS stack1A contB
stackSobrepasado =  testF (stackS stack1AB contC) -- stack1 puede cargar solo 2 contenedores y le intento cargar un tercero (falla)
stack2A = stackS stack2 contA
stack2AB = stackS stack2A contB --estoy usando dos lugares, me queda uno
stack2ABC = stackS stack2AB contC --llené el barco (freeCellsS == 0)
stack2AA = stackS stack2A contA
testStack = [
            freeCellsS stack2AB == freeCellsS stack2ABC + 1,
            stackVacio,
            stackSobrepasado,
            --testF (stackS stack1 (newC "Brunei" 0)), --stack un container inexistente -> deberia levantar error
            not(holdsS stack2AB contC rutaABC), -- destino del contenedor a agregar es posterior al ultimo en la pila
            not(holdsS stack2AB contA rutaABC), -- se pasa de peso
            not(holdsS stack2ABC contC rutaABC), -- stack ya lleno
            holdsS stack2A contA rutaABC,
            netS stack2ABC ==  sum (map netC [contA, contB, contC]), --chequea netS con netC
            popS stack2ABC "Comoros" == stack2AB, --pop correcto
            popS stack2ABC "Brunei" == stack2ABC, --no se puede sacar xq el destino de arriba de todo es "Comoros"
            popS stack1A "ruta inexistente" == stack1A,
            testF(popS (newS 0) "ruta inexistente"), --popS sobre un stack vacio -> levanta error
            popS stack1 "Armenia" == stack1, --popS sobre stack vacío
            popS stack2AA "Armenia" == stack2, --popS recursivo
            True ]

{- Vessel -}
-- newV ✓, freeCellsV ✓, loadV ✓, unloadV , netV ✓
--Pruebas de barco vacios o inconsistentes
barcoVacioTest = foldr (&&) True [ testF (newV 0 0 rutaABC), testF (newV 1 0 rutaABC), testF (newV (-1) 0 rutaABC)]
barcoSobrepasadoTest = testF (loadV barcoBA contB)
testRutaVacia = testF (newV 3 3 (newR [])) --si la ruta falla no cree que barco
barco = newV 1 3 rutaABC --bahias, alturas
barcoB = loadV barco contB
barcoBA = loadV barcoB contA --peso y orden
barcoBB = loadV barcoB contB
testVessel = [
        barcoVacioTest,
        barcoSobrepasadoTest,
        --que no me deje CREAR barco con lista FALLIDA
        testF (loadV barcoB (newC "Brunei" 0)), --cargo un container fallido --> error
        freeCellsV barcoBA == 1,
        freeCellsV barco == 3,
        netV barcoBA == netC contB + netC contA,
        unloadV barcoBA "Armenia" == barcoB,
        unloadV barcoBB "Brunei" == barco, --descarga todos los de ese puerto
        unloadV barcoBB "Armenia" == barcoBB, --no descarga nada en ese puerto
        unloadV barco "Marte" == barco, --no saca nada de barco sin container (y no falla con ruta inexistente)
        True    ] -- todo deberia dar True

testModulos = [testRoute,testContainer,testStack,testVessel]
testFinalModulos = foldr (&&) True (concat testModulos)
