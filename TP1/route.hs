module Route ( Route, newR , inOrderR )
    where


-- importamos elemIndex que sera usado en inOrderR
import Data.List (elemIndex)

--creamos el tipo de dato Route
data Route = Rou [ String ] deriving (Eq, Show)

-- funcion que arma un tipo de dato Route a partir del formato del constructor
newR :: [ String ] -> Route                    -- construye una ruta segun una lista de ciudades
newR listaDestinos = Rou listaDestinos

-- funcion que compara los index (si es que existen) de los strings que pasemos buscando dentro de la lista del tipo de dato Route
inOrderR :: Route -> String -> String -> Bool
inOrderR (Rou strs) str1 str2 = case (elemIndex str1 strs, elemIndex str2 strs) of
    (Just index1, Just index2) -> index1 <= index2
    _                           -> False
