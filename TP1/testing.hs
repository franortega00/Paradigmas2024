import Container
import Route
import Stack
import Vessel

import Control.Exception
import System.IO.Unsafe
import Language.Haskell.TH.Ppr (bar)

testF :: Show a => a -> Bool
testF action = unsafePerformIO $ do
    result <- tryJust isException (evaluate action)
    return $ case result of
        Left _ -> True
        Right _ -> False
    where
        isException :: SomeException -> Maybe ()
        isException _ = Just ()

mdq = "MDQ"
rio = "Rio de Janeiro"
tok = "Tokio"

{- Probando modulos-}
ruta = newR [mdq,rio,tok] 
rutaVacia = newR [] 

barcoChato = newV 1 1 ruta -- tiene 1 stack de altura 1
barcoVacio = newV 0 0 ruta
barcoVacio2 = newV 0 1 ruta
barcoVacio1 = newV 1 0 ruta



test = [(barcoChato == barcoVacio `==` barcoVacio2 `==` barcoVacio1),
        --,
        --    ,
        --    ,
        --    ,
        --    ,
        --    ,
        --    ,
        --    ,
        --    ,
        --    ,
        --    ,
        --    ,
        True]

testing = foldr (&&) True test






