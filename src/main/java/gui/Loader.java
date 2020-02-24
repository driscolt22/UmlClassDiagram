package gui;
import java.util.*;
import app_model.Visitor;
import app_model.Line;
import app_model.Block;
import app_model.DisplayText;
import app_model.lines.*;

import java.io.FileReader;
import java.io.IOException;

public class Loader implements Visitor{

  private String objectInfo;

  public Loader(String fileInfo){
    objectInfo = fileInfo;
  }

  public void visit(Line l){
    String[] lineInfo = objectInfo.split("\n");
    l.setFirstX_Value(Integer.parseInt(lineInfo[0]));
    l.setFirstY_Value(Integer.parseInt(lineInfo[1]));
    l.setSecondX_Value(Integer.parseInt(lineInfo[2]));
    l.setSecondY_Value(Integer.parseInt(lineInfo[3]));
  }

  public void visit(Block b){
    String[] lineInfo = objectInfo.split("\n");
    b.setLocation(Integer.parseInt(lineInfo[0]), Integer.parseInt(lineInfo[1]));
    b.setWidth(Integer.parseInt(lineInfo[2]));
    b.setLength(Integer.parseInt(lineInfo[3]));
    b.setClassName(lineInfo[4]);
    String vars = lineInfo[5];
    String[] varsArray = vars.split(" ");
    for(int i = 0; i < varsArray.length; i++){
      b.addInstanceVariable(varsArray[i]);
    }
    String methods = lineInfo[6];
    String[] methodsArray = methods.split(" ");
    for(int i = 0; i < methodsArray.length; i++){
      b.addMethod(methodsArray[i]);
    }
  }

    public void visit(AggregationLine l){
      String[] lineInfo = objectInfo.split("\n");
      l.setFirstX_Value(Integer.parseInt(lineInfo[0]));
      l.setFirstY_Value(Integer.parseInt(lineInfo[1]));
      l.setSecondX_Value(Integer.parseInt(lineInfo[2]));
      l.setSecondY_Value(Integer.parseInt(lineInfo[3]));
      return;
    }
    public void visit(AssociationLine l){
      String[] lineInfo = objectInfo.split("\n");
      l.setFirstX_Value(Integer.parseInt(lineInfo[0]));
      l.setFirstY_Value(Integer.parseInt(lineInfo[1]));
      l.setSecondX_Value(Integer.parseInt(lineInfo[2]));
      l.setSecondY_Value(Integer.parseInt(lineInfo[3]));
      }
    public void visit(CompostionLine l){
      String[] lineInfo = objectInfo.split(",");
      l.setFirstX_Value(Integer.parseInt(lineInfo[0]));
      l.setFirstY_Value(Integer.parseInt(lineInfo[1]));
      l.setSecondX_Value(Integer.parseInt(lineInfo[2]));
      l.setSecondY_Value(Integer.parseInt(lineInfo[3]));
      }
    public void visit(DependencyLine l){
      String[] lineInfo = objectInfo.split("\n");
      l.setFirstX_Value(Integer.parseInt(lineInfo[0]));
      l.setFirstY_Value(Integer.parseInt(lineInfo[1]));
      l.setSecondX_Value(Integer.parseInt(lineInfo[2]));
      l.setSecondY_Value(Integer.parseInt(lineInfo[3]));
      }
    public void visit(ImplementationLine l){
      String[] lineInfo = objectInfo.split("\n");
      l.setFirstX_Value(Integer.parseInt(lineInfo[0]));
      l.setFirstY_Value(Integer.parseInt(lineInfo[1]));
      l.setSecondX_Value(Integer.parseInt(lineInfo[2]));
      l.setSecondY_Value(Integer.parseInt(lineInfo[3]));
      }
    public void visit(InheritanceLine l){
      String[] lineInfo = objectInfo.split("\n");
      l.setFirstX_Value(Integer.parseInt(lineInfo[0]));
      l.setFirstY_Value(Integer.parseInt(lineInfo[1]));
      l.setSecondX_Value(Integer.parseInt(lineInfo[2]));
      l.setSecondY_Value(Integer.parseInt(lineInfo[3]));
      }
    public void visit(DisplayText t){
      String[] textInfo = objectInfo.split("\n");
      t.setLocation(Integer.parseInt(textInfo[0]),Integer.parseInt(textInfo[1]));
      t.setText(textInfo[2]);
      }

}
