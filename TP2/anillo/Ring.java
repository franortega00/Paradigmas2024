package anillo;

public class Ring {

    public ContentNode nodo = new EmptyNode();
    
    public Ring next() {
        nodo = nodo.next();
        return this;
    }
    public Object current() {
        return nodo.current();
    }
    public Ring add(Object cargo) {
        nodo = nodo.add(cargo) ;
        return this;
    }
    public Ring remove() {
        nodo = nodo.remove();
        return this;
    }
}

