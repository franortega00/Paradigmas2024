package anillo;

public class BasicNode extends ContentNode {
    private ContentNode next;
    private final ContentNode prev;
    private final Object element;
    private ContentNode source;

    public BasicNode(Object cargo) {
        this.element = cargo;
        this.prev = this;
        this.next = this;
        this.source = new EmptyNode();
    }
    public BasicNode(ContentNode node, Object cargo) {
        this.prev = node;
        this.next = node;
        this.element = cargo;
        this.source = node;
    }
    public ContentNode next() {
        return next;
    }
    public Object current() { 
        return element; 
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
