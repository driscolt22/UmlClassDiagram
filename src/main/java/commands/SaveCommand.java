package commands;

import app_model.AppModel;
import javax.swing.*;
import java.io.File;

public class SaveCommand implements Command{
    private AppModel appmodel;

    public SaveCommand(AppModel appmodel){
        this.appmodel = appmodel;
    }

    public void execute(){
        JFileChooser chooser = new JFileChooser();
        if(chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            appmodel.save2((String)selectedFile.getPath());
        }
    }
}
