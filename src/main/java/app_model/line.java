package app_model;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.util.*;


public class Line implements DisplayObject{
  private int x1;
  private int y1;
  private int x2;
  private int y2;


  public Line(int x1, int y1, int x2, int y2)
  {
    this.x1 = x1;
    this.y1 = y1;
    this.x2 = x2;
    this.y2 = y2;
  }

  public int getFirstX_Value(){
    return this.x1;
  }

  public int getFirstY_Value(){
    return this.y1;
  }

  public int getSecondX_Value(){
    return this.x2;
  }

  public int getSecondY_Value(){
    return this.y2;
  }

  public int setFirstX_Value(int value){
    return this.x1 = value;
  }

  public int setFirstY_Value(int value){
    return this.y1 = value;
  }

  public int setSecondX_Value(int value){
    return this.x2 = value;
  }

  public int setSecondY_Value(int value){
    return this.y2 = value;
  }

  public boolean pointOneIsConnected(Block b){
    if(b.getX() == x1 || b.getX() + b.getWidth() == x1){
      if(b.getY() >= y1 && b.getY()+b.getLength() <= y1)
        return true;
      else
        return false;
    }
    else if(b.getY() == y1 || b.getY() + b.getLength() == y1){
      if(b.getX() <= x1 && b.getX()+b.getWidth() >= x1)
        return true;
      else
        return false;
    }
    else
      return false;
  }

  public boolean pointTwoIsConnected(Block b){
    if(b.getX() == x2 || b.getX() + b.getWidth() == y2){
      if(b.getY() >= y2 && b.getY()+b.getLength() <= y2)
        return true;
      else
        return false;
    }
    else if(b.getY() == y2 || b.getY() + b.getLength() == y2){
      if(b.getX() <= x2 && b.getX()+b.getWidth() >= x2)
        return true;
      else
        return false;
    }
    else
      return false;
  }

  public void accept(Visitor v){
      v.visit(this);
  }

}
