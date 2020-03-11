package gui;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;

import javax.swing.JOptionPane;

import app_model.AppModel;
import gui.AppDisplay;
import commands.*;

public class AppControl
implements MouseListener, MouseMotionListener, KeyListener
{
    private AppModel app;
    private AppDisplay appDisplay;
    private int pressX;
    private int pressY;

    private boolean ctrlPressed;

    /**
     * Initizes the mouse contorl for An appmodel, given the display
     * @param app        Appmodel to control
     * @param appDisplay display to attach to
     */
    public AppControl(AppModel app, AppDisplay appDisplay)
    {
        this.app = app;
        this.appDisplay = appDisplay;

        ctrlPressed = false;

        appDisplay.addMouseListener(this);
        appDisplay.addMouseMotionListener(this);
        //appDisplay.addKeyListener(this);

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
            Command c = new MoveCommand(app, dx, dy);
            c.execute();
            pressX = e.getX();
            pressY = e.getY();
        }
    }

    public void keyPressed(KeyEvent e) {
        //System.out.println(e);
        System.out.println(e.getModifiers());
        Command c = null;
        if(e.isControlDown()){
            int k = e.getKeyCode();
            if(k == KeyEvent.VK_S){
                c = new SaveCommand(app);
            }else if(k == KeyEvent.VK_A){
                c = new LoadCommand(app);
            }else if(k == KeyEvent.VK_B){
                c = new CreateBlockCommand(app);
            }else if(k == KeyEvent.VK_L){
                c = new CreateLineCommand(app);
            }else if(k == KeyEvent.VK_T){
                c = new CreateTextCommand(app);
            }else if(k == KeyEvent.VK_E){
                c = new ExportCommand(appDisplay);
            }
        }else{
            int k = e.getKeyCode();
            if(k == KeyEvent.VK_RIGHT){
                c = new MoveCommand(app, 10,0);
            }else if(k == KeyEvent.VK_LEFT){
                c = new MoveCommand(app, -10,0);
            }else if(k == KeyEvent.VK_UP){
                c = new MoveCommand(app, 0,-10);
            }else if(k == KeyEvent.VK_DOWN){
                c = new MoveCommand(app, 0,10);
            }else if(k == KeyEvent.VK_ESCAPE){
                app.select(-100,-100);
            }
        }
        if(c != null){
            c.execute();
        }
    }
    public void keyReleased(KeyEvent e) {
        //System.out.println(e);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
