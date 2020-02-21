package app_model;
import app_model.*;
import java.util.*;
import java.io.Writer;
import java.io.FileWriter;
import java.io.BufferedReader;
import gui.Saver;
import java.io.FileReader;
import java.io.IOException;


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

  /**
   * @param o Display Object(Line, Block etc) to add to the appmodel;
   */
  public void addObj(DisplayObject o){
    displayObjects.add(o);
    notifyListeners();
  }

  /**
   * @return Iterable of all displayobjects in the model;
   */
  public Iterable<DisplayObject> getDisplayObjects(){
      return displayObjects;
  }

  /**
   * @param  b Disply object(Line, Block, etc)
   * @return   true if b is an object in the model, false otherwise
   */
  public boolean containsObject(DisplayObject b){
    return displayObjects.contains(b);
  }

  /**
   * @param l Lister to add to get update about thsi appmodel
   */
  public void addListener(AppListener l)
  {
      listeners.add(l);
  }

  /**
   * @param l removes AppListener from list of listeners
   */
  public void removeListener(AppListener l)
  {
      listeners.remove(l);
  }

  /**
   * updates all listerners assiged to this
   */
  private void notifyListeners()
  {
      for (AppListener l : listeners) {
          l.update();
      }
  }


  /**
   * @param fileName Saves this to given file, using Saver
   */
  public void save(String fileName){
    Iterable<DisplayObject> objects = getDisplayObjects();
    Saver saver = new Saver(fileName);
    for(DisplayObject d: objects){
      d.accept(saver);
    }
  }

  /**
   * @param fileName Loads form file, populating the appmodel with objectes
   */
  public void load(String fileName){
    try {
      FileReader reader = new FileReader(fileName);
          BufferedReader bufferedReader = new BufferedReader(reader);
          String line;
          while ((line = bufferedReader.readLine()) != null) {
              // to add loading function
          }
          reader.close();
      } catch (IOException e) {
          e.getStackTrace();
        }
      }

  /**
   * Selected teh display object at the given x,y cordints
   * @param x x postion of the selction
   * @param y y postion of the selction
   */
  public void select(int x, int y){
       updateLinePositions();
      for(DisplayObject d: getDisplayObjects()){
          if(d.contains(x,y)){
              currentlySelected = d;
              if(currentlySelected instanceof Line){
                  Line l = (Line)currentlySelected;
                  selectHead = Math.hypot(x -l.getFirstX_Value(), y - l.getFirstY_Value()) <=
                            Math.hypot(x- l.getSecondX_Value(), y-l.getSecondY_Value());
              }
              //System.out.println("Selected object");
              notifyListeners();
              return;
          }
      }
      //System.out.println("Did not Select object");
      currentlySelected = null;
      notifyListeners();
      return;
  }

  /**
   * @param d manullay selected the given DisplayObject
   */
  public void select(DisplayObject d){
      currentlySelected = d;
  }

  /**
   * @return currently Selected DisplayObject
   */
  public DisplayObject getSelected(){
      return currentlySelected;
  }

  /**
   * @param  d DisplayObject
   * @return   if d is the currently Selected DisplayObject
   */
  public boolean isSelected(DisplayObject d){
      return d == currentlySelected;
  }

  /**
   * Moves the currently Selected by dx dy
   * @param dx [description]
   * @param dy [description]
   */
  public void moveSelected(int dx,int dy){
      if(currentlySelected instanceof Block){
          Block b = (Block) currentlySelected;
          b.setLocation(b.getX() + dx, b.getY() +dy);
          for(DisplayObject c: getDisplayObjects()){
              if(c instanceof Line){
                  ((Line)c).updatePosition();
              }
          }
      }else if(currentlySelected instanceof DisplayText){
          DisplayText t = (DisplayText) currentlySelected;
          t.setLocation(t.getX() + dx, t.getY() +dy);
      }else if(currentlySelected instanceof Line){
          Line l = (Line) currentlySelected;
          if(selectHead){
              l.setHead(l.getFirstX_Value() + dx, l.getFirstY_Value() + dy);
              l.connectHead(null);
          }else{
              l.setTail(l.getSecondX_Value() + dx, l.getSecondY_Value() + dy);
              l.connectTail(null);
          }
          //reatach to the blocks
          for(DisplayObject c: getDisplayObjects()){

              if(c instanceof Block){
                  //System.out.println("checking a block?");
                  l.connectToBlock((Block) c);
              }
          }
      }
      notifyListeners();
  }

  /**
   * Deletes the curretly selected DisplayObject from the appmodel
   * @return ture if the Display object was scuecfully deleted
   */
  public boolean removeSelected(){
      boolean ret = displayObjects.remove(currentlySelected);
      notifyListeners();
      return ret;
  }

  private void updateLinePositions(){
      for(DisplayObject d: displayObjects){
          if(d instanceof Line){
              ((Line) d).updatePosition();
          }
      }
  }

  /**
   * Creates and seletecs a new block
   */
  public void createBlock(){
      Block b = new Block();
      addObj(b);
      select(b);
      notifyListeners();
  }

  /**
   * Creates and selectes a new Line
   */
  public void createLine(){
      Line l = LineFactory.createLine();
      addObj(l);
      select(l);
      notifyListeners();
  }

  /**
   * Creates and selectes a new floating textbox
   */
  public void createText(){
      DisplayText t = new DisplayText();
      addObj(t);
      select(t);
      notifyListeners();
  }

}
