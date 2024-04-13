package anillo;

public class EmptyNode extends ContentNode {
    
    public ContentNode next() {
        throw new RuntimeException();
    }
    public Object current() {
        throw new RuntimeException();
    }
    public ContentNode add(Object cargo) {
        return new BasicNode( cargo );
    }
    public ContentNode remove() {
        return this;
    }
}
