package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

import app_model.AppModel;
import app_model.Block;
import app_model.Line;
import app_model.LineFactory;
import app_model.BlockFactory;
import gui.CodeGenerator;

import java.io.IOException;
import java.io.FileNotFoundException;


@RunWith(JUnit4.class)
public class AppModelTest{
    private AppModel a;

    @Before
    public void setUp(){
        a = new AppModel();
    }

    @After
    public void tearDown(){
        a = null;
    }

    @Test
    public void addBox(){
        Block b = BlockFactory.createBlock();
        a.addObj(b);
        assertTrue("Adding a block say there is a block in the model", a.containsObject(b));
    }

    @Test
    public void moveblock(){
        Block b = BlockFactory.createBlock();
        a.addObj(b);
        a.select(b);
        a.moveSelected(100,200);
        assertEquals("Moving a block changes its X value", 100, b.getX());
        assertEquals("Moving a block changes its X value", 200, b.getY());
    }

    @Test
    public void connectLine(){
        Block b = BlockFactory.createBlock();
        b.setLocation(0,300);
        Block d = new Block();
        a.addObj(b);
        a.addObj(d);
        Line l = LineFactory.createLine();
        l.connectHead(b);
        l.connectTail(d);
        l.updatePosition();
        l.setLine(b,d);
        assertEquals("connected line has right X1 cord", 50, l.getFirstX_Value());
        assertEquals("connected line has right X2 cord", 50, l.getSecondX_Value());
        assertEquals("connected line has right Y1 cord", 300, l.getFirstY_Value());
        assertEquals("connected line has right Y2 cord", 100, l.getSecondY_Value());
    }

    @Test
    public void moveLine(){
        Block b = BlockFactory.createBlock();
        b.setLocation(0,300);
        Block d = BlockFactory.createBlock();
        a.addObj(b);
        a.addObj(d);
        Line l = LineFactory.createLine();
        l.connectHead(b);
        l.connectTail(d);
        l.updatePosition();
        l.setLine(b,d);
        a.addObj(l);
        a.select(b);
        a.moveSelected(50,0);
        assertEquals("connected line has right X1 cord", 100, l.getFirstX_Value());
        assertEquals("connected line has right X2 cord", 50, l.getSecondX_Value());
        assertEquals("connected line has right Y1 cord", 300, l.getFirstY_Value());
        assertEquals("connected line has right Y2 cord", 100, l.getSecondY_Value());
    }

    @Test
    public void moveBlock(){
        Block b = new Block();
        b.setLocation(300,300);
        a.addObj(b);
        Line l = LineFactory.createLine();
        a.addObj(l);
        l.connectHead(b);
        l.setHead(0,0);
        assertFalse("Line is no longer connected to the block", l.pointOneIsConnected(b));
    }

    @Test
    public void testEquals(){
      Block b = BlockFactory.createBlock();
      a.addObj(b);
      AppModel a2 = new AppModel();
      a2.addObj(b);
      assertTrue("two AppModels holding the same block are equal", a.equals(a2));
      Line l = LineFactory.createCompositionLine();
      Line l2 = LineFactory.createCompositionLine();
      a2.addObj(l2);
      a.addObj(l);
      assertEquals("two AppModels holding lines that are equal are equal", a, a2);
      Block b2 = BlockFactory.createBlock();
      Block b3 = BlockFactory.createBlock();
      a.addObj(b2);
      a2.addObj(b3);
      assertEquals("two AppModels holding lines that are equal are equal", a, a2);
    }

    @Test
    public void testCodeGenerator(){
      Block b = BlockFactory.createBlock();
      b.setClassName("Example");
      b.addMethod("Method");
      a.addObj(b);
      String path = System.getProperty("user.dir");
      CodeGenerator gen = new CodeGenerator(a, path);
      b.accept(gen);
      //gen.writeStringToFile();
      try{
          BufferedReader objReader = new BufferedReader(new FileReader(path + "/Example.java"));

          assertEquals("first line is correct", objReader.readLine(), "public class Example {");
          assertEquals("second line is correct", objReader.readLine(), "   public void Method(){}");
          assertEquals("thrid line is correct", objReader.readLine(), "}");
      }catch(Exception e){
          assertTrue("File not found", false);
      }
      }

      @Test
      public void testCodeGenerator2(){
        Block b = BlockFactory.createBlock();
        b.setClassName("Example");
        b.addInstanceVariable("Object Variable");
        b.addMethod("String Method int param1 String param2");
        a.addObj(b);
        String path = System.getProperty("user.dir");
        CodeGenerator gen = new CodeGenerator(a, path);
        b.accept(gen);
        //gen.writeStringToFile();
        try{
            BufferedReader objReader = new BufferedReader(new FileReader(path + "/Example.java"));

            assertEquals("first line is correct", objReader.readLine(), "public class Example {");
            assertEquals("second line is correct", objReader.readLine(), "   private Object Variable;");
            assertEquals("third line is correct", objReader.readLine(), "   public String Method(int param1, String param2){}");
            assertEquals("fourth line is correct", objReader.readLine(), "}");
        }catch(Exception e){
            assertTrue("File not found", false);
        }
        }

}
