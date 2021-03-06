package app_model.lines;

import app_model.Line;
import app_model.Block;
import app_model.Visitor;

public class AggregationLine extends Line{
    public AggregationLine(int x1, int y1, int x2, int y2){
        super(x1, y1, x2, y2);
    }

    public AggregationLine(Block head, Block tail){
        super(head, tail);
    }

    @Override
    public void accept(Visitor v){
        v.visit(this);
    }
}
