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
import java.awt.event.*;
import javax.swing.event.*;
import java.io.IOException;
import java.io.File;
import java.io.Serializable;

import app_model.*;
import gui.*;
import commands.*;

public class MenuDisplay extends JComponent implements ActionListener, AppListener, DocumentListener, Serializable  {
    public static int WIDTH = 200;
    public static int HEIGHT = 800;
    private AppModel app;
    private JScrollPane scrollPane;
    private JSplitPane splitPane;

    private JPanel buttonMenu;
    private JPanel selectedContents;

    private JPanel boxPanel;
    private JPanel textPanel;

    private JTextField classText;
    private JTextField textField;

    private JTextArea instanceVariableText;
    private JTextArea classMethodText;

    private DisplayObject prevSelected;

    private AppDisplay display1;


    public MenuDisplay(AppModel app)
    {
      this.splitPane = new JSplitPane();
      this.app = app;
      this.scrollPane = new JScrollPane();
      this.buttonMenu = new JPanel();

      this.selectedContents = new JPanel();

      splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
      splitPane.setDividerLocation(400);
      splitPane.setTopComponent(this.buttonMenu);
      splitPane.setBottomComponent(this.selectedContents);

      initButtonMenu(this.buttonMenu);

      prevSelected = null;

      boxPanel = new JPanel();
      textPanel = new JPanel();

      boxPanel.setLayout(new BoxLayout(boxPanel, BoxLayout.Y_AXIS));
      boxPanel.setMaximumSize(new Dimension(300, 400));
      Block block = (Block)app.getSelected();
      JLabel classLabel = new JLabel("Class name: ");
      classText = new JTextField(20);
      //classText.setSize(new Dimension(300,50));
      classText.setActionCommand("setClass");
      classText.addActionListener(this);
      //classLabel.set
      JLabel instanceVariables = new JLabel("Instance Variables: ");
      instanceVariableText = new JTextArea();
      instanceVariableText.getDocument().addDocumentListener(this);
      JScrollPane instanceVariableScrollPane = new JScrollPane(instanceVariableText);
      //areaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
      instanceVariableScrollPane.setPreferredSize(new Dimension(300, 150));
      JLabel classMethods = new JLabel("Class methods: ");
      classMethodText = new JTextArea();
      classMethodText.getDocument().addDocumentListener(this);
      JScrollPane classMethodScrollPane = new JScrollPane(classMethodText);
      //areaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
      classMethodScrollPane.setPreferredSize(new Dimension(300, 150));

      boxPanel.add(classLabel);
      boxPanel.add(classText);
      boxPanel.add(instanceVariables);
      boxPanel.add(instanceVariableScrollPane);
      boxPanel.add(classMethods);
      boxPanel.add(classMethodScrollPane);
      boxPanel.setVisible(false);

      textPanel.setLayout(new GridLayout(2, 1));
      textPanel.setMaximumSize(new Dimension(300, 400));
      JLabel textLabel = new JLabel("Text: ");
      textField = new JTextField(50);
      textField.setActionCommand("setText");
      textField.addActionListener(this);
      // JButton submitButton = new JButton("Submit Changes");
      // submitButton.setActionCommand("submitText");
      // submitButton.addActionListener(this);
      textPanel.add(textLabel);
      textPanel.add(textField);
      //textPanel.add(submitButton);
      textPanel.setVisible(false);




      //splitPane.setBottomComponent(textPanel);

    }

    public void setDisplay(AppDisplay ad){
        this.display1 = ad;
    }

    public JSplitPane getSplitPane(){
      return this.splitPane;
    }

    private void initButtonMenu(JPanel panel){
      panel.setMaximumSize(new Dimension(300, 400));
      panel.setLayout(new GridLayout(10, 1));
      panel.setBackground(Color.black);
      addButtonsToMenu();
    }

