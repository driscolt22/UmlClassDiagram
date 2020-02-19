package gui;
import java.util.*;

import app_model.Visitor;
import app_model.Line;
import app_model.Block;
import app_model.DisplayText;
import app_model.lines.*;
import java.io.FileWriter;
import java.io.IOException;


public class Saver implements Visitor{

  private String fileName;
  private FileWriter writer;

  public Saver(String fName){
    fileName = fName;
    try{
    writer = new FileWriter(fileName);
    }catch (IOException e) {
            e.getStackTrace();
          }
  }

  public void visit(Line l){
    String toSave = "";
    toSave += "Line";
    toSave += String.valueOf(l.getFirstX_Value()) + "\n" + String.valueOf(l.getFirstY_Value()) + "\n";
    toSave += String.valueOf(l.getSecondX_Value()) + "\n" + String.valueOf(l.getSecondY_Value()) + "\n";
    //f(l.getHead() != null)
    //  toSave +=
    try{
      writer.write(toSave);
    }catch (IOException e) {
            e.getStackTrace();
          }
  }

  public void visit(Block b){
    String toSave = "";
    toSave += "Block";
    toSave += String.valueOf(b.getX()) + "," + String.valueOf(b.getY()) + "\n";
    toSave += String.valueOf(b.getWidth()) + "," + String.valueOf(b.getLength()) + "\n";
    toSave += b.getName() + "\n";
    toSave += addVariables(b);
    toSave += addMethods(b);
    try{
      writer.write(toSave);
    }catch (IOException e) {
            e.getStackTrace();
          }
  }

  private String addMethods(Block b){
    ArrayList<String> toAdd = b.getMethods();
    String toSave = "";
    for(String m: toAdd){
      if(m != null)
        toSave += m + " ";
    }
    return toSave;
  }

  private String addVariables(Block b){
    ArrayList<String> toAdd = b.getInstanceVariables();
    String toSave = "";
    for(String m: toAdd){
      if(m != null)
        toSave += m + " ";
    }
    return toSave;
  }

  public void visit(DisplayText t){
      return;
  }

  public void visit(AggregationLine l){ return; }
  public void visit(AssociationLine l){ return; }
  public void visit(CompostionLine l){ return; }
  public void visit(DependencyLine l){ return; }
  public void visit(ImplementationLine l){ return; }
  public void visit(InheritanceLine l){ return; }

}
