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
  public void testSaveLine()throws IOException{
    Line l = LineFactory.createLine();
    Block b = BlockFactory.createBlock();
    b.setClassName("model");
    b.addInstanceVariable("length");
    b.addInstanceVariable("width");
    b.addMethod("get()");
    a.addObj(l);
    a.addObj(b);
    DisplayText t = new DisplayText();
    t.setLocation(100, 45);
    t.setText("hello");
    a.addObj(t);

    AppModel a2 = new AppModel();
    //System.out.println(a);
    a.save("savedLine.txt");
    File file = new File("savedLine.txt");
    assertTrue(file.length() > 2);
    AppModel a4 = new AppModel();
    a4.load("savedLine.txt");
    System.out.println(a4);
    assertEquals("saving an AppModel with a Block and Line",a.toString(),a4.toString());
  }

}