    public void actionPerformed(ActionEvent e){
        Command c = null;
      if(e.getActionCommand().equals("block")){
          c = new CreateBlockCommand(app);
      }else if(e.getActionCommand().equals("line")){
          c = new CreateLineCommand(app);
      }else if(e.getActionCommand().equals("text")){
          c = new CreateTextCommand(app);
      }else if(e.getActionCommand().equals("delete")){
          c = new DeleteCommand(app);
      }else if(e.getActionCommand().equals("save")){
          c = new SaveCommand(app);
      }else if(e.getActionCommand().equals("load")){
          c = new LoadCommand(app);
      }else if(e.getActionCommand().equals("clear")){
          c = new ClearCommand(app);
      }else if(e.getActionCommand().equals("genCode")){
          c = new GenCodeCommand(app);
      }else if(e.getActionCommand().equals("theme")){
          c = new ChangeThemeCommand(display1);
        }else if(e.getActionCommand().equals("setClass")){
            ((Block)app.getSelected()).setClassName(classText.getText());
            app.select(app.getSelected());
            //submitClassButtonPressed();
        }else if(e.getActionCommand().equals("setText")){
            if(!textField.getText().equals("")){
                ((DisplayText)app.getSelected()).setText(textField.getText());
                app.select(app.getSelected());
            }
        }else if(e.getActionCommand().equals("export")){
            c = new ExportCommand(display1);
        }
        if(c != null){
            c.execute();
        }
    }

    public JPanel getButtonMenu(){
      return this.buttonMenu;
    }
    public JPanel getSelectedContents(){
      return this.selectedContents;
    }
    // public void blockButtonPressed(){
    //   this.app.addObj(BlockFactory.createBlock());
    // }
    // public void associationLineButtonPressed(){
    //   this.app.addObj(LineFactory.createAssociationLine());
    // }
    // public void inheritanceLineButtonPressed(){
    //   this.app.addObj(LineFactory.createInheritanceLine());
    // }
    // public void implementationLineButtonPressed(){
    //   this.app.addObj(LineFactory.createImplementationLine());
    // }
    // public void dependencyLineButtonPressed(){
    //   this.app.addObj(LineFactory.createDependencyLine());
    // }
    // public void aggregationLineButtonPressed(){
    //   this.app.addObj(LineFactory.createAggregationLine());
    // }
    // public void compositionLineButtonPressed(){
    //   this.app.addObj(LineFactory.createCompositionLine());
    // }
    // public void textButtonPressed(){
    //   this.app.addObj(new DisplayText());
    // }
    // public void deleteSelectedPressed(){
    //   this.app.removeSelected();
    // }
    // public void submitClassButtonPressed(){
    //   Block curretlySelected = (Block)this.app.getSelected();
    //   //currentlySelected.setClassName(classText.getText());
    //   //currentlySelected.addInstanceVariable(instanceVariableText.getText());
    //   //currentlySelected.addMethod(classMethodText.getText());
    // }
    //
    // public void saveButtonPressed(){
    //     JFileChooser chooser = new JFileChooser();
    //     if(chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
    //         File selectedFile = chooser.getSelectedFile();
    //         AppModel a = copyDisplayObjects();
    //         a.save2((String)selectedFile.getPath());
    //     }
    // }
    //
    // private void setAppModel(AppModel a){
    //   this.app = a;
    // }
    //
    // private AppModel copyDisplayObjects(){
    //   AppModel toReturn = new AppModel();
    //   for(DisplayObject d: this.app.getDisplayObjects()){
    //     toReturn.addObj(d);
    //   }
    //   return toReturn;
    // }
    //
    // public void loadButtonPressed(){
    //     JFileChooser chooser = new JFileChooser();
    //     if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
    //         File selectedFile = chooser.getSelectedFile();
    //         this.app.load2((String)selectedFile.getPath());
    //         //System.out.println(this.app);
    //     }
    // }

