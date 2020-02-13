package app_model;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.util.*;
import app_model.Block;

public class Line implements DisplayObject{
  private int x1;
  private int y1;
  private int x2;
  private int y2;
  private Block head;
  private Block tail;


  public Line(int x1, int y1, int x2, int y2)
  {
    this.x1 = x1;
    this.y1 = y1;
    this.x2 = x2;
    this.y2 = y2;
  }

  public Line(Block head, Block tail){
      this.head = head;
      this.tail = tail;
      updatePosition();
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
      if(b.getY() <= y2 && b.getY()+b.getLength() >= y2)
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

  public void updatePosition(){
      //sets x1, y1, x2, y2
      return;
  }
  public void setLine(Block head, Block tail){
    if(head.getX() > tail.getX()){
      setHead(head.getX(), head.getY() + head.getLength() / 2);
      setTail(tail.getX() + getWidth(), tail.getY() + tail.getLength() / 2);
    }else if(head.getX() < tail.getX()){
      setHead(head.getX() + head.getWidth(), head.getY() + head.getLength() / 2);
      setTail(tail.getX(), tail.getY() + tail.getLength() / 2);
    }else if((head.getX() == tail.getX()) && (head.getY() > tail.getY())){
      setHead(head.getX() + head.getWidth() / 2, head.getY());
      setTail(tail.getX() + tail.getWidth() / 2, tail.getY() + tail.getLength());
    }else if((head.getX() == tail.getX()) && (head.getY() < tail.getY())){
      setHead(head.getX() + head.getWidth() / 2, head.getY() + head.getLength());
      setTail(tail.getX() + tail.getWidth() / 2, tail.getY());
    }
  }

  private void setHead(int x1, int y1){
      setFirstX_Value(x1);
      setFirstY_Value(y1);
  }
  private void setTail(int x2, int y2){
    setSecondX_Value(x2);
    setSecondY_Value(y2);
  }

  public void accept(Visitor v){
      v.visit(this);
  }

}
