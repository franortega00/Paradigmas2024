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

{- VESSEL -}
-- newV , freeCellsV , loadV, unloadV, netV
rutaABC = newR ["Armenia", "Brunei", "Comoros"]
testRoute = [   inOrderR rutaABC "Armenia" "Brunei", -- = True
                not(inOrderR rutaABC "Comoros" "Brunei"), -- not(False)
                not(inOrderR rutaABC "Armenia" "Ruta inexistente") {-not(False)-},
                testF (newR [])
                ]

contA = newC "Armenia" 10
contB = newC "Brunei" 10
{-Pruebas de barco vacios o inconsistentes-}
barcoVacioTest = foldr (&&) True [ testF (newV 0 0 rutaABC), testF (newV 1 0 rutaABC), testF (newV (-1) 0 rutaABC)]
barco = newV 3 1 rutaABC --bahias, alturas
barcoA = loadV barco contA
barcoAB = loadV barcoA contB --peso y orden