    private void addButtonsToMenu(){
      JButton blockButton = new JButton("New Class");
      blockButton.setPreferredSize(new Dimension(25, 100));
      blockButton.setActionCommand("block");
      blockButton.addActionListener(this);
      this.buttonMenu.add(blockButton);

      JButton lineButton = new JButton("New Line");
      lineButton.setPreferredSize(new Dimension(25, 100));
      lineButton.setActionCommand("line");
      lineButton.addActionListener(this);

      this.buttonMenu.add(lineButton);

      JButton textButton = new JButton("New Text");
      textButton.setPreferredSize(new Dimension(25, 100));
      textButton.setActionCommand("text");
      textButton.addActionListener(this);
      this.buttonMenu.add(textButton);

      JButton deleteSelectedButton = new JButton("Delete Selected");
      deleteSelectedButton.setPreferredSize(new Dimension(25, 100));
      deleteSelectedButton.setActionCommand("delete");
      deleteSelectedButton.addActionListener(this);
      this.buttonMenu.add(deleteSelectedButton);

      JButton saveButton = new JButton("Save File");
      saveButton.setPreferredSize(new Dimension(25, 100));
      saveButton.setActionCommand("save");
      saveButton.addActionListener(this);
      this.buttonMenu.add(saveButton);

      JButton loadButton = new JButton("Load File");
      loadButton.setPreferredSize(new Dimension(25, 100));
      loadButton.setActionCommand("load");
      loadButton.addActionListener(this);
      this.buttonMenu.add(loadButton);

      JButton exportButton = new JButton("Export");
      exportButton.setPreferredSize(new Dimension(25, 100));
      exportButton.setActionCommand("export");
      exportButton.addActionListener(this);
      this.buttonMenu.add(exportButton);

      JButton themeButton = new JButton("Change Theme");
      themeButton.setPreferredSize(new Dimension(25, 100));
      themeButton.setActionCommand("theme");
      themeButton.addActionListener(this);
      this.buttonMenu.add(themeButton);

      JButton clearButton = new JButton("Clear");
      clearButton.setPreferredSize(new Dimension(25, 100));
      clearButton.setActionCommand("clear");
      clearButton.addActionListener(this);
      this.buttonMenu.add(clearButton);

      JButton codeGenButton = new JButton("Generate Code");
      codeGenButton.setPreferredSize(new Dimension(25, 100));
      codeGenButton.setActionCommand("genCode");
      codeGenButton.addActionListener(this);
      this.buttonMenu.add(codeGenButton);
    }

    public void insertUpdate(DocumentEvent e) {
        //System.out.println(e.getDocument());
        updateBlock(e);
    }
    public void removeUpdate(DocumentEvent e) {
        //System.out.println(e.getDocument());
        updateBlock(e);
    }
    public void changedUpdate(DocumentEvent e) {
        //System.out.println("changedUpdate");
    }

    private void updateBlock(DocumentEvent e){
        if(app.getSelected() == prevSelected){
            Block b = (Block)app.getSelected();
            if(e.getDocument() == classMethodText.getDocument())
                b.setMethods(classMethodText.getText());
            else if(e.getDocument() == instanceVariableText.getDocument()){
                b.setInstanceVariables(instanceVariableText.getText());
            }
            app.select(app.getSelected());
        }

    }

    /**
     * On update, just update the visual
     */
    public void update()
    {
        if(app.getSelected() != prevSelected){
            prevSelected = app.getSelected();
            if(app.getSelected() instanceof Block){
                boxPanel.setVisible(true);
                textPanel.setVisible(false);
                Block b = (Block)app.getSelected();
                classText.setText(b.getName());

                splitPane.setBottomComponent(boxPanel);
                instanceVariableText.setText(b.convertInstanceVariables(b.getInstanceVariables()));
                classMethodText.setText(b.convertMethods(b.getMethods()));

            }else if(app.getSelected() instanceof DisplayText){
                textPanel.setVisible(true);
                boxPanel.setVisible(false);
                textField.setText(((DisplayText)app.getSelected()).getText());
                splitPane.setBottomComponent(textPanel);

            }else{
                textPanel.setVisible(false);
                boxPanel.setVisible(false);
            }
        }
    }
}
