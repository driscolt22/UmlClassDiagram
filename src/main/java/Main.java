import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import gui.AppDisplay;
import app_model.AppModel;
import app_model.BlockFactory;
import app_model.Block;
import app_model.Line;
import app_model.LineFactory;
import gui.MenuDisplay;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.util.*;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main{

    public Main() {

        JFrame mainFrame = new JFrame("Main app");
        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        AppModel app = new AppModel();

        AppDisplay mainDisplay = new AppDisplay(app);
        app.addListener(mainDisplay);

//
        JFrame menuFrame = new JFrame("Menu app");
        menuFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        MenuDisplay menuDisplay = new MenuDisplay(app);
//

        Block b = BlockFactory.createBlock();
        b.setLocation(100,100);
        b.setClassName("Test Class");
        b.addInstanceVariable("var1");
        b.addInstanceVariable("var2");
        b.addInstanceVariable("var3");
        b.addMethod("method1");
        b.addMethod("method2");
        app.addObj(b);
        //app.select(b);

        Block c = BlockFactory.createBlock();
        c.setLocation(300,350);
        c.setClassName("Test Class2");
        app.addObj(c);

        Line l = new Line(200,100,0,0);
        app.addObj(l);

        l.setLine(c,b);

        mainFrame.add(mainDisplay);
        mainFrame.pack();
        mainFrame.setVisible(true);
        mainFrame.setFocusable(true);
        mainFrame.requestFocusInWindow();

//
        menuFrame.add(menuDisplay.getMenu());
        //menuFrame.add(menuDisplay.getBlockButton());
        //menuFrame.add(menuDisplay.getlineButton());
        //menuFrame.add(menuDisplay.getTextButton());
        //menuFrame.add(menuDisplay.getDeleteButton());
        menuFrame.pack();
        menuFrame.setVisible(true);
        menuFrame.requestFocusInWindow();
//
    }

    public static void main(String[] args) {
        Main ex = new Main();
    }
}
