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

    public DisplayText(){
        this.x = 0;
        this.y = 0;
        this.t = "Floating Text";
    }

    public void accept(Visitor v){
        v.visit(this);
    }

    public void setText(String t){
        this.t = t;
    }

    public String getText(){
        return this.t;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public void setLocation(int x, int y){
        this.x = x;
        this.y = y;
    }

    public boolean contains(int x, int y){
            return x >= getX() && y >= getY() && x <= getX() + WIDTH && y <= getY() + HEIGHT;
    }

    public boolean equals(DisplayObject d){
      if(d instanceof DisplayText){
        return getX()==((DisplayText)d).getX()&&getY()==((DisplayText)d).getY()
        &&getText().equals(((DisplayText)d).getText());
      }
      else
        return false;
    }
}
