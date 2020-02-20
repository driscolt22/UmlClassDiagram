package app_model;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.util.*;
import app_model.Block;

public abstract class Line implements DisplayObject{
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
        // if(b.getX() == x1 || b.getX() + b.getWidth() == x1){
        //     if(b.getY() >= y1 && b.getY()+b.getLength() <= y1)
        //     return true;
        //     else
        //     return false;
        // }
        // else if(b.getY() == y1 || b.getY() + b.getLength() == y1){
        //     if(b.getX() <= x1 && b.getX()+b.getWidth() >= x1)
        //     return true;
        //     else
        //     return false;
        // }
        // else
        // return false;
        return (x1 >= b.getX() && x1 <= b.getX() + b.getWidth() &&
                y1 >= b.getY() && y1 <= b.getY() + b.getLength());
    }

    public boolean pointTwoIsConnected(Block b){
      //if(b.getY() <= y2 && b.getY()+b.getLength() >= y2){
        //if(b.getX() == x2 ){
          //  setTail(x2, b.getY()+(b.getLength()/2), b);
    //        return true;
    //      }
    //    if(b.getX() + b.getWidth() == x2){
      //      setTail(x2, b.getY()+(b.getLength()/2), b);
    //        return true;
    //      }
    //    else
    //        return false;
    //    }
    //  if(b.getX() <= x2 && b.getX()+b.getWidth() >= x2){
      //  if(b.getY() == y2){
          //setTail(b.getX()+(b.getWidth()/2), y2, b);
        //  return true;
      //  }
      //  if(b.getY() + b.getLength() == y2){
      //    setTail(b.getX()+(b.getWidth()/2), y2, b);
        //  return true;
      //  }
        //else
      //    return false;
    //  }
      //else
      //  return false;
    //}
        return (x2 >= b.getX() && x2 <= b.getX() + b.getWidth() &&
                y2 >= b.getY() && y2 <= b.getY() + b.getLength());
    }

    public void updatePosition(){
        if(head != null){ //set postion of head
            updateHead();
        }
        if(tail != null){
            updateTail();
        }
    }

    private void updateHead(){
        double x = (double)head.getX();
        double y = (double)head.getY();
        double h = (double)head.getLength();
        double l = (double)head.getWidth();
        double cx = x + l/2;
        double cy = y + h/2;
        if(Math.abs((this.y2 - cy)/(this.x2 - cx)) > (h/l)){
            if(this.y2 > cy){
                setHead((int)cx, (int)(y + h));
            }else{
                setHead((int)cx, (int)y);
            }
        }else{
            if(this.x2 > cx){
                setHead((int)(x + l), (int)cy);
            }else{
                setHead((int)x, (int)cy);
            }
        }
    }

    private void updateTail(){
        double x = (double)tail.getX();
        double y = (double)tail.getY();
        double h = (double)tail.getLength();
        double l = (double)tail.getWidth();
        double cx = x + l/2;
        double cy = y + h/2;
        if(Math.abs((this.y1 - cy)/(this.x1 - cx)) > (h/l)){
            if(this.y1 > cy){
                setTail((int)cx, (int)(y + h));
            }else{
                setTail((int)cx, (int)y);
            }
        }else{
            if(this.x1 > cx){
                setTail((int)(x + l), (int)cy);
            }else{
                setTail((int)x, (int)cy);
            }
        }
    }

    public void updatePosition2(){
        if(head != null && tail != null){
            if(head.getX() > tail.getX()){
                setHead(head.getX(), head.getY() + head.getLength() / 2);
                setTail(tail.getX() + tail.getWidth(), tail.getY() + tail.getLength() / 2);
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
    }

    public void setLine(Block head, Block tail){
        this.head = head;
        this.tail = tail;
        updatePosition();
    }

    public void setHead(int x1, int y1){
        setFirstX_Value(x1);
        setFirstY_Value(y1);
    }
    public void setTail(int x2, int y2){
        setSecondX_Value(x2);
        setSecondY_Value(y2);
    }

    public boolean connectToBlock(Block b){
        if(pointOneIsConnected(b)){
            //System.out.println("connecting Head");
            connectHead(b);
            return true;
        }else if(pointTwoIsConnected(b)){
            connectTail(b);
            return true;
        }
        return false;
    }

    public void connectHead(Block b){
        this.head = b;
        //updatePosition();
    }

    public void connectTail(Block b){
        this.tail = b;
        //updatePosition();
    }
    public void accept(Visitor v){
        v.visit(this);
    }

    public boolean contains(int x,int y){
        double y1 = (double)getFirstY_Value();
        double y2 = (double)getSecondY_Value();
        double x1 = (double)getFirstX_Value();
        double x2 = (double)getSecondX_Value();
        double d = Math.abs((y2-y1)*x -(x2-x1)*y+x2*y1-y2*x1)/
        Math.sqrt(Math.pow(y2-y1,2)+Math.pow(x2-x1,2));
        System.out.println("Selected l, d =" + d);
        return d < 5.0;
    }

    public boolean equals(Line l){
      if(getFirstX_Value()==l.getFirstX_Value()&&getFirstY_Value()==getFirstY_Value()
      &&getSecondX_Value()==l.getSecondX_Value()&&getSecondY_Value()==l.getSecondY_Value())
          return true;
      else
          return false;
    }

}
