import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import gui.AppDisplay;
import app_model.AppModel;
import app_model.BlockFactory;
import app_model.Block;
import app_model.Line;
import app_model.LineFactory;

public class Main extends JFrame{

    public Main() {

        JFrame mainFrame = new JFrame("Main app");
        AppModel app = new AppModel();

        AppDisplay mainDisplay = new AppDisplay(app);
        app.addListener(mainDisplay);

        Block b = BlockFactory.createBlock();
        b.setLocation(50,50);
        app.addObj(b);

        Line l = LineFactory.createLine();
        app.addObj(l);

        mainFrame.getContentPane().add(mainDisplay);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    public static void main(String[] args) {
        Main ex = new Main();
    }
}
