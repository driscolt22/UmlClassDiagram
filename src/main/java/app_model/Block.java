package app_model;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.util.*;


public class Block  implements DisplayObject{
  private int x;
  private int y;
  private int width;
  private int length;
  private String className;
  private ArrayList<String> instanceVariables;
  private ArrayList<String> contents;


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

  public int getLength(){
    return length;
  }

  public int getWidth(){
    return width;
  }

  public void setLocation(int x, int y){
    this.x = x;
    this.y = y;
  }

  public int getXLocation(){
    return x;
  }

  public int getYLocation(){
    return y;
  }

  public void accept(Visitor v){
      v.visit(this);
  }
}
