package gui;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;

import app_model.Visitor;
import app_model.Line;
import app_model.Block;

public class Render implements Visitor{

    private Graphics g;

    private static int LINE_WIDTH = 1;

    public Render(Graphics g){
        this.g = g;
        setLineWidth(g);
    }

    public void updateGraphics(Graphics g){
        this.g = g;
        setLineWidth(g);
    }

    private void setLineWidth(Graphics g)
    {
        ((Graphics2D) g).setStroke(new BasicStroke(LINE_WIDTH));
    }

    public void visit(Block b){
        g.drawRect(b.getX(), b.getY(), b.getLength(), b.getWidth());
        // TODO show class info and stuff
    }

    public void visit(Line l){
        g.drawLine(l.getFirstX_Value(), l.getFirstY_Value(),
            l.getSecondX_Value(), l.getSecondY_Value());
        // TODO add arrowheads?
    }

}
