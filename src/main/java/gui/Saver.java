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

  //private String fileName;
  //private FileWriter writer;
  private String toSave;

  public Saver(){
    toSave = "";

  }

  public void visit(Line l){
    this.toSave += "Line\n";
    this.toSave += String.valueOf(l.getFirstX_Value()) + "\n" + String.valueOf(l.getFirstY_Value()) + "\n";
    this.toSave += String.valueOf(l.getSecondX_Value()) + "\n" + String.valueOf(l.getSecondY_Value()) + "\n";
    //f(l.getHead() != null)
    //  toSave +=
      //writer.write(toSave);
  }

  public void visit(Block b){
    this.toSave += "Block\n";
    this.toSave += String.valueOf(b.getX()) + "\n" + String.valueOf(b.getY()) + "\n";
    this.toSave += String.valueOf(b.getWidth()) + "\n" + String.valueOf(b.getLength()) + "\n";
    this.toSave += b.getName() + "\n";
    this.toSave += addVariables(b);
    this.toSave += addMethods(b);
      //writer.write(toSave);
  }

  private String addMethods(Block b){
    ArrayList<String> toAdd = b.getMethods();
    String methods = "";
    for(String m: toAdd){
      if(m != null)
        methods += m + " ";
    }
    return methods;
  }

  private String addVariables(Block b){
    ArrayList<String> toAdd = b.getInstanceVariables();
    String varsToSave = "";
    for(String m: toAdd){
      if(m != null)
        varsToSave += m + " ";
    }
    return varsToSave;
  }

  public void visit(DisplayText t){
    this.toSave += "DisplayText\n";
    this.toSave += String.valueOf(t.getX()) + "\n" + String.valueOf(t.getY()) + "\n";
    this.toSave += t.getText();
      //writer.write(toSave);
  }

  public void visit(AggregationLine l){
    this.toSave += "AggregationLine\n";
    this.toSave += String.valueOf(l.getFirstX_Value()) + "\n" + String.valueOf(l.getFirstY_Value()) + "\n";
    this.toSave += String.valueOf(l.getSecondX_Value()) + "\n" + String.valueOf(l.getSecondY_Value()) + "\n";
    //f(l.getHead() != null)
    //  toSave +=
      //writer.write(toSave);
  }

  public void visit(AssociationLine l){
    this.toSave += "AssociationLine\n";
    this.toSave += String.valueOf(l.getFirstX_Value()) + "\n" + String.valueOf(l.getFirstY_Value()) + "\n";
    this.toSave += String.valueOf(l.getSecondX_Value()) + "\n" + String.valueOf(l.getSecondY_Value()) + "\n";
    //f(l.getHead() != null)
    //  toSave +=
      //writer.write(toSave);
   }
  public void visit(CompostionLine l){
    this.toSave += "CompostionLine\n";
    this.toSave += String.valueOf(l.getFirstX_Value()) + "\n" + String.valueOf(l.getFirstY_Value()) + "\n";
    this.toSave += String.valueOf(l.getSecondX_Value()) + "\n" + String.valueOf(l.getSecondY_Value()) + "\n";
    //f(l.getHead() != null)
    //  toSave +=
      //writer.write(toSave);
    }
  public void visit(DependencyLine l){
    this.toSave += "DependencyLine\n";
    this.toSave += String.valueOf(l.getFirstX_Value()) + "\n" + String.valueOf(l.getFirstY_Value()) + "\n";
    this.toSave += String.valueOf(l.getSecondX_Value()) + "\n" + String.valueOf(l.getSecondY_Value()) + "\n";
    //f(l.getHead() != null)
    //  toSave +=
      //writer.write(toSave);
    }
  public void visit(ImplementationLine l){
    this.toSave += "ImplementationLine\n";
    this.toSave += String.valueOf(l.getFirstX_Value()) + "\n" + String.valueOf(l.getFirstY_Value()) + "\n";
    this.toSave += String.valueOf(l.getSecondX_Value()) + "\n" + String.valueOf(l.getSecondY_Value()) + "\n";
    //f(l.getHead() != null)
    //  toSave +=
      //writer.write(toSave);
    }
  public void visit(InheritanceLine l){
    this.toSave += "InheritanceLine\n";
    this.toSave += String.valueOf(l.getFirstX_Value()) + "\n" + String.valueOf(l.getFirstY_Value()) + "\n";
    this.toSave += String.valueOf(l.getSecondX_Value()) + "\n" + String.valueOf(l.getSecondY_Value()) + "\n";
    //f(l.getHead() != null)
    //  toSave +=
      //writer.write(toSave);
     }
    public String getToSave(){
      return toSave;
    }

}
