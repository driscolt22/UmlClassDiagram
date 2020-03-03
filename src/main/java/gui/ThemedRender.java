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

public class ThemedRender extends Render{

    private Graphics g;

    private static int LINE_WIDTH = 2;
    private static int BLOCK_LINE_WIDTH = 1;
    private static int FONT_SIZE = 15;
    private static int h = 10;
    private static int d = 20;

    /**
     * @param g Creates a render for the display given the graphics
     */
    public ThemedRender(Graphics g){
        super(g);
        this.g = g;
    }

    public void paintBackground(int h, int w){
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.lightGray);
        g2.fillRect(0, 0, w, h);
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
        g.drawRoundRect(x, y, width, height, 10,10);

    }
}
