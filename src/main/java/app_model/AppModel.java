package app_model;
import app_model.*;
import java.util.*;


public class AppModel{
  private ArrayList<DisplayObject> displayObjects;
  //private ArrayList<line> lines;
  private Vector<AppListener> listeners;

  private DisplayObject currentlySelected;
  private boolean selectHead;

  public AppModel(){
    displayObjects = new ArrayList<DisplayObject>(0);
    //lines = new ArrayList<line>(0);
    listeners = new Vector<AppListener>();
    currentlySelected = null;

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

  public void select(int x, int y){
      for(DisplayObject d: getDisplayObjects()){
          if(d.contains(x,y)){
              currentlySelected = d;
              if(currentlySelected instanceof Line){
                  Line l = (Line)currentlySelected;
                  selectHead = Math.hypot(x -l.getFirstX_Value(), y - l.getFirstY_Value()) <=
                            Math.hypot(x- l.getSecondX_Value(), y-l.getSecondY_Value());
              }
              System.out.println("Selected object");
              notifyListeners();
              return;
          }
      }
      System.out.println("Did not Select object");
      currentlySelected = null;
      notifyListeners();
      return;
  }

  public void select(DisplayObject d){
      currentlySelected = d;
  }

  public DisplayObject getSelected(){
      return currentlySelected;
  }

  public boolean isSelected(DisplayObject d){
      return d == currentlySelected;
  }

  public void moveSelected(int dx,int dy){
      if(currentlySelected instanceof Block){
          Block b = (Block) currentlySelected;
          b.setLocation(b.getX() + dx, b.getY() +dy);
      }else if(currentlySelected instanceof Line){
          Line l = (Line) currentlySelected;
          if(selectHead){
              l.setHead(l.getFirstX_Value() + dx, l.getFirstY_Value() + dy);
          }else{
              l.setTail(l.getSecondX_Value() + dx, l.getSecondY_Value() + dy);
          }
      }
      notifyListeners();
  }

}
