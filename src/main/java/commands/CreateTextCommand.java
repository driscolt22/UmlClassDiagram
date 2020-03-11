package commands;

import app_model.AppModel;
import app_model.DisplayText;

public class CreateTextCommand implements Command{
    private AppModel appmodel;

    public CreateTextCommand(AppModel appmodel){
        this.appmodel = appmodel;
    }

    /**
     * Adds a new line to appmodel
     */
    public void execute(){
        appmodel.addObj(new DisplayText());
    }
}
