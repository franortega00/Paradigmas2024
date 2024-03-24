module Route ( Route, newR , inOrderR)
    where

import Data.List (elemIndex) -- importamos elemIndex que sera usado en inOrderR

data Route = Rou [ String ] deriving (Eq, Show) --creamos el tipo de dato Route

newR :: [ String ] -> Route   -- funcion que arma un tipo de dato Route a partir del formato del constructor (lista de ciudades)
newR listaDestinos | length listaDestinos > 0 = Rou listaDestinos

indiceElemento :: Eq a => a -> [a] -> Maybe Int
indiceElemento = elemIndex

inOrderR :: Route -> String -> String -> Bool -- funcion que compara los index (si es que existen) de los strings que pasemos buscando dentro de la lista del tipo de dato Route
inOrderR (Rou lista_ciudades) ciudad1 ciudad2 | (ciudad1 `notElem` lista_ciudades) && (ciudad2 `notElem` lista_ciudades) = False
                                              | index1 <= index2 = True --mayor estricto, A no esta antes que A
                                              | otherwise = False
    where
        index1 = indiceElemento ciudad1 lista_ciudades
        index2 = indiceElemento ciudad2 lista_ciudades
