package app_model;

import app_model.lines.*;

public class LineFactory{

  /**
  * creates different types of lines 
  */
  public LineFactory(){
  }

  public static Line createLine(){
    return new CompostionLine(0,0,100,100);
  }

  public static Line createAggregationLine(){
    return new AggregationLine(0,0,100,100);
  }

  public static Line createAssociationLine(){
    return new AssociationLine(0,0,100,100);
  }

  public static Line createCompositionLine(){
    return new CompostionLine(0,0,100,100);
  }

  public static Line createDependencyLine(){
    return new DependencyLine(0,0,100,100);
  }

  public static Line createImplementationLine(){
    return new ImplementationLine(0,0,100,100);
  }

  public static Line createInheritanceLine(){
    return new InheritanceLine(0,0,100,100);
  }
}
