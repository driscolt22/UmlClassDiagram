package gui;
import java.util.*;

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
    private static int FONT_SIZE = 15;

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
        int x = b.getX();
        int y = b.getY();
        int width = b.getWidth();
        int height = b.getLength();
        g.drawRect(x, y, width, height);

        Font  f2  = new Font(Font.SANS_SERIF,  Font.PLAIN, FONT_SIZE);
        g.setFont(f2);
        g.drawString(b.getName(), x, y + FONT_SIZE);
        g.drawLine(x, y + FONT_SIZE, x+ width,y + FONT_SIZE);
        ArrayList<String> iVars= b.getInstanceVariables();
        ArrayList<String> methods = b.getMethods();
        for(int i = 0; i < iVars.size(); i++){
            g.drawString(iVars.get(i),x,y + FONT_SIZE*(i + 2));
        }
        g.drawLine(x, y + FONT_SIZE*(iVars.size() +1), x+ width,y + FONT_SIZE*(iVars.size() +1));
        for(int j = 0; j < methods.size(); j++){
            g.drawString(methods.get(j),x,y+ FONT_SIZE*(iVars.size() + 2 + j));
        }
        // TODO show class info and stuff
    }

    public void visit(Line l){
    int x1 = l.getFirstX_Value();
    int x2 = l.getSecondX_Value();
    int y1 = l.getFirstY_Value();
    int y2 = l.getSecondY_Value();
    int h = 10;
    int d = 20;

    int dx = x1 - x2, dy = y1 - y2;
    double D = Math.sqrt(dx*dx + dy*dy);
    double xm = D - d, xn = xm, ym = h, yn = -h, x;
    double sin = dy / D, cos = dx / D;

    x = xm*cos - ym*sin + x2;
    ym = xm*sin + ym*cos + y2;
    xm = x;

    x = xn*cos - yn*sin + x2;
    yn = xn*sin + yn*cos + y2;
    xn = x;

    int[] xpoints = {x1, (int) xm, (int) xn};
    int[] ypoints = {y1, (int) ym, (int) yn};

    g.drawLine((int)(xm + xn)/2,(int)(ym+yn)/2, x2, y2);
    g.drawPolygon(xpoints, ypoints, 3);
    }

}
