import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import gui.AppDisplay;
import app_model.AppModel;

public class Main extends JFrame{

    public Main() {

        JFrame mainFrame = new JFrame("Main app");
        AppModel app = new AppModel();

        AppDisplay mainDisplay = new AppDisplay(app);
        app.addListener(mainDisplay);

        mainFrame.getContentPane().add(mainDisplay);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    public static void main(String[] args) {
        Main ex = new Main();
    }
}
