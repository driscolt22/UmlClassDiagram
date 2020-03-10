package gui;
import java.util.*;

import app_model.Visitor;
import app_model.Line;
import app_model.Block;
import app_model.AppModel;
import app_model.DisplayText;
import app_model.lines.*;
import app_model.Variable;
import app_model.Method;

import java.io.FileReader;
import java.io.IOException;

public class CodeGenerator implements Visitor{

  private String modelCode;

  /**
  * creates an object for exporting code of a visual class diagram
  */
  public CodeGenerator(){
    this.modelCode = "";
  }

  public void visit(Block b){
    this.modelCode += "public class " + b.getName() + "{" + "\n";
    ArrayList<Variable> variableList = b.getVariableList();
    ArrayList<Method> methodList = b.getMethodList();
    ArrayList<String> paramList = b.getMethodList().getParameters();
    for(int i = 0; i < variableList.size(); i++){
      this.modelCode += "   " + "private" + " " + variableList.get(i).getType() + " " +
      variableList.get(i).getVariableName() + ";" + "\n";
    }
    for(int i = 0; i < methodList.size(); i++){
      this.modelCode += "   " + "public" + " " + methodList.get(i).getReturnType() + " " methodList.get(i).getMethodName(); +
      "(" + iterateParams(paramList) + "){}";
    }
  }

  private String createConstructor(String className){
    String constructorString;
    constructorString += "public" + " " + className + "()" + "{}" + "\n";
  }

  private String iterateParams(ArrayList<String> paramList){
    String paramString;
    for(int i = 0; i < paramList.size(); i++){
      if(i < paramList.size() - 1){
        paramString += paramList.get(i) + ", ";
      }else{
        paramString += paramList.get(i);
      }
    }
    return paramString;
  }

//  private void addToFile(String toAdd){
//      try{
//        PrintWriter toExport = new PrintWriter("codeFile.txt");
//        export.println(modelCode);
//        export.close();
//      }catch(IOException e){
//        System.out.println("error exporting");
//        e.getStackTrace();
//      }
//  }

  public void visit(DisplayText t){

  }
  public void visit(AggregationLine l){

  }
  public void visit(AssociationLine l){

  }
  public void visit(CompostionLine l){

  }
  public void visit(DependencyLine l){

  }
  public void visit(ImplementationLine l){
//implements
    this.modelCode = this.modelCode.replaceFirst("{", "JAVA");


  }
  public void visit(InheritanceLine l){
//extends
  }
}
