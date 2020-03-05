package app_model;
import java.io.Serializable;
import java.util.*;

public class Variable implements Serializable{
  private String type;
  private String variableName;

  public Variable(){
    this.type = "";
    this.variableName = "";
  }

  public Variable(String varType, String varName){
    this.type = varType;
    this.variableName = varName;
  }

  public void setType(String newType){
    this.type = newType;
  }

  public void setVariableName(String newVariable){
    this.variableName = newVariable;
  }

  public String getType(){
    return this.type;
  }

  public String getVariableName(){
    return this.variableName;
  }

  public boolean equals(Object v){
    if(v instanceof Variable)
      return getType().equals(((Variable)v).getType())&&getVariableName().equals(((Variable)v).getVariableName());
    else
      return false;
  }
}
