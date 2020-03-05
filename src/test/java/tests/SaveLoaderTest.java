package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.io.IOException;
import org.junit.Test;
import org.junit.Ignore;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import java.io.File;

import app_model.*;
import gui.*;


public class SaveLoaderTest{
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
  public void testSaveLoadLine()throws IOException{
    Line l = LineFactory.createLine();
    Block b = BlockFactory.createBlock();
    b.setClassName("model");
    b.addInstanceVariable("length");
    b.addInstanceVariable("width");
    b.addMethod("get()");
    b.setLocation(215, 150);
    a.addObj(l);
    a.addObj(b);
    DisplayText t = new DisplayText();
    t.setLocation(100, 45);
    t.setText("hello");
    a.addObj(t);
    Line l2 = LineFactory.createInheritanceLine();
    a.addObj(l2);

    AppModel a2 = new AppModel();
    //System.out.println(a);
    a.save("savedLine.txt");
    File file = new File("savedLine.txt");
    assertTrue(file.length() > 2);
    AppModel a4 = new AppModel();
    a4.load("savedLine.txt");
    //System.out.println(a4);
    System.out.println(a);
    System.out.println();
    System.out.println(a4);
    assertEquals("saving an AppModel with a Block and Line",a.toString(),a4.toString());
  }

  @Test
  public void testSaveLoad2(){
    Line l = LineFactory.createLine();
    Block b = BlockFactory.createBlock();
    b.setClassName("model1");
    b.addInstanceVariable("length");
    b.addInstanceVariable("width");
    b.addMethod("get()");
    b.setLocation(215, 150);
    l.setTail(100, 50);
    a.addObj(l);
    a.addObj(b);
    DisplayText t = new DisplayText();
    t.setLocation(100, 45);
    t.setText("hello");
    a.addObj(t);
    Line l2 = LineFactory.createInheritanceLine();
    a.addObj(l2);
    a.save2("savedModel.bin");

    AppModel a2 = new AppModel();
    a2.load2("savedModel.bin");
    System.out.println(a);
    System.out.println();
    System.out.println(a2);
    assertEquals("saving an AppModel with a Block and Line",a.toString(),a2.toString());
  }

}
