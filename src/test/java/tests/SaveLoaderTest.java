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
    a.addObj(l);
    //Saver saver = new Saver("savedLine.txt");
    //l.accept(saver);
    //AppModel a3 = a;
    //a3.save2("savedLine.bin");

    AppModel a2 = new AppModel();
    //a2 = AppModel.load2("savedLine.bin");
    System.out.println(a);
    //assertTrue(a3.equals(a2));
    a.save("savedLine.txt");
    //a.printFile("savedLine.txt");
    File file = new File("savedLine.txt");
    assertTrue(file.length() > 2);
    AppModel a4 = new AppModel();
    a4.load("savedLine.txt");
    System.out.println(a4);
    //assertTrue(a4.equals(a));
  }

}
