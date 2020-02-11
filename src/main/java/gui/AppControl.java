package gui;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import app_model.AppModel;
import gui.AppDisplay;

public class AppControl
        implements MouseListener
{
    private AppModel app;
    private AppDisplay appDisplay;

    public AppControl(AppModel app, AppDisplay appDisplay)
    {
        this.app = app;
        this.appDisplay = appDisplay;

        appDisplay.addMouseListener(this);
    }

    public void mouseClicked(MouseEvent e)
    {
        int x = e.getX();
        int y = e.getY();
    }
    public void mouseEntered(MouseEvent e)
    {

    }
    public void mouseExited(MouseEvent e)
    {

    }
    public void mousePressed(MouseEvent e)
    {

    }

    public void mouseReleased(MouseEvent e)
    {

    }
}
