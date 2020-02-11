package app_model;

public interface Visitor{
    public void visit(Block b);
    public void visit(Line l);
}
