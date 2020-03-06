package commands;

import app_model.AppModel;
import app_model.BlockFactory;

public class CreateBlockCommand implements Command{
    private AppModel appmodel;

    public CreateBlockCommand(AppModel appmodel){
        this.appmodel = appmodel;
    }

    public void execute(){
        appmodel.addObj(BlockFactory.createBlock());
    }
}
