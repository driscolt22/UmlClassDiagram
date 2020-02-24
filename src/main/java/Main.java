import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import gui.AppDisplay;
import app_model.AppModel;
import app_model.BlockFactory;
import app_model.Block;
import app_model.Line;
import app_model.LineFactory;
import app_model.lines.*;
import gui.MenuDisplay;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.util.*;
import java.awt.Container;
import java.awt.BorderLayout;

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
        b.setClassName("Long Test Class AAAAAAAAAAAAAAAAAAAA");
        b.addInstanceVariable("var1");
        b.addInstanceVariable("var2");
        b.addInstanceVariable("var3");
        b.addMethod("method1");
        b.addMethod("method2");
        b.addMethod("method3");
        b.addMethod("method4");
        app.addObj(b);
        //app.select(b);

        Block c = BlockFactory.createBlock();
        c.setLocation(300,350);
        c.setClassName("Test Class2");
        app.addObj(c);

        app.addObj(new AggregationLine(300,220,100,100));
        app.addObj(new AssociationLine(300,240,100,100));
        app.addObj(new CompostionLine(300,260,100,100));
        app.addObj(new DependencyLine(300,280,100,100));
        app.addObj(new ImplementationLine(300,300,100,100));
        app.addObj(new InheritanceLine(300,320,100,100));

        app.createLine();
        app.createText();

        mainFrame.add(mainDisplay);
        mainFrame.pack();
        //mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
        mainFrame.setFocusable(true);
        mainFrame.requestFocusInWindow();

//
        menuFrame.add(menuDisplay.getSplitPane());
        menuFrame.pack();
        menuFrame.setSize(300, 800);
        menuFrame.setVisible(true);
        menuFrame.requestFocusInWindow();

        // menuFrame.setLocation((int)mainFrame.getLocation().getX() + AppDisplay.WIDTH,
        //                       (int)mainFrame.getLocation().getY());

        //mainDisplay.export("output.png");
//
    }

    public static void main(String[] args) {
        Main ex = new Main();
    }
}
