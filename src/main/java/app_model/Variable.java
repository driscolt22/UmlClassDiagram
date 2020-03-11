package app_model;
import java.io.Serializable;
import java.util.*;

public class Variable implements Serializable{
  private String type;
  private String variableName;

  /**
  * the default construcor for the Variable Object
  */
  public Variable(){
    this.type = "";
    this.variableName = "";
  }

  /**
  * @param varType the new Variable types
  * @param varName the new Variable name
  * a construcor for Variable Objects
  * that sets the name and type of the Variable based on the given parameters
  */
  public Variable(String varType, String varName){
    this.type = varType;
    this.variableName = varName;
  }

  /**
  * @param newType the new Variable type
  * sets the Variables type to the given parameter
  */
  public void setType(String newType){
    this.type = newType;
  }

  /**
  * @param newVariable the new Variable name
  * sets the Variable's name to the given parameter
  */
  public void setVariableName(String newVariable){
    this.variableName = newVariable;
  }

  /**
  * @return the Object/primitive type of the Variable
  */
  public String getType(){
    return this.type;
  }

  /**
  * @return the name of the Variable
  */
  public String getVariableName(){
    return this.variableName;
  }

  /**
  * @param v the Variable being compared to
  * @return true if the Variables have the same name and type and false otherwise
  */
  public boolean equals(Object v){
    if(v instanceof Variable)
      return getType().equals(((Variable)v).getType())&&getVariableName().equals(((Variable)v).getVariableName());
    else
      return false;
  }
}
