package app_model;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.util.*;


public class Line{
  private int x1;
  private int y1;
  private int x2;
  private int y2;


  public Line(int x1, int y1, int x2, int y2)
  {
    this.x1 = 0;
    this.y1 = 0;
    this.x2 = 0;
    this.y2 = 0;
  }

  public int getFirstX_Value(){
    return this.x1;
  }

  public int getFirstY_Value(){
    return this.y1;
  }

  public int getSecondX_Value(){
    return this.x2;
  }

  public int getSecondY_Value(){
    return this.y2;
  }

  public int setFirstX_Value(int value){
    return this.x1 = value;
  }

  public int setFirstY_Value(int value){
    return this.y1 = value;
  }

  public int setSecondX_Value(int value){
    return this.x2 = value;
  }

  public int setSecondY_Value(int value){
    return this.y2 = value;
  }

}
