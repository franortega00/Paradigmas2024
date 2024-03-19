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


-- creaciones inconsistentes : Raise Error
-- acciones no permitidas : No se hacen


{- Routes -}
-- newV , freeCellsV , loadV, unloadV, netV
rutaABC = newR ["Armenia", "Brunei", "Comoros"]
testRoute = [   inOrderR rutaABC "Armenia" "Brunei", -- = True
                not(inOrderR rutaABC "Comoros" "Brunei"), -- not(False)
                not(inOrderR rutaABC "Armenia" "Ruta inexistente") {-not(False)-},
                testF (newR []) -- prueba de ruta vacia []
                ]

{- Containers -}

{-Pruebas de contenedores vacios o inconsistentes-}
testContainer = foldr (&&) True [ testF (newC "Brunei" 0), testF (newC "Brunei" (-10))]
contA = newC "Armenia" 10
contB = newC "Brunei" 10

{- Stack -}
{-Pruebas de contenedores vacios o inconsistentes-}
testStack = foldr (&&) True [ testF (newS 0), testF (newS (-10))]

{- Vessel -}

{-Pruebas de barco vacios o inconsistentes-}
barcoVacioTest = foldr (&&) True [ testF (newV 0 0 rutaABC), testF (newV 1 0 rutaABC), testF (newV (-1) 0 rutaABC)]
barco = newV 1 3 rutaABC --bahias, alturas
barcoB = loadV barco contB
barcoBA = loadV barcoB contA --peso y orden
barcoBAB = loadV barcoBA contB

testVessel = [
        barcoBA == barcoBAB , --como ya se excedio el limite de altura, mantiene igual
        barcoVacioTest
            ]

