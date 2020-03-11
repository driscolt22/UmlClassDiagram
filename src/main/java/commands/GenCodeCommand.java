package commands;

import app_model.AppModel;
import javax.swing.*;
import java.io.File;

public class GenCodeCommand implements Command{
    private AppModel appmodel;

    public GenCodeCommand(AppModel appmodel){
        this.appmodel = appmodel;
    }

    public void execute(){
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
        if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            String folderPath = chooser.getSelectedFile().toString();
            System.out.println("File Path: " + folderPath);
        }
    }
}
