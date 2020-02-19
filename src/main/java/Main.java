import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import gui.AppDisplay;
import app_model.AppModel;
import app_model.BlockFactory;
import app_model.Block;
import app_model.Line;
import app_model.LineFactory;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main{

    public Main() {

        JFrame mainFrame = new JFrame("Main app");
        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        AppModel app = new AppModel();

        AppDisplay mainDisplay = new AppDisplay(app);
        app.addListener(mainDisplay);

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

        app.createLine();
        app.createText();

        mainFrame.add(mainDisplay);
        mainFrame.pack();
        mainFrame.setVisible(true);
        mainFrame.setFocusable(true);
        mainFrame.requestFocusInWindow();
    }

    public static void main(String[] args) {
        Main ex = new Main();
    }
}
