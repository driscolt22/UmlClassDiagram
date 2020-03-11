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
import app_model.Visitor;

public class AppDisplay extends JComponent implements AppListener{
    private AppModel app;
    public static int WIDTH = 1000;
    public static int HEIGHT = 800;
    private AppControl controller;

    private Render render;

    public AppDisplay(AppModel app)
    {
        this.app = app;
        this.render = new Render();

        controller = new AppControl(app, this);

        setSize(new Dimension(WIDTH, HEIGHT));
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

    }

    /**
     * @return the AppControler for this display
     */
    public AppControl getController(){
        return controller;
    }
    
    /**
     * Repaints the whole display from the appmodel
     * @param g graphics, called by Swing
     */
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        render.setGraphics(g);
        render.paintBackground(HEIGHT, WIDTH);



        for(DisplayObject d: app.getDisplayObjects()){
            render.setSelectedColor(app.isSelected(d));
            d.accept(render);
        }
    }

    public void setRender(Render r){
        this.render = r;
    }

    /**
     * On update, just repaint the whole thing
     */
    public void update()
	{
		repaint();
        //export("output.png");
	}

    public void export(File f, String fileType){
        BufferedImage bi = new BufferedImage(this.getSize().width, this.getSize().height, BufferedImage.TYPE_INT_ARGB);
        Graphics g = bi.createGraphics();
        this.paint(g);  //this == JComponent
        g.dispose();
        System.out.println("saving file: " + f + " as: " + fileType);
        try{
            ImageIO.write(bi,fileType, f);
        }catch (Exception e) {
            System.out.println("error Exporting file: " + f);
        }
    }
}
