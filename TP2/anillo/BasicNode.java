package anillo;

public class BasicNode extends ContentNode {
    private ContentNode next;
    private final ContentNode prev;
    private final Object element;
    private ContentNode source;

    public BasicNode(Object cargo) {
        element = cargo;
        
        prev = this;
        next = this;
        source = new EmptyNode();
    }
    public BasicNode(ContentNode node, Object cargo) {
        prev = node.previous();
        next = node;
        element = cargo;

        source = node;
    }
    public ContentNode next() {
        return next;
    }
    public Object current() { 
        return element; 
    }
    public ContentNode previous() {
        return prev;
    }
    public ContentNode add(Object cargo){
        BasicNode nuevoNode = new BasicNode(this, cargo);
        ((BasicNode) this.next).next = nuevoNode;
        ((BasicNode) this.prev).source = nuevoNode;
        return nuevoNode;
    }
    public ContentNode remove(){
        ((BasicNode)next).next = next;
        return this.source;
    }
}
