package app_model;

import app_model.lines.*;

public class MoveVisitor implements Visitor{

    private Iterable<DisplayObject> displayObjects;
    private boolean headSelected;

    private int dx;
    private int dy;

    public MoveVisitor(Iterable<DisplayObject> displayObjects, boolean headSelected, int dx, int dy){
        this.displayObjects = displayObjects;
        this.headSelected = headSelected;
        this.dx = dx;
        this.dy = dy;
    }

    public void visit(Block b){
        b.setLocation(b.getX() + dx, b.getY() +dy);
        Visitor v = new Visitor(){
            public void visit(Block b){}
            public void visit(DisplayText t){}
                public void visit(AggregationLine l){l.updatePosition();}
                public void visit(AssociationLine l){l.updatePosition();}
                public void visit(CompostionLine l){l.updatePosition();}
                public void visit(DependencyLine l){l.updatePosition();}
                public void visit(ImplementationLine l){l.updatePosition();}
                public void visit(InheritanceLine l){l.updatePosition();}
        };
        for(DisplayObject d: displayObjects){
            d.accept(v);
        }
    }
    //public void visit(Line l);
    public void visit(DisplayText t){
        t.setLocation(t.getX() + dx, t.getY() +dy);
    }

    public void visit(AggregationLine l){
        moveLine(l);
    }
    public void visit(AssociationLine l){
        moveLine(l);
    }
    public void visit(CompostionLine l){
        moveLine(l);
    }
    public void visit(DependencyLine l){
        moveLine(l);
    }
    public void visit(ImplementationLine l){
        moveLine(l);
    }
    public void visit(InheritanceLine l){
        moveLine(l);
    }

    private void moveLine(Line l){
        if(headSelected){
            l.setHead(l.getFirstX_Value() + dx, l.getFirstY_Value() + dy);
            l.connectHead(null);
        }else{
            l.setTail(l.getSecondX_Value() + dx, l.getSecondY_Value() + dy);
            l.connectTail(null);
        }
        //reatach to the blocks
        Visitor v = new Visitor(){
            public void visit(Block b){
                l.connectToBlock(b);
            }
            public void visit(DisplayText t){}
            public void visit(AggregationLine l){}
            public void visit(AssociationLine l){}
            public void visit(CompostionLine l){}
            public void visit(DependencyLine l){}
            public void visit(ImplementationLine l){}
            public void visit(InheritanceLine l){}
        };

        for(DisplayObject c: displayObjects){
            c.accept(v);
        }
    }
}
