package app_model;

import app_model.lines.CompostionLine;

public class LineFactory{

  public LineFactory(){
  }

  public static Line createLine(){
    return new CompostionLine(0,0,100,100);
  }
}
