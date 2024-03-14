import Data.List (elemIndex)
--creamos el tipo de dato Route
data Route = Rou [ String ] deriving (Eq, Show)
data Container = Con String Int deriving (Eq, Show)
data Stack = Sta [ Container ] Int deriving (Eq, Show)

newR :: [ String ] -> Route                    -- construye una ruta segun una lista de ciudades
newR listaDestinos = Rou listaDestinos

inOrderR :: Route -> String -> String -> Bool
inOrderR (Rou strs) str1 str2 = case (elemIndex str1 strs, elemIndex str2 strs) of
    (Just index1, Just index2) -> index1 <= index2
    _                           -> False

destinationC :: Container -> String  -- responde la ciuda destino del contenedor
destinationC (Con destino num) = destino --(?)

ultimoDestinoPila :: [Container] -> String -- función para informar el ultimo destino de una pila de Containers 
ultimoDestinoPila = last . map destinationC

stackS :: Stack -> Container -> Stack         -- apila el contenedor indicado en la pila 
stackS (Sta contsS capacidadStack) contenedor = Sta ((++) contsS [contenedor]) capacidadStack

newC :: String -> Int -> Container   -- construye un Contenedor dada una ciudad de destino y un peso en toneladas
newC destino num = contenedor --COMO CONSTRUIR UN NUEVO CONTAINER
    where contenedor = Con destino num

netC :: Container -> Int   -- responde el peso en toneladas del contenedor
netC (Con destino num) = num

newS :: Int -> Stack -- construye una Pila vacia con la capacidad indicada 
newS capacidad = Sta [] capacidad 

sumarInts :: [Container] -> Int -- función para sumar todos los Ints dentro de una colección de tipos Container
sumarInts = sum . map netC

netS :: Stack -> Int  -- responde el peso neto de los contenedores en la pila
netS (Sta contsS capacidadStack) = toneladas
    where
        toneladas = sumarInts contsS

holdsS :: Stack -> Container -> Route -> Bool -- indica si la pila puede aceptar el contenedor considerando las ciudades en la ruta
holdsS (Sta contsS capacidadStack) contenedor ruta | inOrderR ruta ultimo_destino destino_contenedor == True = True
                                                    | otherwise = False
    where
        ultimo_destino = ultimoDestinoPila contsS 
        destino_contenedor = destinationC contenedor

















ruta = newR ["MDQ", "Bahamas", "Kuwait"] 

stack10 = newS 30
container1 = newC "MDQ" 5
container2 = newC "Bahamas" 5
container3 = newC "Kuwait" 5
stack20 = stackS stack10 container1 
stack30 = stackS stack20 container2
stack40 = stackS stack30 container3
pesototal = netS stack40
entracontainer = holdsS stack40 container1 ruta
entracontainer2 = holdsS stack40 container3 ruta

