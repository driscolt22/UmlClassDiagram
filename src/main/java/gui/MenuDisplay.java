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


import app_model.AppListener;
import app_model.AppModel;
import app_model.DisplayObject;
import app_model.Block;
import app_model.BlockFactory;
import app_model.LineFactory;
import app_model.DisplayText;

public class MenuDisplay extends JComponent implements ActionListener, AppListener, DocumentListener  {
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
      JTextArea instanceVariableText = new JTextArea("test1");
      instanceVariableText.getDocument().addDocumentListener(this);
      JScrollPane instanceVariableScrollPane = new JScrollPane(instanceVariableText);
      //areaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
      instanceVariableScrollPane.setPreferredSize(new Dimension(300, 150));
      JLabel classMethods = new JLabel("Class methods: ");
      JTextArea classMethodText = new JTextArea();
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

    public JSplitPane getSplitPane(){
      return this.splitPane;
    }

    private void initButtonMenu(JPanel panel){
      panel.setMaximumSize(new Dimension(300, 400));
      panel.setLayout(new GridLayout(11, 1));
      panel.setBackground(Color.black);
      addButtonsToMenu();
    }

    public void actionPerformed(ActionEvent e){
      if(e.getActionCommand().equals("block")){
        blockButtonPressed();
      }else if(e.getActionCommand().equals("association")){
        associationLineButtonPressed();
      }else if(e.getActionCommand().equals("inheritance")){
        inheritanceLineButtonPressed();
      }else if(e.getActionCommand().equals("implementation")){
        implementationLineButtonPressed();
      }else if(e.getActionCommand().equals("dependency")){
        dependencyLineButtonPressed();
      }else if(e.getActionCommand().equals("aggregation")){
        aggregationLineButtonPressed();
      }else if(e.getActionCommand().equals("composition")){
        compositionLineButtonPressed();
      }else if(e.getActionCommand().equals("text")){
        textButtonPressed();
      }else if(e.getActionCommand().equals("delete")){
        deleteSelectedPressed();
      }else if(e.getActionCommand().equals("save")){
        saveButtonPressed();
      }else if(e.getActionCommand().equals("load")){
        loadButtonPressed();
        }else if(e.getActionCommand().equals("setClass")){
            ((Block)app.getSelected()).setClassName(classText.getText());
            app.select(app.getSelected());
        //submitClassButtonPressed();
    }else if(e.getActionCommand().equals("setText")){
        ((DisplayText)app.getSelected()).setText(textField.getText());
        app.select(app.getSelected());
    }
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
      this.app.addObj(LineFactory.createAssociationLine());
    }
    public void inheritanceLineButtonPressed(){
      this.app.addObj(LineFactory.createInheritanceLine());
    }
    public void implementationLineButtonPressed(){
      this.app.addObj(LineFactory.createImplementationLine());
    }
    public void dependencyLineButtonPressed(){
      this.app.addObj(LineFactory.createDependencyLine());
    }
    public void aggregationLineButtonPressed(){
      this.app.addObj(LineFactory.createAggregationLine());
    }
    public void compositionLineButtonPressed(){
      this.app.addObj(LineFactory.createCompositionLine());
    }
    public void textButtonPressed(){
      this.app.addObj(new DisplayText());
    }
    public void deleteSelectedPressed(){
      this.app.removeSelected();
    }
    public void submitClassButtonPressed(){
      Block curretlySelected = (Block)this.app.getSelected();
      //currentlySelected.setClassName(classText.getText());
      //currentlySelected.addInstanceVariable(instanceVariableText.getText());
      //currentlySelected.addMethod(classMethodText.getText());
    }

    public void saveButtonPressed(){
        JFileChooser chooser = new JFileChooser();
        if(chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            try{
                this.app.save((String)selectedFile.getPath());
            }catch(IOException e){
                System.out.print("Problem saving");
            }
        }
    }

    public void loadButtonPressed(){
        JFileChooser chooser = new JFileChooser();
        if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            try{
                this.app.load((String)selectedFile.getPath());
            }catch(IOException e){
                System.out.print("Problem loading");
            }
        }

    }

    private void addButtonsToMenu(){
      JButton blockButton = new JButton("New Class");
      blockButton.setPreferredSize(new Dimension(25, 100));
      blockButton.setActionCommand("block");
      blockButton.addActionListener(this);
      this.buttonMenu.add(blockButton);

      JButton associationLineButton = new JButton("New Association Line");
      associationLineButton.setPreferredSize(new Dimension(25, 100));
      associationLineButton.setActionCommand("association");
      associationLineButton.addActionListener(this);

      this.buttonMenu.add(associationLineButton);

      JButton inheritanceLineButton = new JButton("New Inheritance Line");
      inheritanceLineButton.setPreferredSize(new Dimension(25, 100));
      inheritanceLineButton.setActionCommand("inheritance");
      inheritanceLineButton.addActionListener(this);
      this.buttonMenu.add(inheritanceLineButton);

      JButton implementationLineButton = new JButton("New Implementation Line");
      implementationLineButton.setPreferredSize(new Dimension(25, 100));
      implementationLineButton.setActionCommand("implementation");
      implementationLineButton.addActionListener(this);
      this.buttonMenu.add(implementationLineButton);

      JButton dependencyLineButton = new JButton("New Dependency Line");
      dependencyLineButton.setPreferredSize(new Dimension(25, 100));
      dependencyLineButton.setActionCommand("dependency");
      dependencyLineButton.addActionListener(this);
      this.buttonMenu.add(dependencyLineButton);

      JButton aggregationLineButton = new JButton("New Aggregation Line");
      aggregationLineButton.setPreferredSize(new Dimension(25, 100));
      aggregationLineButton.setActionCommand("aggregation");
      aggregationLineButton.addActionListener(this);
      this.buttonMenu.add(aggregationLineButton);

      JButton compositionLineButton = new JButton("New Compositon Line");
      compositionLineButton.setPreferredSize(new Dimension(25, 100));
      compositionLineButton.setActionCommand("composition");
      compositionLineButton.addActionListener(this);
      this.buttonMenu.add(compositionLineButton);

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
    }

    public void insertUpdate(DocumentEvent e) {
        //System.out.println(e.getDocument());
        updateBlock();
    }
    public void removeUpdate(DocumentEvent e) {
        //System.out.println(e.getDocument());
        updateBlock();
    }
    public void changedUpdate(DocumentEvent e) {
        //System.out.println("changedUpdate");
    }

    private void updateBlock(){
        Block b = app.getSelected();
        
    }

    /**
     * On update, just update the visual
     */
    public void update()
    {
        if(app.getSelected() instanceof Block){
            boxPanel.setVisible(true);
            textPanel.setVisible(false);
            classText.setText(((Block)app.getSelected()).getName());
            splitPane.setBottomComponent(boxPanel);

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
