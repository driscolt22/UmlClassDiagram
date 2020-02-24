package app_model;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.util.*;

public class DisplayText implements DisplayObject{
    private int x;
    private int y;
    private String t;
    private static int WIDTH = 50;
    private static int HEIGHT = 50;

    /**
     * creates a new DisplayText to represent some floating text
     */
    public DisplayText(){
        this.x = 0;
        this.y = 0;
        this.t = "Floating Text";
    }

    /**
     * @param v Vistire ot accept and call
     */
    public void accept(Visitor v){
        v.visit(this);
    }

    /**
     * @param t Changes the text beign represented by this
     */
    public void setText(String t){
        this.t = t;
    }

    /**
     * @return Text of this
     */
    public String getText(){
        return this.t;
    }

    /**
     * @return x-cord
     */
    public int getX(){
        return this.x;
    }

    /**
     * @return y-cord
     */
    public int getY(){
        return this.y;
    }

    /**
     * @param x moves to this x-cord
     * @param y moves to this x-cord
     */
    public void setLocation(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * @param  x given x-cord
     * @param  y given y-cord
     * @return   if the x,y is containd within the text
     */
    public boolean contains(int x, int y){
            return x >= getX() && y >= getY() && x <= getX() + WIDTH && y <= getY() + HEIGHT;
    }
}
