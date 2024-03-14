module Vessel --( Vessel, newV, freeCellsV, loadV, unloadV, netV )
 where

import Container
import Stack
import Route

data Vessel = Ves [ Stack ] Route deriving (Eq, Show)

ruta = newR ["MDQ", "Bahamas", "Kuwait"] 

crearListaDePilas :: Int -> Int -> [Stack]
crearListaDePilas cantidad altura | cantidad > 1 = lista_stacks
                                    | otherwise = [newS altura]
    where
        stack_nuevo = newS altura
        lista_stacks = (++) [stack_nuevo] (crearListaDePilas (cantidad-1) altura)


newV :: Int -> Int -> Route -> Vessel  -- construye un barco según una cnatida de bahias, la altura de las mismas y una ruta
newV cantidad_pilas altura_pilas ruta = Ves lista_pilas ruta
    where
        lista_pilas = crearListaDePilas cantidad_pilas altura_pilas

barco1 = newV 6 20 ruta 

--freeCellsV :: Vessel -> Int            -- responde la celdas disponibles en el barco
--loadV :: Vessel -> Container -> Vessel -- carga un contenedor en el barco
--unloadV :: Vessel -> String -> Vessel  -- responde un barco al que se le han descargado los contenedores que podían descargarse en la ciudad
--netV :: Vessel -> Int                  -- responde el peso neto en toneladas de los contenedores en el barco