package gui;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;

import app_model.AppModel;
import gui.AppDisplay;

public class AppControl
implements MouseListener, MouseMotionListener, KeyListener
{
    private AppModel app;
    private AppDisplay appDisplay;
    private int pressX;
    private int pressY;

    /**
     * Initizes the mouse contorl for An appmodel, given the display
     * @param app        Appmodel to control
     * @param appDisplay display to attach to
     */
    public AppControl(AppModel app, AppDisplay appDisplay)
    {
        this.app = app;
        this.appDisplay = appDisplay;

        appDisplay.addMouseListener(this);
        appDisplay.addMouseMotionListener(this);

    }

    /**
     * Called on mouse clicked, used for selecting a Displayobject
     * @param e MouseEvent
     */
    public void mouseClicked(MouseEvent e)
    {
        //System.out.println(e);
        int x = e.getX();
        int y = e.getY();
        app.select(x,y);
    }

    public void mouseEntered(MouseEvent e)
    {
        //System.out.println(e);
    }
    public void mouseExited(MouseEvent e)
    {
        //System.out.println(e);
    }

    /**
     * mouse pressed in order to drag
     * @param e MouseEvent
     */
    public void mousePressed(MouseEvent e)
    {
        pressX = e.getX();
        pressY = e.getY();
        //System.out.println(e);
    }

    public void mouseReleased(MouseEvent e){
        //System.out.println(e);
    }

    public void mouseMoved(MouseEvent e){
        //System.out.println(e);

    }

    /**
     * drages the selected compent aroud if there is one selected
     * @param e MouseEvent
     */
    public void mouseDragged(MouseEvent e){
        //System.out.println(e);
        if(e.getX() > 0 && e.getY() > 0 &&
            e.getX() < AppDisplay.WIDTH && e.getY() < AppDisplay.HEIGHT){
            int dx = e.getX() - pressX;
            int dy = e.getY() - pressY;
            app.moveSelected(dx, dy);
            pressX = e.getX();
            pressY = e.getY();
        }
    }

    public void keyPressed(KeyEvent e) {
        System.out.println(e);
    }
    public void keyReleased(KeyEvent e) {
        System.out.println(e);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println(e);
    }
}
