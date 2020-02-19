package app_model;
import app_model.*;
import java.util.*;
import java.io.Writer;
import java.io.FileWriter;
import java.io.BufferedReader;
import gui.Saver;
import java.io.FileReader;
import java.io.IOException;
import gui.Loader;


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
              if(line.equals("Line")){
                String lineInfo = "";
                for(int i = 0; i < 4; i++){
                lineInfo += bufferedReader.readLine();
                }
                Line l = LineFactory.createLine();
                Loader loader = new Loader(lineInfo);
                l.accept(loader);
                addObj(l);
              }
              else if(line.equals("Block")){
                String blockInfo = "";
                for(int i = 0; i < 7; i++){
                blockInfo += bufferedReader.readLine();
                }
                Block b = BlockFactory.createBlock();
                Loader loader = new Loader(blockInfo);
                b.accept(loader);
                addObj(b);
              }
              // to add loading function
          }
          reader.close();
      } catch (IOException e) {
          e.getStackTrace();
        }
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
          for(DisplayObject c: getDisplayObjects()){
              if(c instanceof Line){
                  ((Line)c).updatePosition();
              }
          }
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
  

}
