package anillo;

public abstract class ContentNode {
    abstract public ContentNode next();
    abstract public Object current();
    public abstract ContentNode previous();
    abstract public ContentNode add(Object cargo);
    abstract public ContentNode remove();
}
