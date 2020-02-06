package gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

import app_model.AppListener;
import app_model.AppModel;

public class AppDisplay extends JComponent implements AppListener{
    private AppModel app;
    private static int WIDTH = 600;
    private static int HEIGHT = 500;

    public AppDisplay(AppModel app)
    {
        this.app = app;

        setSize(new Dimension(WIDTH, HEIGHT));
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    public void update()
	{
		repaint();
	}
}
