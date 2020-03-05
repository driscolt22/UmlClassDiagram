package app_model;

import app_model.lines.*;

public class SelectionVisitor implements Visitor{
    private DisplayObject currentlySelected;
    private boolean lineHeadSelected;

    private int x;
    private int y;

    public SelectionVisitor(int x, int y){
        currentlySelected = null;
        lineHeadSelected = true;
        this.x = x;
        this.y = y;
    }

    public DisplayObject getSelected(){
        return currentlySelected;
    }

    public boolean isHeadSelected(){
        return lineHeadSelected;
    }

    public void visit(Block b){
        if(x >= b.getX() && x <= b.getX() + b.getWidth()
            && y >= b.getY() && y <= b.getY() + b.getLength()){
            currentlySelected = b;
        }
    }
    //public void visit(Line l);
    public void visit(DisplayText t){
        if(x >= t.getX() && y >= t.getY() && x <= t.getX() + 50 && y <= t.getY() + 50){
            currentlySelected = t;
        }
    }
    public void visit(AggregationLine l){
        visitLine(l);
    }
    public void visit(AssociationLine l){
        visitLine(l);
    }
    public void visit(CompostionLine l){
        visitLine(l);
    }
    public void visit(DependencyLine l){
        visitLine(l);
    }
    public void visit(ImplementationLine l){
        visitLine(l);
    }
    public void visit(InheritanceLine l){
        visitLine(l);
    }

    private void visitLine(Line l){
        double y1 = (double)l.getFirstY_Value();
        double y2 = (double)l.getSecondY_Value();
        double x1 = (double)l.getFirstX_Value();
        double x2 = (double)l.getSecondX_Value();
        double d = Math.abs((y2-y1)*x -(x2-x1)*y+x2*y1-y2*x1)/
        Math.sqrt(Math.pow(y2-y1,2)+Math.pow(x2-x1,2));
        //System.out.println("Selected l, d =" + d);
        if(d <= 5 && x >= (int)Math.min(x1,x2) && x <= (int)Math.max(x1,x2) &&
                     y >= (int)Math.min(y1,y2) && y <= (int)Math.max(y1,y2)){
            lineHeadSelected = Math.hypot(x -l.getFirstX_Value(), y - l.getFirstY_Value()) <=
                      Math.hypot(x- l.getSecondX_Value(), y-l.getSecondY_Value());
            currentlySelected = l;
        }
    }
}
