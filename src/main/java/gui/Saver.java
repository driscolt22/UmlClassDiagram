package gui;
import java.util.*;

import app_model.Visitor;
import app_model.Line;
import app_model.Block;
import app_model.DisplayText;
import app_model.lines.*;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.File;


public class Saver implements Visitor{

  private String fileName;

  /**
  * creates Strings to be saved to a text file that represent instances of DisplayObjects
  */
  public Saver(String file){
    fileName = file;
    clearFile();
  }

  private void clearFile(){
    try{
      BufferedWriter writer = new BufferedWriter(new FileWriter(this.fileName));
      writer.write("");
      writer.close();
    }catch(IOException e){
      System.out.println("error saving");
      e.getStackTrace();
    }
  }

  public void visit(Line l){
    String toSave = "";
    toSave += "Line\n";
    toSave += String.valueOf(l.getFirstX_Value()) + "\n" + String.valueOf(l.getFirstY_Value()) + "\n";
    toSave += String.valueOf(l.getSecondX_Value()) + "\n" + String.valueOf(l.getSecondY_Value()) + "\n";
    //f(l.getHead() != null)
    //  toSave +=
      //writer.write(toSave);
    addToFile(toSave);
  }

  public void visit(Block b){
    String toSave = "";
    toSave += "Block\n";
    toSave += String.valueOf(b.getX()) + "\n" + String.valueOf(b.getY()) + "\n";
    toSave += String.valueOf(b.getWidth()) + "\n" + String.valueOf(b.getLength()) + "\n";
    toSave += b.getName() + "\n";
    toSave += addVariables(b) + "\n";
    toSave += addMethods(b) + "\n";

      //writer.write(toSave);
      addToFile(toSave);
  }

  /**
  * @param the given block
  * @return a string of the methods of the given block seperated by spaces
  */
  private String addMethods(Block b){
    ArrayList<String> toAdd = b.getMethods();
    if(toAdd.size()==0){
      return " ";
    }
    String methods = "";
    for(String m: toAdd){
      if(m != null){
        methods += m + " ";
      }
    }
    return methods;
  }

  /**
  * @param the given block
  * @return a string of the instanceVariables of the given block seperated by spaces
  */
  private String addVariables(Block b){
    ArrayList<String> toAdd = b.getInstanceVariables();
    if(toAdd.size()==0){
      return " ";
    }
    String varsToSave = "";
    for(String m: toAdd){
      if(m != null){
        varsToSave += m + " ";
      }
    }
    return varsToSave;
  }

  public void visit(DisplayText t){
    String toSave = "";
    toSave += "DisplayText\n";
    toSave += String.valueOf(t.getX()) + "\n" + String.valueOf(t.getY()) + "\n";
    toSave += t.getText() + "\n";
      //writer.write(toSave);
      addToFile(toSave);
  }

  public void visit(AggregationLine l){
    String toSave = "";
    toSave += "AggregationLine\n";
    toSave += String.valueOf(l.getFirstX_Value()) + "\n" + String.valueOf(l.getFirstY_Value()) + "\n";
    toSave += String.valueOf(l.getSecondX_Value()) + "\n" + String.valueOf(l.getSecondY_Value()) + "\n";
    //f(l.getHead() != null)
    //  toSave +=
      //writer.write(toSave);
      addToFile(toSave);
  }

  public void visit(AssociationLine l){
    String toSave = "";
    toSave += "AssociationLine\n";
    toSave += String.valueOf(l.getFirstX_Value()) + "\n" + String.valueOf(l.getFirstY_Value()) + "\n";
    toSave += String.valueOf(l.getSecondX_Value()) + "\n" + String.valueOf(l.getSecondY_Value()) + "\n";
    //f(l.getHead() != null)
    //  toSave +=
      //writer.write(toSave);
      addToFile(toSave);
   }
  public void visit(CompostionLine l){
    String toSave = "";
    toSave += "CompostionLine\n";
    toSave += String.valueOf(l.getFirstX_Value()) + "\n" + String.valueOf(l.getFirstY_Value()) + "\n";
    toSave += String.valueOf(l.getSecondX_Value()) + "\n" + String.valueOf(l.getSecondY_Value()) + "\n";
    //f(l.getHead() != null)
    //  toSave +=
      //writer.write(toSave);
      addToFile(toSave);
    }
  public void visit(DependencyLine l){
    String toSave = "";
    toSave += "DependencyLine\n";
    toSave += String.valueOf(l.getFirstX_Value()) + "\n" + String.valueOf(l.getFirstY_Value()) + "\n";
    toSave += String.valueOf(l.getSecondX_Value()) + "\n" + String.valueOf(l.getSecondY_Value()) + "\n";
    //f(l.getHead() != null)
    //  toSave +=
      //writer.write(toSave);
      addToFile(toSave);
    }
  public void visit(ImplementationLine l){
    String toSave = "";
    toSave += "ImplementationLine\n";
    toSave += String.valueOf(l.getFirstX_Value()) + "\n" + String.valueOf(l.getFirstY_Value()) + "\n";
    toSave += String.valueOf(l.getSecondX_Value()) + "\n" + String.valueOf(l.getSecondY_Value()) + "\n";
    //f(l.getHead() != null)
    //  toSave +=
      //writer.write(toSave);
      addToFile(toSave);
    }
  public void visit(InheritanceLine l){
    String toSave = "";
    toSave += "InheritanceLine\n";
    toSave += String.valueOf(l.getFirstX_Value()) + "\n" + String.valueOf(l.getFirstY_Value()) + "\n";
    toSave += String.valueOf(l.getSecondX_Value()) + "\n" + String.valueOf(l.getSecondY_Value()) + "\n";
    //f(l.getHead() != null)
    //  toSave +=
      //writer.write(toSave);
      addToFile(toSave);
     }


    //public String getToSave(){
      //return toSave;
  //  }

    private void addToFile(String toAdd){
        try{
          BufferedWriter writer = new BufferedWriter(new FileWriter(new File(this.fileName), true));
          writer.write(toAdd);
          writer.close();
        }catch(IOException e){
          System.out.println("error saving");
          e.getStackTrace();
        }
    }


}
