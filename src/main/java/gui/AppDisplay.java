package gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Color;

import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JToolBar;
import javax.swing.JButton;

import app_model.AppListener;
import app_model.AppModel;
import app_model.DisplayObject;

public class AppDisplay extends JComponent implements AppListener{
    private AppModel app;
    public static int WIDTH = 1000;
    public static int HEIGHT = 800;
    private AppControl controller;

    public AppDisplay(AppModel app)
    {
        this.app = app;

        controller = new AppControl(app, this);

        setSize(new Dimension(WIDTH, HEIGHT));
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    /**
     * Repaints the whole display from the appmodel
     * @param g graphics, called by Swing
     */
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Render render = new Render(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.white);
        g2.fillRect(0, 0, WIDTH, HEIGHT);



        for(DisplayObject d: app.getDisplayObjects()){
            if(app.isSelected(d)){
                g.setColor(Color.blue);
            }else{
                g.setColor(Color.black);
            }
            d.accept(render);
        }
    }

    /**
     * On update, just repaint the whole thing
     */
    public void update()
	{
		repaint();
	}

    public void export(String s){
        BufferedImage bi = new BufferedImage(this.getSize().width, this.getSize().height, BufferedImage.TYPE_INT_ARGB);
        Graphics g = bi.createGraphics();
        this.paint(g);  //this == JComponent
        g.dispose();
        try{
            ImageIO.write(bi,"PNG",new File(s));
        }catch (Exception e) {}
    }
}
