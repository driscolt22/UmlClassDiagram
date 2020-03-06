package commands;

import app_model.AppModel;

public class DeleteCommand implements Command{
    private AppModel appmodel;

    public DeleteCommand(AppModel appmodel){
        this.appmodel = appmodel;
    }

    public void execute(){
        appmodel.removeSelected();
    }
}
