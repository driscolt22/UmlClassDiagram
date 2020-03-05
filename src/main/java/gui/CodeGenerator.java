package gui;
import java.util.*;
import app_model.Visitor;
import app_model.Line;
import app_model.Block;
import app_model.DisplayText;
import app_model.lines.*;

import java.io.FileReader;
import java.io.IOException;

public class CodeGenerator implements Visitor{

  private String modelCode;
  private AppModel app;

  /**
  * creates DisplayObjects based on Strings that are read from a text file
  */
  public CodeGenerator(AppModel app){
    this.modelCode = "";
    this.app = app;
  }

  public void visit(Block b){
    this.modelCode += "public class " + b.getName + "\n";
    this.modelCode +=
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
