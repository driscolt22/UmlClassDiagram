package commands;

import app_model.AppModel;
import javax.swing.JOptionPane;

public class ClearCommand implements Command{
    private AppModel appmodel;

    public ClearCommand(AppModel appmodel){
        this.appmodel = appmodel;
    }

    public void execute(){
        int input = JOptionPane.showConfirmDialog(null, "Are sure you want to Clear?","Confirm",JOptionPane.OK_CANCEL_OPTION);
        if(input == 0){
            appmodel.clear();
        }
    }
}
