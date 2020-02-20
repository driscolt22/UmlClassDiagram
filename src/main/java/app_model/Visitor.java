package app_model;

import app_model.lines.*;

public interface Visitor{
    public void visit(Block b);
    public void visit(Line l);
    public void visit(DisplayText t);
    public void visit(AggregationLine l);
    public void visit(AssociationLine l);
    public void visit(CompostionLine l);
    public void visit(DependencyLine l);
    public void visit(ImplementationLine l);
    public void visit(InheritanceLine l);
}
