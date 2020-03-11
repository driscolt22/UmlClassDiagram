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
import app_model.DisplayObject;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.awt.*;

public class CodeGenerator implements Visitor{
  private AppModel app;
  private String file;

  /**
  * creates an object for exporting code of a visual class diagram
  */
  public CodeGenerator(AppModel app, String file){
    this.app = app;
    this.file = file;

  }

  public void visit(Block b){
    String modelCode = "";
    final Boolean[] isInterface = new Boolean[1];
    isInterface[0] = false;
    ArrayList<String> extendsList = new ArrayList<String>();
    ArrayList<String> implementsList = new ArrayList<String>();
    Visitor v = new Visitor(){

        public void visit(Block b){}
        public void visit(DisplayText t){}
        public void visit(AggregationLine l){}
        public void visit(AssociationLine l){}
        public void visit(CompostionLine l){}
        public void visit(DependencyLine l){}
        public void visit(ImplementationLine l){
            if(l.getHead() == b){
                isInterface[0] = true;
            }else if(l.getTail() == b){
               implementsList.add(b.getName());
            }
        }
        public void visit(InheritanceLine l){
          if(l.getTail() == b){
            extendsList.add(l.getHead().getName());
          }
        }
    };
    for(DisplayObject d: app.getDisplayObjects()){
      d.accept(v);
    }
    String classType = "class";
    if(isInterface[0]){
        classType = "interface";
    }
    modelCode += "public" + " " + classType +" " + b.getName() + " ";
    if(!implementsList.isEmpty()){
      modelCode += "implements" + " " + convertList(implementsList);
    }
    if(!extendsList.isEmpty()){
      modelCode += "extends" + " " + convertList(extendsList);
    }
    modelCode += "{" + "\n";

    ArrayList<Variable> variableList = b.getVariableList();
    ArrayList<Method> methodList = b.getMethodList();

    for(int i = 0; i < variableList.size(); i++){
      modelCode += "   " + "private" + " " + variableList.get(i).getType() + " " +
      variableList.get(i).getVariableName() + ";" + "\n";
    }
    for(int i = 0; i < methodList.size(); i++){
      modelCode += "   " + "public" + " " + methodList.get(i).getReturnType() + " " + methodList.get(i).getMethodName() +
      "(";
      modelCode += convertParams(methodList.get(i).getParameters());
      modelCode += "){}";

    }
    modelCode+= "\n}";
    writeStringToFile(file +"/" +  b.getName() + ".java", modelCode);
  }

  private String convertList(ArrayList<String> list){
    String convertedStr = "";
    for(int i = 0; i < list.size(); i++){
      if(i < list.size() - 1){
        convertedStr += list.get(i) + ", ";
      }
      else if(i == list.size() -1){
        convertedStr += list.get(i);
      }
    }
    return convertedStr;
  }

  private String convertParams(ArrayList<String> list){
    String convertedStr = "";
    for(int i = 0; i < list.size(); i+=2){
      int j = i + 1;
      if(j < list.size() - 1){
        convertedStr += list.get(i) + " " + list.get(j) + ", ";
      }else if(j == list.size() - 1){
        convertedStr += list.get(i) + " " + list.get(j);
      }
    }
    return convertedStr;
    }

  public void writeStringToFile(String file, String data){
      try (PrintWriter toExport = new PrintWriter(file)) {
          toExport.println(data);
        }catch (Exception e) {
            System.out.println(e);
        }
    }

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
  }
  public void visit(InheritanceLine l){
//extends
  }
}
