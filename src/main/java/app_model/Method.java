package app_model;
import java.io.Serializable;
import java.util.*;


public class Method implements Serializable{
  private String returnType;
  private String methodName;
  private ArrayList<String> parameters;

  public Method(String type, String name){
    this.methodName = name;
    this.returnType = type;
    this.parameters = new ArrayList<String>(0);
  }

  /**
  * default construcor for Method that sets its name and return type to blank strings
  */
  public Method(){
    this.methodName = "";
    this.returnType = "";
    this.parameters= new ArrayList<String>(0);
  }

  /**
  * @return the name of the method as a String
  */
  public String getMethodName(){
    return this.methodName;
  }

  /**
  * @return the return type of the method as a String
  */
  public String getReturnType(){
    return this.returnType;
  }

  /**
  * @return the ArrayList of Stings holding the parameters(only their type is given as a String)
  */
  public ArrayList<String> getParameters(){
    return this.parameters;
  }

  /**
  * @param name: the new name for the method
  * sets the name of the method to the given name in the parameter
  */
  public void setMethodName(String name){
    this.methodName = name;
  }

  /**
  * @param type: the new return type for the method
  * sets the return type for the method to the given return type parameter
  */
  public void setReturnType(String type){
    this.returnType = type;
  }

  /**
  * @param type the type for the parameter that is to be added to the list of parameters
  * adds a given parameter to the parameter list
  */
  public void addParameter(String type){
    this.parameters.add(type);
  }

  /**
  * @param m the method n=being compared to
  * returns true if the methods have the same name return type and parameters and false otherwise
  */
  public boolean equals(Object m){
    if(m instanceof Method){
      return getReturnType().equals(((Method)m).getReturnType())&&getMethodName().equals(((Method)m).getMethodName())
      &&getParameters().equals(((Method)m).getParameters());
    }
    else
      return false;
  }
}
