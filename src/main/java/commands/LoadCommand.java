package commands;

import app_model.AppModel;
import javax.swing.*;
import java.io.File;

public class LoadCommand implements Command{
    private AppModel appmodel;

    public LoadCommand(AppModel appmodel){
        this.appmodel = appmodel;
    }

    /**
     * prompts the user to load a file
     */
    public void execute(){
        JFileChooser chooser = new JFileChooser();
        if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            appmodel.load2((String)selectedFile.getPath());
            //System.out.println(this.app);
        }
    }
}
