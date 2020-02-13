package app_model;
import app_model.*;
import java.util.*;
import java.io.Writer;
import java.io.FileWriter;


public class AppModel{
  private ArrayList<DisplayObject> displayObjects;
  //private ArrayList<line> lines;
  private Vector<AppListener> listeners;

  public AppModel(){
    displayObjects = new ArrayList<DisplayObject>(0);
    //lines = new ArrayList<line>(0);
    listeners = new Vector<AppListener>();

  }

  public void addObj(DisplayObject o){
    displayObjects.add(o);
    notifyListeners();
  }

  public Iterable<DisplayObject> getDisplayObjects(){
      return displayObjects;
  }

  public boolean containsObject(DisplayObject b){
    return displayObjects.contains(b);
  }

  public void addListener(AppListener l)
  {
      listeners.add(l);
  }

  public void removeListener(AppListener l)
  {
      listeners.remove(l);
  }

  private void notifyListeners()
  {
      for (AppListener l : listeners) {
          l.update();
      }
  }

//  private String displayObjectToString(DisplayObject d){

//  }

  public void saveLoader(){

  }

}
