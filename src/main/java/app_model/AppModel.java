package app_model;
import app_model.*;
import java.util.*;
import java.io.Writer;
import java.io.FileWriter;
import java.io.BufferedReader;


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



  public void save(String fileName){
    Iterable<DisplayObject> objects = getDisplayObjects();
    Saver saver = new Saver(fileName);
    for(DisplayObject d: objects){
      d.accept(saver);
    }
  }

  public void load(String fileName){
    try {
      FileReader reader = new FileReader(fileName);
          BufferedReader bufferedReader = new BufferedReader(reader);
          String line;
          while ((line = bufferedReader.readLine()) != null) {
              
          }
          reader.close();
      } catch (IOException e) {
          e.getStackTrace();
  }

}
