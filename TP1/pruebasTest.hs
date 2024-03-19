import Container
import Route
import Stack
import Vessel


-- creaciones inconsistentes : Raise Error
-- acciones no permitidas : No se hacen


{- VESSEL -}
-- newV , freeCellsV , loadV, unloadV, netV
rutaABC = newR ["Armenia", "Brunei", "Comoros"]
testRoute = [   inOrderR rutaABC "Armenia" "Brunei", -- = True
                not(inOrderR rutaABC "Comoros" "Brunei"), -- not(False)
                not(inOrderR rutaABC "Armenia" "Ruta inexistente") {-not(False)-}]

contA = newC "Armenia" 1
contB = newC "Brunei" 3
barco = newV 1 2 rutaABC --bahias, alturas
barcoB = loadV barco contB
barcoBA = loadV barcoB contA --peso y orden
barcoBAB = loadV barcoBA contB

testVessel = [
        barcoBA == barcoBAB --como ya se excedio el limite de altura, mantiene igual
            ]

