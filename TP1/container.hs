module Container ( Container, newC, destinationC, netC)
 where

data Container = Con String Int deriving (Eq, Show)-- (Eq, Show) para q use el igual y el show pre establecido        -- (todo desps del = == constructor)
                    -- String == su destino
newC :: String -> Int -> Container   -- construye un Contenedor dada una ciudad de destino y un peso en toneladas
newC destino peso = contenedor --COMO CONSTRUIR UN NUEVO CONTAINER
    where contenedor = Con destino peso
    
destinationC :: Container -> String  -- responde la ciuda destino del contenedor
destinationC (Con destino peso) = destino

netC :: Container -> Int   -- responde el peso en toneladas del contenedor
netC (Con destino peso) = peso


