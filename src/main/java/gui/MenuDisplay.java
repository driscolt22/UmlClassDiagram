package gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Color;

import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.util.*;
import javax.swing.BoxLayout;
import java.awt.*;
import javax.swing.*;

import app_model.AppListener;
import app_model.AppModel;
import app_model.DisplayObject;
import app_model.BlockFactory;
import app_model.LineFactory;
import app_model.DisplayText;

public class MenuDisplay extends JComponent {
    public static int WIDTH = 350;
    public static int HEIGHT = 800;

    private JButton blockButton;
    private JButton lineButton;
    private JButton deleteButton;
    private JButton textButton;
    private AppModel app;
    private JPanel menu;

    public MenuDisplay(AppModel app)
    {
      this.app = app;
      setSize(new Dimension(WIDTH, HEIGHT));
      setPreferredSize(new Dimension(WIDTH, HEIGHT));

      menu = new JPanel();
      menu.setBackground(Color.black);
      //menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));
      menu.setLayout( new GridLayout(4, 1));


      this.blockButton = new JButton("Block");
      this.lineButton = new JButton("Line");
      this.textButton = new JButton("Text");
      this.deleteButton = new JButton("Delete");

      this.blockButton.setPreferredSize(new Dimension(200, 200));
      this.lineButton.setPreferredSize(new Dimension(200, 200));
      this.textButton.setPreferredSize(new Dimension(200, 200));
      this.deleteButton.setPreferredSize(new Dimension(200, 200));
      
      menu.add(this.blockButton);
      menu.add(this.lineButton);
      menu.add(this.textButton);
      menu.add(this.deleteButton);

    }

    public JPanel getMenu(){
      return this.menu;
    }

    public JButton getBlockButton(){
      return this.blockButton;
    }

    public JButton getlineButton(){
      return this.lineButton;
    }

    public JButton getTextButton(){
      return this.textButton;
    }

    public JButton getDeleteButton(){
      return this.deleteButton;
    }

    public void setBlockButton(){
      this.blockButton.setBounds(175, 100, 100, 100);
      this.blockButton.setVisible(true);
    }

    public void setLineButton(){
      this.lineButton.setBounds(175, 300, 100, 100);
      this.lineButton.setVisible(true);
    }

    public void setTextButton(){
      this.textButton.setBounds(175, 500, 100, 100);
      this.textButton.setVisible(true);
    }

    public void setDeleteButton(){
      this.deleteButton.setBounds(175, 700, 100, 100);
      this.deleteButton.setVisible(true);
    }

    public void blockButtonPressed(){
      this.app.addObj(BlockFactory.createBlock());
    }

    public void lineButtonPressed(){
      this.app.addObj(LineFactory.createLine());
    }

    public void textButtonPressed(){
      this.app.addObj(new DisplayText());
    }

    public void deleteSelectedPressed(){
      this.app.removeSelected();
    }
}
