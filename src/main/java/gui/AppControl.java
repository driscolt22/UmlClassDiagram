package gui;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JOptionPane;

import app_model.AppModel;
import gui.AppDisplay;

public class AppControl
implements MouseListener, MouseMotionListener
{
    private AppModel app;
    private AppDisplay appDisplay;

    public AppControl(AppModel app, AppDisplay appDisplay)
    {
        this.app = app;
        this.appDisplay = appDisplay;

        appDisplay.addMouseListener(this);
        appDisplay.addMouseMotionListener(this);
    }

    public void mouseClicked(MouseEvent e)
    {
        //System.out.println(e);
        int x = e.getX();
        int y = e.getY();
    }
    public void mouseEntered(MouseEvent e)
    {
        //System.out.println(e);
    }
    public void mouseExited(MouseEvent e)
    {
        //System.out.println(e);
    }
    public void mousePressed(MouseEvent e)
    {
        //System.out.println(e);
    }

    public void mouseReleased(MouseEvent e)
    {
        //System.out.println(e);
    }

    public void mouseMoved(MouseEvent e){
        //System.out.println(e);

    }

    public void mouseDragged(MouseEvent e){
        //System.out.println(e);
    }
}
