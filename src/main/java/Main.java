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
import java.awt.Toolkit;
import java.awt.Dimension;

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
        app.addListener(menuDisplay);
        menuDisplay.setDisplay(mainDisplay);
//


        mainFrame.add(mainDisplay);
        mainFrame.pack();
        //mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
        mainFrame.setFocusable(true);
        mainFrame.requestFocusInWindow();

//
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        menuFrame.setLocation(1000, 0);
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
