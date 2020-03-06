package gui;
import java.util.*;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Stroke;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;

import app_model.Visitor;
import app_model.Line;
import app_model.Block;
import app_model.DisplayText;
import app_model.lines.*;
import java.awt.Color;

public class Render implements Visitor{

    private Graphics g;

    private static int LINE_WIDTH = 2;
    private static int BLOCK_LINE_WIDTH = 1;
    private static int FONT_SIZE = 15;
    private static int h = 10;
    private static int d = 20;

    /**
     * @param g Creates a render for the display given the graphics
     */
    public Render(){
        this.g = null;
    }

    public void setGraphics(Graphics g){
        this.g = g;
    }

    public void paintBackground(int h, int w){
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.white);
        g2.fillRect(0, 0, w, h);
    }

    public void setSelectedColor(boolean selected){
        if(selected){
            g.setColor(Color.blue);
        }else{
            g.setColor(Color.black);
        }
    }

    public void updateGraphics(Graphics g){
        this.g = g;
    }

    private void setLineWidth(int l)
    {
        ((Graphics2D) this.g).setStroke(new BasicStroke(l));
    }

    /**
     * @param b Renders the given block on the display
     */
    public void visit(Block b){
        int x = b.getX();
        int y = b.getY();
        int width = 100;
        int height = 100;

        setLineWidth(BLOCK_LINE_WIDTH);

        Font  f2  = new Font(Font.SANS_SERIF,  Font.PLAIN, FONT_SIZE);
        //int textwidth = (int)(f2.getStringBounds(text, frc).getWidth());
        //int textheight = (int)(f2.getStringBounds(text, frc).getHeight());
        //
        AffineTransform affinetransform = new AffineTransform();
        FontRenderContext frc = new FontRenderContext(affinetransform,true,true);

        g.setFont(f2);
        g.drawString(b.getName(), x+2, y + FONT_SIZE);
        width = Math.max((int)(f2.getStringBounds(b.getName(), frc).getWidth()),width);

        ArrayList<String> iVars= b.getInstanceVariables();
        ArrayList<String> methods = b.getMethods();
        for(int i = 0; i < iVars.size(); i++){
            g.drawString(iVars.get(i),x+2,y + FONT_SIZE*(i + 2));
            width = Math.max((int)(f2.getStringBounds(iVars.get(i), frc).getWidth()),width);
        }

        for(int j = 0; j < methods.size(); j++){
            g.drawString(methods.get(j),x+2,y+ FONT_SIZE*(iVars.size() + 2        + j));
            width = Math.max((int)(f2.getStringBounds(methods.get(j), frc).getWidth()),width);        }
        b.setWidth(width);
        g.drawLine(x, y + FONT_SIZE, x+ width,y + FONT_SIZE);
        g.drawLine(x, y + FONT_SIZE*(iVars.size() +1), x+ width,y + FONT_SIZE*(iVars.size() +1));
        height = Math.max(FONT_SIZE*(iVars.size() + 1 + methods.size()), height);
        b.setLength(height);
        g.drawRect(x, y, width, height);

    }

    /**
     * @param l reders the line on the Graphics
     */

    // public void visit(Line l){
    //     return;
    // }

    /**
     * Renders out a implemaentation lines, dashed line with tringle head
     * @param l DisplayObject line
     */
    public void visit(ImplementationLine l){
        int x1 = l.getFirstX_Value();
        int x2 = l.getSecondX_Value();
        int y1 = l.getFirstY_Value();
        int y2 = l.getSecondY_Value();

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

        // g.drawLine((int)(xm + xn)/2,(int)(ym+yn)/2, x2, y2);
        drawDashedLine((int)(xm + xn)/2,(int)(ym+yn)/2,x2,y2);

        g.drawPolygon(xpoints, ypoints, 3);
    }
    /**
     * Renders out a inheritance lines, solid line with triangle head
     * @param l DisplayObject line
     */
    public void visit(InheritanceLine l){
        int x1 = l.getFirstX_Value();
        int x2 = l.getSecondX_Value();
        int y1 = l.getFirstY_Value();
        int y2 = l.getSecondY_Value();

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
        //drawDashedLine((int)(xm + xn)/2,(int)(ym+yn)/2,x2,y2);

        g.drawPolygon(xpoints, ypoints, 3);
    }

    /**
     * Renders out a assocation lines, solid lines with tip
     * @param l DisplayObject line
     */
    public void visit(AssociationLine l){
        int x1 = l.getFirstX_Value();
        int x2 = l.getSecondX_Value();
        int y1 = l.getFirstY_Value();
        int y2 = l.getSecondY_Value();

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

        int[] xpoints = {(int) xm, x1, (int) xn};
        int[] ypoints = {(int) ym, y1, (int) yn};

        g.drawLine(x1, y1, x2, y2);
        //drawDashedLine((int)(xm + xn)/2,(int)(ym+yn)/2,x2,y2);

        g.drawPolyline(xpoints, ypoints, 3);
    }

    /**
     * Renders out a dependency lines, dashed lines with tip
     * @param l DisplayObject line
     */
    public void visit(DependencyLine l){
        int x1 = l.getFirstX_Value();
        int x2 = l.getSecondX_Value();
        int y1 = l.getFirstY_Value();
        int y2 = l.getSecondY_Value();

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

        int[] xpoints = {(int) xm, x1, (int) xn};
        int[] ypoints = {(int) ym, y1, (int) yn};

        drawDashedLine(x1, y1, x2, y2);
        //drawDashedLine((int)(xm + xn)/2,(int)(ym+yn)/2,x2,y2);

        g.drawPolyline(xpoints, ypoints, 3);
    }

    /**
     * Renders out a implemaentation lines solid line with diamond tip
     * @param l DisplayObject line
     */
    public void visit(AggregationLine l){
        int x1 = l.getFirstX_Value();
        int x2 = l.getSecondX_Value();
        int y1 = l.getFirstY_Value();
        int y2 = l.getSecondY_Value();

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

        double xb = xm + xn - x1;
        double yb = ym + yn - y1;

        int[] xpoints = {(int) xm, x1, (int) xn, (int)xb};
        int[] ypoints = {(int) ym, y1, (int) yn, (int)yb};

        g.drawLine((int)xb, (int)yb, x2, y2);
        //drawDashedLine((int)(xm + xn)/2,(int)(ym+yn)/2,x2,y2);

        g.drawPolygon(xpoints, ypoints, 4);
    }

    /**
     * Renders out a implemaentation lines, solid line withh filled diamond tip
     * @param l DisplayObject line
     */
    public void visit(CompostionLine l){
        int x1 = l.getFirstX_Value();
        int x2 = l.getSecondX_Value();
        int y1 = l.getFirstY_Value();
        int y2 = l.getSecondY_Value();

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

        double xb = xm + xn - x1;
        double yb = ym + yn - y1;

        int[] xpoints = {(int) xm, x1, (int) xn, (int)xb};
        int[] ypoints = {(int) ym, y1, (int) yn, (int)yb};

        g.drawLine((int)xb, (int)yb, x2, y2);
        //drawDashedLine((int)(xm + xn)/2,(int)(ym+yn)/2,x2,y2);

        g.fillPolygon(xpoints, ypoints, 4);
    }

    private void drawDashedLine(int x1, int y1, int x2, int y2){
        //creates a copy of the Graphics instance
        Graphics2D g2d = (Graphics2D) g.create();

        //set the stroke of the copy, not the original
        Stroke dashed = new BasicStroke(LINE_WIDTH, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
        g2d.setStroke(dashed);
        g2d.drawLine(x1, y1, x2, y2);

        //gets rid of the copy
        g2d.dispose();
    }

    public void visit(DisplayText t){
        Font  f2  = new Font(Font.SANS_SERIF,  Font.PLAIN, FONT_SIZE);
        g.setFont(f2);
        g.drawString(t.getText(), t.getX(), t.getY() + FONT_SIZE);
    }

}
