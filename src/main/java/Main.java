import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import gui.AppDisplay;
import app_model.AppModel;
import app_model.BlockFactory;
import app_model.Block;
import app_model.Line;
import app_model.LineFactory;

public class Main{

    public Main() {

        JFrame mainFrame = new JFrame("Main app");
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

        Block c = BlockFactory.createBlock();
        c.setLocation(100,350);
        c.setClassName("Test Class2");
        app.addObj(c);


        Line l = new Line(200,100,0,0);
        app.addObj(l);

        l.setLine(c,b);

        mainFrame.getContentPane().add(mainDisplay);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    public static void main(String[] args) {
        Main ex = new Main();
    }
}
