module Route ( Route, newR , inOrderR)
    where


-- importamos elemIndex que sera usado en inOrderR
import Data.List (elemIndex)

--creamos el tipo de dato Route
data Route = Rou [ String ] deriving (Eq, Show)

-- funcion que arma un tipo de dato Route a partir del formato del constructor
newR :: [ String ] -> Route                    -- construye una ruta segun una lista de ciudades
newR listaDestinos | length listaDestinos > 0 = Rou listaDestinos

indiceElemento :: Eq a => a -> [a] -> Maybe Int
indiceElemento elemento lista = elemIndex elemento lista

-- funcion que compara los index (si es que existen) de los strings que pasemos buscando dentro de la lista del tipo de dato Route
inOrderR :: Route -> String -> String -> Bool
inOrderR (Rou lista_ciudades) ciudad1 ciudad2 | index1 <= index2 = True
                                            | otherwise = False
    where
        index1 = indiceElemento ciudad1 lista_ciudades
        index2 = indiceElemento ciudad2 lista_ciudades
-- ojo! Cuando hacemos inOrderR de dos ciudades que no existen, arroja True
