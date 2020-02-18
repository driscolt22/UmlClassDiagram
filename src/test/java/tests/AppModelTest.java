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

import app_model.AppModel;
import app_model.Block;
import app_model.Line;

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
        Block b = new Block();
        a.addObj(b);
        assertTrue("Adding a block say there is a block in the model", a.containsObject(b));
    }

    @Test
    public void moveblock(){
        Block b = new Block();
        a.addObj(b);
        a.select(b);
        a.moveSelected(100,200);
        assertEquals("Moving a block changes its X value", 100, b.getX());
        assertEquals("Moving a block changes its X value", 200, b.getY());
    }

    @Test
    public void connectLine(){
        Block b = new Block();
        b.setLocation(0,300);
        Block d = new Block();
        a.addObj(b);
        a.addObj(d);
        Line l = new Line(b, d);
        assertEquals("connected line has right X1 cord", 50, l.getFirstX_Value());
        assertEquals("connected line has right X2 cord", 50, l.getSecondX_Value());
        assertEquals("connected line has right Y1 cord", 300, l.getFirstY_Value());
        assertEquals("connected line has right Y2 cord", 100, l.getSecondY_Value());
    }

    @Test
    public void moveLine(){
        Block b = new Block();
        b.setLocation(0,300);
        Block d = new Block();
        a.addObj(b);
        a.addObj(d);
        Line l = new Line(b, d);
        a.addObj(l);
        a.select(b);
        a.moveSelected(50,0);
        assertEquals("connected line has right X1 cord", 100, l.getFirstX_Value());
        assertEquals("connected line has right X2 cord", 50, l.getSecondX_Value());
        assertEquals("connected line has right Y1 cord", 300, l.getFirstY_Value());
        assertEquals("connected line has right Y2 cord", 100, l.getSecondY_Value());
    }


}