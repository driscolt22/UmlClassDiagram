package commands;

import app_model.AppModel;
import javax.swing.*;
import java.io.File;
import gui.CodeGenerator;
import app_model.DisplayObject;

public class GenCodeCommand implements Command{
    private AppModel appmodel;

    public GenCodeCommand(AppModel appmodel){
        this.appmodel = appmodel;
    }

    /**
     * asks the user to what folder they want to save to. then Generates Java code
     */
    public void execute(){
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
        if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            String folderPath = chooser.getSelectedFile().toString();
            CodeGenerator g = new CodeGenerator(appmodel, folderPath);
            for(DisplayObject d: appmodel.getDisplayObjects()){
                d.accept(g);
            }
            //System.out.println("File Path: " + folderPath);
        }
    }
}
