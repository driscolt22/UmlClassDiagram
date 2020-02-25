package app_model;
import app_model.*;
import java.util.*;
import java.io.Writer;
import java.io.FileWriter;
import java.io.BufferedReader;
import gui.Saver;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.lang.ClassNotFoundException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import gui.Loader;
import java.io.Serializable;
import java.io.BufferedWriter;
import java.io.File;


public class AppModel {
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
    select(o);
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


  public void save(String fileName)throws IOException{
    Iterable<DisplayObject> objects = getDisplayObjects();
    Saver saver = new Saver();
    //int count = 0;
    //FileUtils.write(new File(fileName), "");
    BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
    for(DisplayObject d: objects){
      //count++;
      d.accept(saver);
    }
    //System.out.println(String.valueOf(count));
    //System.out.println(saver.getToSave());
    writer.write(saver.getToSave());
    writer.close();
  }

  public void printFile(String fileName)throws IOException{
    BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
    String line = bufferedReader.readLine();
    while(line != null){
      System.out.println(line);
      line = bufferedReader.readLine();
    }
  }



  public void load(String fileName)throws IOException{
      clear();
      //FileReader reader = new FileReader(fileName);
          BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
          String line = bufferedReader.readLine();
          //System.out.println(line);
          while (line != null) {
              //System.out.println(line);
              if(line.equals("Line")){
                //Class c = Class.forName("app_model.lines."+line);
                String lineInfo = "";
                lineInfo += bufferedReader.readLine();
                for(int i = 0; i < 3; i++){
                lineInfo += "," + bufferedReader.readLine();
                }
                Line l = LineFactory.createLine();
                Loader loader = new Loader(lineInfo);
                l.accept(loader);
                addObj(l);
              }
              else if(line.equals("AggregationLine")){
                //Class c = Class.forName("app_model.lines."+line);
                String lineInfo = "";
                lineInfo += bufferedReader.readLine();
                for(int i = 0; i < 3; i++){
                lineInfo += "," + bufferedReader.readLine();
                }
                Line l = LineFactory.createAggregationLine();
                Loader loader = new Loader(lineInfo);
                l.accept(loader);
                addObj(l);
              }
              else if(line.equals("AssociationLine")){
                //Class c = Class.forName("app_model.lines."+line);
                String lineInfo = "";
                lineInfo += bufferedReader.readLine();
                for(int i = 0; i < 3; i++){
                lineInfo += "," + bufferedReader.readLine();
                }
                Line l = LineFactory.createAssociationLine();
                Loader loader = new Loader(lineInfo);
                l.accept(loader);
                addObj(l);
              }
              else if(line.equals("CompostionLine")){
                System.out.println("found");
                //Class c = Class.forName("app_model.lines."+line);
                String lineInfo = "";
                lineInfo += bufferedReader.readLine();
                for(int i = 0; i < 3; i++){
                lineInfo += "," + bufferedReader.readLine();
                }
                Line l = LineFactory.createCompositionLine();
                Loader loader = new Loader(lineInfo);
                l.accept(loader);
                addObj(l);
              }
              else if(line.equals("DependencyLine")){
                //Class c = Class.forName("app_model.lines."+line);
                String lineInfo = "";
                lineInfo += bufferedReader.readLine();
                for(int i = 0; i < 3; i++){
                lineInfo += "," + bufferedReader.readLine();
                }
                Line l = LineFactory.createDependencyLine();
                Loader loader = new Loader(lineInfo);
                l.accept(loader);
                addObj(l);
              }
              else if(line.equals("ImplementationLine")){
                //Class c = Class.forName("app_model.lines."+line);
                String lineInfo = "";
                lineInfo += bufferedReader.readLine();
                for(int i = 0; i < 3; i++){
                lineInfo += "," + bufferedReader.readLine();
                }
                Line l = LineFactory.createImplementationLine();
                Loader loader = new Loader(lineInfo);
                l.accept(loader);
                addObj(l);
              }
              else if(line.equals("InheritanceLine")){
                //Class c = Class.forName("app_model.lines."+line);
                String lineInfo = "";
                lineInfo += bufferedReader.readLine();
                for(int i = 0; i < 3; i++){
                lineInfo += "," + bufferedReader.readLine();
                }
                Line l = LineFactory.createInheritanceLine();
                Loader loader = new Loader(lineInfo);
                l.accept(loader);
                addObj(l);
              }
              else if(line.equals("Block")){
                String blockInfo = "";
                blockInfo += bufferedReader.readLine();
                for(int i = 0; i < 6; i++){
                blockInfo += "," + bufferedReader.readLine();
                }
                Block b = BlockFactory.createBlock();
                Loader loader = new Loader(blockInfo);
                b.accept(loader);
                addObj(b);
              }
              else if(line.equals("DisplayText")){
                //Class c = Class.forName("app_model.lines."+line);
                String textInfo = "";
                textInfo += bufferedReader.readLine();
                for(int i = 0; i < 2; i++){
                textInfo += "," + bufferedReader.readLine();
                }
                DisplayText t = new DisplayText();
                Loader loader = new Loader(textInfo);
                t.accept(loader);
                addObj(t);
              }
              line = bufferedReader.readLine();
              //System.out.println(line);
      }        // to add loading function
          bufferedReader.close();
          notifyListeners();
      }

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

  public int numObjects(){
    return displayObjects.size();
  }

  private ArrayList<DisplayObject> copyDisplayObjects(){
    ArrayList<DisplayObject> toReturn = new ArrayList<>(0);
    for(DisplayObject d: getDisplayObjects()){
      toReturn.add(d);
    }
    return toReturn;
  }

  private boolean hasEqualObject(ArrayList<DisplayObject> remainingObjects, DisplayObject toFind){
    for(DisplayObject d: remainingObjects){
      if(toFind.equals(d))
        return true;
    }
    return false;
  }

  private boolean hasSameObjects(AppModel other){
    ArrayList<DisplayObject> displayObjects = copyDisplayObjects();
    if(numObjects()==other.numObjects()){
      for(DisplayObject d: other.getDisplayObjects()){
        if(!hasEqualObject(displayObjects, d))
          return false;
        else
          displayObjects.remove(d);
      }
      return true;
    }
    return false;
  }

  public boolean equals(Object other){
    if(other instanceof AppModel){
      return hasSameObjects((AppModel)other);
    }
    else
      return false;
  }

  public String toString(){
    String toReturn = "";
    for(DisplayObject d: getDisplayObjects()){
      toReturn += d.toString();
    }
    return toReturn;
  }

  public void clear(){
      displayObjects = new ArrayList<DisplayObject>(0);
      //lines = new ArrayList<line>(0);
      currentlySelected = null;
      notifyListeners();
  }

}
