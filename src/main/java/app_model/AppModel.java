package app_model;
import app_model.*;
import java.util.ArrayList;
import java.util.Vector;


public class appModel{
  private ArrayList<block> blocks;
  //private ArrayList<line> lines;
  private Vector<appListener> listeners;

  public appModel(){
    blocks = new ArrayList<block>(0);
    //lines = new ArrayList<line>(0);
    listeners = new Vector<appListener>();

  }

  public void addBlock(){
    block b = blockFactory();
    blocks.add(b);
    notifyListeners();
  }

  public void addClassToBlock(block b, String className){
    if(containsBlock(b)){
      b.setClassName(className);
      notifyListeners();
    }
    else
      throw new IllegalArgumentException();
  }

  public boolean containsBlock(block b){
    return blocks.contains(b);
  }

  public void addMethodToBlock(block b, String methods){
    if(containsBlock(b)){
      b.addMethod(methods);
      notifyListeners();
    }
    else
      throw new IllegalArgumentException();
  }

  public void addVariableToBlock(block b, String instanceVariable){
    if(containsBlock(b)){
      b.addInstanceVariable(instanceVariable);
      notifyListeners();
    }
    else
      throw new IllegalArgumentException();
  }

  public void newBlockSize(block b, int length, int width){
    if(containsBlock(b)){
      b.setLength(length);
      b.setWidth(width);
      notifyListeners();
    }
    else
      throw new IllegalArgumentException();
  }

  public void newLocation(block b, int x, int y){

    if(containsBlock(b)){
    b.setLocation(x,y);
      notifyListeners();
    }
    else
      throw new IllegalArgumentException();
  }



  public void addListener(appListener l)
  {
      listeners.add(l);
  }

  public void removeListener(appListener l)
  {
      listeners.remove(l);
  }

  private void notifyListeners()
  {
      for (appListener l : listeners) {
          l.update();
      }
  }

}
