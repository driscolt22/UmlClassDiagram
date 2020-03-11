package app_model;
import app_model.*;
import app_model.lines.*;
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


public class AppModel implements Serializable{
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

  /**
   * saves the currently created file to the users machine
   */
  public void save(String fileName){
    Iterable<DisplayObject> objects = getDisplayObjects();
    Saver saver = new Saver(fileName);
      for(DisplayObject d: objects){
        d.accept(saver);
      }
  }

  /**
  * @param fileName: the given file path name
  * prints the contents of the given file
  */
  public void printFile(String fileName)throws IOException{
    BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
    String line = bufferedReader.readLine();
    while(line != null){
      System.out.println(line);
      line = bufferedReader.readLine();
    }
}


  /**
   * loads a previously created file
   */
  public void load(String fileName){
    try{
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
    }catch(IOException e){
      System.out.println("error loading");
    }
      }

      /**
      * @return the AppModel that is created from the serializable file
      */
      private static AppModel loader2(String fileName)throws FileNotFoundException, IOException, ClassNotFoundException{
          ObjectInputStream am = new ObjectInputStream(new FileInputStream(fileName));
          AppModel toReturn = (AppModel) am.readObject();
          am.close();
          return toReturn;
      }

      /**
      *loads an serializable appmodel file that was saved to a file to this AppModel
      */
      public void load2(String fileName){
        try{
          clear();
          AppModel toCopy = loader2(fileName);

          for(DisplayObject d: toCopy.getDisplayObjects()){
            this.addObj(d);
          }
          notifyListeners();
        } catch(FileNotFoundException e){
          System.out.println("error loading1");
          e.getStackTrace();
        } catch(IOException e){
          System.out.println("error loading2");
          e.getStackTrace();
        } catch(ClassNotFoundException e){
          System.out.println("error loading3");
          e.getStackTrace();
        }
      }

      public void save2(String fileName){
        try{
          ObjectOutputStream am = new ObjectOutputStream(new FileOutputStream(fileName));
          am.writeObject(this);
          am.close();
        } catch(FileNotFoundException e){
          e.getStackTrace();
        } catch(IOException e){
          e.getStackTrace();
        }
      }


    /**
      * @param x,y for selecting by a coordinate
      */
  public void select(int x, int y){
      updateLinePositions();
      SelectionVisitor s = new SelectionVisitor(x, y);
      for(DisplayObject d: getDisplayObjects()){
          d.accept(s);
      }

      //System.out.println("Did not Select object");
      currentlySelected = s.getSelected();
      selectHead = s.isHeadSelected();
      notifyListeners();
  }

  /**
   * @param d manullay selected the given DisplayObject
   */
  public void select(DisplayObject d){
      currentlySelected = d;
      notifyListeners();
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
      MoveVisitor mv = new MoveVisitor(getDisplayObjects(), selectHead, dx, dy);
      if(getSelected() != null) getSelected().accept(mv);
      //updateLinePositions();
      notifyListeners();
  }

  /**
   * Deletes the curretly selected DisplayObject from the appmodel
   * @return ture if the Display object was scuecfully deleted
   */
  public boolean removeSelected(){
      boolean ret = displayObjects.remove(currentlySelected);
      currentlySelected = null;
      notifyListeners();
      return ret;
  }

  private void updateLinePositions(){
      Visitor v = new Visitor(){
          public void visit(Block b){}
          public void visit(DisplayText t){}
          public void visit(AggregationLine l){l.updatePosition();}
          public void visit(AssociationLine l){l.updatePosition();}
          public void visit(CompostionLine l){l.updatePosition();}
          public void visit(DependencyLine l){l.updatePosition();}
          public void visit(ImplementationLine l){l.updatePosition();}
          public void visit(InheritanceLine l){l.updatePosition();}
      };
      for(DisplayObject d: displayObjects){
          d.accept(v);
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

  /**
  * @return the number of DisplayObjects in the AppModel
  */
  public int numObjects(){
    return displayObjects.size();
  }

  /**
  * @return creates an ArrayList copy of the DisplayObjects and returns it
  */
  private ArrayList<DisplayObject> copyDisplayObjects(){
    ArrayList<DisplayObject> toReturn = new ArrayList<>(0);
    for(DisplayObject d: getDisplayObjects()){
      toReturn.add(d);
    }
    return toReturn;
  }

  /**
  * @param remainingObjects: the AppModels display objects that still have not been found
  * @param toFind: the DisplayObject we are looking for
  * @return true if there exists an equal DisplayObject to toFind in remainingObjects and false otherwise
  */
  private boolean hasEqualObject(ArrayList<DisplayObject> remainingObjects, DisplayObject toFind){
    for(DisplayObject d: remainingObjects){
      if(toFind.equals(d))
        return true;
    }
    return false;
  }

  /**
  * @param other: the AppModel being compared to
  * @return: true if the model bing compared to has an equal set of displayObjects
  * and false if it does not
  * the sets are considered equal if they are the same size,
  * and hold equal objects regardless of order
  */
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

  /**
   * @param other comparison object
   * @return true/false depending on if the two objects are equal
   */
  public boolean equals(Object other){
    if(other instanceof AppModel){
      return hasSameObjects((AppModel)other);
    }
    else
      return false;
  }

  /**
  * @return String representation of an AppModel that lists the DisplayObjects as Strings
  */
  public String toString(){
    String toReturn = "";
    for(DisplayObject d: getDisplayObjects()){
      toReturn += d.toString() + "\n";
    }
    return toReturn;
  }

  /**
   * clears the app model, gets rid of of all display objects and currently selected
   */
  public void clear(){
      displayObjects = new ArrayList<DisplayObject>(0);
      //lines = new ArrayList<line>(0);
      currentlySelected = null;
      notifyListeners();
  }

}
