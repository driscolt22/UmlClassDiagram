package app_model;
import app_model.*;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.util.*;
import app_model.Variable;
import app_model.Method;
import java.io.Serializable;

/**
 * Represents a Class for a UML class diagram, with postion, name, and attributes
 */
public class Block  implements DisplayObject, Serializable{

  private int x;
  private int y;
  private int width;
  private int length;
  private String className;
  private ArrayList<Variable> instanceVariables;
  private ArrayList<Method> contents;
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
    this.instanceVariables = new ArrayList<Variable>(0);
    this.contents = new ArrayList<Method>(0);
  }

  /**
   * @return Name of class represeted by this Block
   */
  public String getName(){
    return this.className;
  }

  /**
  * @return the ArrayList of Method Objects for this class
  */
  public ArrayList<Method> getMethodList(){
    return this.contents;
  }

  /**
  * @return the ArrayList of Variable Objects for this class
  */
  public ArrayList<Variable> getVariableList(){
    return this.instanceVariables;
  }

  /**
   * @return ArrayList of all instace vars in Class
   */
  public ArrayList<String> getInstanceVariables(){
    ArrayList<String> variables = new ArrayList<>(0);
    ArrayList<Variable> list = getVariableList();
    for(int i = 0; i < list.size(); i++){
      variables.add((list.get(i)).getType() + " " + (list.get(i)).getVariableName());
    }
    return variables;
  }

  /**
   * @return String of all instance variables, each being on a new line
   */
  public String convertInstanceVariables(ArrayList<String> instanceVariables){
    String variables = "";
    ArrayList<String> list = instanceVariables;
    for(int i = 0; i < list.size(); i++){
      variables += list.get(i) + "\n";
    }
    return variables;
  }

  /**
   * @return ArrayList of all methods inside class;
   */
  public ArrayList<String> getMethods(){
    ArrayList<String> methods = new ArrayList<>(0);
    ArrayList<Method> list = getMethodList();
    for(int i = 0; i < list.size(); i++){
      Method m = list.get(i);
      String methodToAdd = m.getReturnType() + " " + m.getMethodName();
      for(String p: m.getParameters()){
      methodToAdd +=  " " + p;
      }
      methods.add(methodToAdd);
    }
    return methods;
  }

  /**
   * @return String of all methods, each being on a new line
   */
  public String convertMethods(ArrayList<String> methodList){
    String methods = "";
    ArrayList<String> list = methodList;
    for(int i = 0; i < list.size(); i++){
      methods += list.get(i) + "\n";
      }
    return methods;
  }

  /**
   * adds the inputted instance variables to the class
   */
  public void setInstanceVariables(String instanceVariables){
    this.instanceVariables.clear();
    if(instanceVariables.equals("")){
        return;
    }
    String[] variables = instanceVariables.split("\n");
    for (String variable: variables){
      this.addInstanceVariable(variable);
    }
  }

  /**
   * adds the inputted methods to the class
   */
  public void setMethods(String methods){
    this.contents.clear();
    if(methods.equals("")){
        return;
    }
    String[] methodList = methods.split("\n");
    for (String method: methodList){
      this.addMethod(method);
    }
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
    String[] typeAndName = instanceVariable.split(" ");
    if(typeAndName.length == 2){
    Variable var = new Variable(typeAndName[0], typeAndName[1]);
    this.instanceVariables.add(var);
    }
    else if(typeAndName.length == 1){
      Variable var = new Variable("Object", typeAndName[0]);
      this.instanceVariables.add(var);
    }
  }

  /**
   * @param method method to add to list of methods in class
   */
  public void addMethod(String method){
    String[] methodInfo = method.split(" ");
    if(methodInfo.length == 1){
      this.contents.add(new Method("void", methodInfo[0]));
    }
    else if(methodInfo.length == 2){
      this.contents.add(new Method(methodInfo[0], methodInfo[1]));
    }
    else if(methodInfo.length >= 3){
      Method m = new Method(methodInfo[0], methodInfo[1]);
      for(int i = 2; i < methodInfo.length; i++){
        m.addParameter(methodInfo[i]);
      }
      this.contents.add(m);
  }
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

  public boolean containsMethod(Method toFind){
    for(Method m: getMethodList()){
      if(m.equals(toFind))
        return true;
    }
    return false;
  }

  public boolean containsVariable(Variable toFind){
    for(Variable v: getVariableList()){
      if(v.equals(toFind))
        return true;
    }
    return false;
  }

  /**
  * @param b: the Block we are checking for the same methods
  * @return: true if the list of methods has the same size and the elements regardless of order
  * and false otherwise
  */
  private boolean hasSameMethods(Block b){
    if(getMethods().size()==b.getMethods().size()){
      for(Method m: b.getMethodList()){
        if(!containsMethod(m))
          return false;
      }
      return true;
    }
    else
      return false;
  }

  /**
  * @param b: the Block we are checking for the same variables
  * @return: true if the list of variables has the same size and the elements regardless of order
  * and false otherwise
  */
  private boolean hasSameVariables(Block b){
    if(getInstanceVariables().size()==b.getInstanceVariables().size()){
      for(Variable v: b.getVariableList()){
        if(!containsVariable(v))
          return false;
      }
      return true;
    }
    else
      return false;
  }

  /**
  * @param d: the object being compared to
  * @return true if the objects are blocks and have the same
  * location, size, name, methods, and variables regardless of order
  * and false otherwise
  */
  public boolean equals(Object d){
    if(d instanceof Block){
      return getX()==((Block)d).getX()&&getY()==((Block)d).getY()&&getWidth()==((Block)d).getWidth()
      &&getLength()==((Block)d).getLength()&&getName()==((Block)d).getName()
      &&hasSameMethods((Block)d)&&hasSameVariables((Block)d);
    }
    else
      return false;
  }

  /**
  * @return the number of methods held in the class
  */
  public int numMethods(){
    return getMethods().size();
  }

  /**
  * @return the number of Instance variables stored in the class
  */
  public int numVars(){
    return getInstanceVariables().size();
  }

  /**
  * @return a String representation of the Block that lists the coordinates,
  * length and width, name, methods, and instance variables
  */
  public String toString(){
    String toReturn = "name:"+ getName() + "\nx: " + String.valueOf(getX()) + "\ny: " + String.valueOf(getY()) + "\nwidth: "
    + String.valueOf(getWidth()) + "\nlength: " + String.valueOf(getLength()) + "\nmethods: ";
    int count = 0;
    for(String m: getMethods()){
      toReturn += m;
      count++;
      if(count != numMethods())
        toReturn +=  ", ";
    }
    toReturn += "\nvariables: ";
    int count2 = 0;
    for(String v: getInstanceVariables()){
      toReturn += v;
      count2++;
      if(count2 != numVars())
        toReturn +=  ", ";
    }
    return toReturn;
    }
}
