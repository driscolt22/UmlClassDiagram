package app_model;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.util.*;
import java.io.Serializable;


public class Block  implements DisplayObject, Serializable{
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

  public String getName(){
    return this.className;
  }

  public ArrayList<String> getInstanceVariables(){
    return this.instanceVariables;
  }

  public ArrayList<String> getMethods(){
    return this.contents;
  }

  public void setClassName(String name){
    this.className = name;
  }

  public void addInstanceVariable(String instanceVariable){
    this.instanceVariables.add(instanceVariable);
  }

  public void addMethod(String method){
    this.contents.add(method);
  }

  public void setWidth(int width){
    this.width = width;
  }

  public void setLength(int length){
    this.length = length;
  }


  public void setLocation(int x, int y){
    this.x = x;
    this.y = y;
  }


  public int getX(){
    return x;
  }

  public int getY(){
    return y;
  }

  public int getWidth(){
      return this.width;
  }

  public int getLength(){
      return this.length;
  }

  public void accept(Visitor v){
      v.visit(this);
  }

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
