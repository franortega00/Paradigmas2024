module Vessel (Vessel, newV , freeCellsV , loadV, unloadV, netV )
 where
import Container
import Stack
import Route

data Vessel = Ves [Stack] Route deriving (Eq, Show)

ruta = newR ["MDQ", "Bahamas", "Kuwait"] 
barco1 = newV 2 20 ruta 
container1 = newC "MDQ" 5

crearListaDePilas :: Int -> Int -> [Stack]
crearListaDePilas cantidad altura | cantidad > 1 = newS altura : crearListaDePilas (cantidad - 1) altura --constructor de listas / altura == capacidad, cantidad == cantidad bahias
                                  | otherwise = [newS altura]

-- "bahia = pila == stack"
newV :: Int -> Int -> Route -> Vessel  -- construye un barco según una cantida de bahias, la altura de las mismas y una ruta
newV cantidad_bahias altura_bahias ruta | (cantidad_bahias > 0) && (altura_bahias > 0) = Ves lista_bahias ruta
    where
        lista_bahias = crearListaDePilas cantidad_bahias altura_bahias 

celdasVaciasTotales :: [Stack] -> Int
celdasVaciasTotales lista | length lista > 1 = freeCellsS (head lista) + celdasVaciasTotales (tail lista) -- recursividad
                          | otherwise = freeCellsS (last lista)

freeCellsV :: Vessel -> Int  -- responde la celdas disponibles en el barco
freeCellsV (Ves lista ruta) = celdasVaciasTotales lista

loadV :: Vessel -> Container -> Vessel -- carga un contenedor en el barco
loadV (Ves lista ruta) contenedor | length posiblesPilas > 0 = Ves listaNueva ruta
                                  | otherwise = error "No se pudo cargar el contenedor en el barco"
    where
        posiblesPilas = [pila | pila <- lista , holdsS pila contenedor ruta] -- == True
        pilasNoDisponibles = [pila | pila <- lista , not (holdsS pila contenedor ruta)] -- == False
        listaNueva = tail posiblesPilas ++ pilasNoDisponibles ++ [stackS (head posiblesPilas) contenedor]

unloadV :: Vessel -> String -> Vessel  -- responde un barco al que se le han descargado los contenedores que podían descargarse en la ciudad
unloadV (Ves lista ruta) ciudad = Ves listaNueva ruta
    where listaNueva = [popS stack ciudad | stack <- lista] 

netV :: Vessel -> Int  -- responde el peso neto en toneladas de los contenedores en el barco
netV (Ves [] ruta) = 0
netV (Ves lista ruta) = sum [netS stack | stack <- lista] 

