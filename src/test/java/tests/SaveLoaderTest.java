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
  public void testSaveLine(){
    Line l = LineFactory.createLine();
    a.addObj(l);
    Saver saver = new Saver("savedLine.txt");
    l.accept(saver);
    AppModel a2 = new AppModel();
    a2.load("savedLine.txt");
  }

}
