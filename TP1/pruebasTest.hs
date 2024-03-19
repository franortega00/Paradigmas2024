import Container
import Route
import Stack
import Vessel

{- VESSEL -}
-- newV , freeCellsV , loadV, unloadV, netV
rutaABC = newR ["Armenia", "Brunei", "Comoros"]
testRoute = [   inOrderR rutaABC "Armenia" "Brunei", -- = True
                not(inOrderR rutaABC "Comoros" "Brunei"), -- not(False)
                not(inOrderR rutaABC "Armenia" "Ruta inexistente") {-not(False)-}]

contA = newC "Armenia" 10
contB = newC "Brunei" 10
barco = newV 3 1 rutaABC --bahias, alturas
barcoA = loadV barco contA
barcoAB = loadV barcoA contB --peso y orden


