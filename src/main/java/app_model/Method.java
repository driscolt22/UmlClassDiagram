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

  public Method(){
    this.methodName = "";
    this.returnType = "";
    this.parameters= new ArrayList<String>(0);
  }

  public String getMethodName(){
    return this.methodName;
  }

  public String getReturnType(){
    return this.returnType;
  }

  public ArrayList<String> getParameters(){
    return this.parameters;
  }

  public void setMethodName(String name){
    this.methodName = name;
  }

  public void setReturnType(String type){
    this.returnType = type;
  }

  public void addParameter(String type){
    this.parameters.add(type);
  }

  public boolean equals(Object m){
    if(m instanceof Method){
      return getReturnType().equals(((Method)m).getReturnType())&&getMethodName().equals(((Method)m).getMethodName())
      &&getParameters().equals(((Method)m).getParameters());
    }
    else
      return false;
  }
}
