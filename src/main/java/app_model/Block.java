package app_model;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.util.*;
import java.io.Serializable;

public class Block  implements DisplayObject{
/**
 * Represents a Class for a UML class diagram, with postion, name, and attributes
 */
public class Block  implements DisplayObject{

  private int x;
  private int y;
  private int width;
  private int length;
  private String className;
  private ArrayList<String> instanceVariables;
  private ArrayList<String> contents;


  /**
   * Creates a empty square Block at 0,0 with size 100
   */
  public Block()
  {
    this.x = 0;
    this.y = 0;
    this.width = 100;
    this.length = 100;
    this.className = "";
    this.instanceVariables = new ArrayList<String>();
    this.contents = new ArrayList<String>();
  }

  /**
   * @return Name of class represeted by this Block
   */
  public String getName(){
    return this.className;
  }

  /**
   * @return ArrayList of all instace vars in Class
   */
  public ArrayList<String> getInstanceVariables(){
    return this.instanceVariables;
  }

  /**
   * @return ArrayList of all methods inside class;
   */
  public ArrayList<String> getMethods(){
    return this.contents;
  }

  /**
   * @param name new name for class represented by Block
   */
  public void setClassName(String name){
    this.className = name;
  }

  /**
   * @param instanceVariable instance varible to add to list of instace vars
   */
  public void addInstanceVariable(String instanceVariable){
    this.instanceVariables.add(instanceVariable);
  }

  /**
   * @param method method to add to list of methods in class
   */
  public void addMethod(String method){
    this.contents.add(method);
  }

  /**
   * @param width new Width for this Block
   */
  public void setWidth(int width){
    this.width = width;
  }

  /**
   * @param length new length(height) to set this Block
   */
  public void setLength(int length){
    this.length = length;
  }

  /**
   * @param x Move Block to this given x-cordi
   * @param y Move Block to this given y-cord
   */
  public void setLocation(int x, int y){
    this.x = x;
    this.y = y;
  }

  /**
   * @return x-cord of Block
   */
  public int getX(){
    return x;
  }

  /**
   * @return y-cord of BLock
   */
  public int getY(){
    return y;
  }

  /**
   * @return Width of BLock
   */
  public int getWidth(){
      return this.width;
  }

  /**
   * @return Length(height) of BLock
   */
  public int getLength(){
      return this.length;
  }

  /**
   * @param v accepts a DisplayObject vistor to this
   */
  public void accept(Visitor v){
      v.visit(this);
  }

  /**
   * @param  x x-cord
   * @param  y y-cord
   * @return   if the x,y is inside this Block
   */
  public boolean contains(int x, int y){
      return x >= getX() && x <= getX() + getWidth() && y >= getY() && y <= getY() + getLength();
  }

  private boolean hasSameMethods(Block b){
    if(getMethods().size()==b.getMethods().size()){
      for(String m: b.getMethods()){
        if(!getMethods().contains(m))
          return false;
      }
      return true;
    }
    else
      return false;
  }

  private boolean hasSameVariables(Block b){
    if(getInstanceVariables().size()==b.getInstanceVariables().size()){
      for(String v: b.getInstanceVariables()){
        if(!getInstanceVariables().contains(v))
          return false;
      }
      return true;
    }
    else
      return false;
  }

  public boolean equals(DisplayObject d){
    if(d instanceof Block){
      return getX()==((Block)d).getX()&&getY()==((Block)d).getY()&&getWidth()==((Block)d).getWidth()
      &&getLength()==((Block)d).getLength()&&getName()==((Block)d).getName()
      &&hasSameMethods((Block)d)&&hasSameVariables((Block)d);
    }
    else
      return false;
  }

  public String toString(){

    String toReturn = "\nname:"+ getName() + "\nx: " + String.valueOf(getX()) + "\ny: " + String.valueOf(getY()) + "\nwidth: "
    + String.valueOf(getWidth()) + "\nlength: " + String.valueOf(getLength()) + "\nmethods: ";
    for(String m: getMethods()){
      toReturn += m + ", ";
    }
    toReturn += "\n";
    for(String v: getInstanceVariables()){
      toReturn += v + ", ";
    }
    return toReturn + "\n";
  }
}
