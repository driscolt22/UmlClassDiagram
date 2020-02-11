package app_model;

public class LineFactory{

  public LineFactory(){
  }

  public static Line createLine(){
    return new Line(0,0,100,100);
  }
}
