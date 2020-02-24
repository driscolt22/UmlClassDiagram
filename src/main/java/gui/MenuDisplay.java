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
import javax.swing.JTextArea;
import java.util.*;
import javax.swing.BoxLayout;
import java.awt.*;
import javax.swing.*;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;


import app_model.AppListener;
import app_model.AppModel;
import app_model.DisplayObject;
import app_model.BlockFactory;
import app_model.LineFactory;
import app_model.DisplayText;

public class MenuDisplay extends JComponent {
    public static int WIDTH = 200;
    public static int HEIGHT = 800;
    private AppModel app;
    private JScrollPane scrollPane;
    private JSplitPane splitPane;

    private JPanel buttonMenu;
    private JPanel selectedContents;


    public MenuDisplay(AppModel app)
    {
      this.splitPane = new JSplitPane();
      this.app = app;
      this.scrollPane = new JScrollPane();
      this.buttonMenu = new JPanel();

      //this.buttonMenu.setBackground(Color.black);
      //this.buttonMenu.setLayout(new GridLayout(11, 1));
      //addButtonsToMenu();
      this.selectedContents = new JPanel();
      //this.selectedContents.setBackground(Color.red);


      splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
      splitPane.setDividerLocation(400);
      splitPane.setTopComponent(this.buttonMenu);
      splitPane.setBottomComponent(this.selectedContents);

      this.buttonMenu.add(scrollPane);
      this.buttonMenu.setMaximumSize(new Dimension(300, 400));
      this.buttonMenu.setLayout(new GridLayout(11, 1));
      addButtonsToMenu();

      this.selectedContents.setLayout(new SpringLayout());
    }

    public JSplitPane getSplitPane(){
      return this.splitPane;
    }

    public JPanel getButtonMenu(){
      return this.buttonMenu;
    }

    public JPanel getSelectedContents(){
      return this.selectedContents;
    }

    public void blockButtonPressed(){
      this.app.addObj(BlockFactory.createBlock());
    }

    public void associationLineButtonPressed(){
      this.app.addObj(LineFactory.createLine());
    }
    public void inheritanceLineButtonPressed(){
      this.app.addObj(LineFactory.createLine());
    }
    public void implementationLineButtonPressed(){
      this.app.addObj(LineFactory.createLine());
    }
    public void dependencyLineButtonPressed(){
      this.app.addObj(LineFactory.createLine());
    }
    public void aggregationLineButtonPressed(){
      this.app.addObj(LineFactory.createLine());
    }
    public void compositionLineButtonPressed(){
      this.app.addObj(LineFactory.createLine());
    }
    public void textButtonPressed(){
      this.app.addObj(new DisplayText());
    }
    public void deleteSelectedPressed(){
      this.app.removeSelected();
    }
    public void saveButtonPressed(){
      //
    }

    public void loadButtonPressed(){
      //
    }

    private void addButtonsToMenu(){
      JButton blockButton = new JButton("New Class");
      blockButton.setPreferredSize(new Dimension(25, 100));
      this.buttonMenu.add(blockButton);

      JButton associationLineButton = new JButton("New Association Line");
      associationLineButton.setPreferredSize(new Dimension(25, 100));
      this.buttonMenu.add(associationLineButton);

      JButton inheritanceLineButton = new JButton("New Inheritance Line");
      inheritanceLineButton.setPreferredSize(new Dimension(25, 100));
      this.buttonMenu.add(inheritanceLineButton);

      JButton implementationLineButton = new JButton("New Implementation Line");
      implementationLineButton.setPreferredSize(new Dimension(25, 100));
      this.buttonMenu.add(implementationLineButton);

      JButton dependencyLineButton = new JButton("New Dependency Line");
      dependencyLineButton.setPreferredSize(new Dimension(25, 100));
      this.buttonMenu.add(implementationLineButton);

      JButton aggregationLineButton = new JButton("New Aggregation Line");
      aggregationLineButton.setPreferredSize(new Dimension(25, 100));
      this.buttonMenu.add(aggregationLineButton);

      JButton compositionLineButton = new JButton("New Compositon Line");
      compositionLineButton.setPreferredSize(new Dimension(25, 100));
      this.buttonMenu.add(compositionLineButton);

      JButton textButton = new JButton("New Text");
      textButton.setPreferredSize(new Dimension(25, 100));
      this.buttonMenu.add(textButton);

      JButton deleteSelectedButton = new JButton("Delete Selected");
      deleteSelectedButton.setPreferredSize(new Dimension(25, 100));
      this.buttonMenu.add(deleteSelectedButton);

      JButton saveButton = new JButton("Save File");
      saveButton.setPreferredSize(new Dimension(25, 100));
      this.buttonMenu.add(saveButton);

      JButton loadButton = new JButton("Load File");
      loadButton.setPreferredSize(new Dimension(25, 100));
      this.buttonMenu.add(loadButton);
    }


}
