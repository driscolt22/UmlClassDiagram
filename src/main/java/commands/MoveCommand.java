package commands;

import app_model.AppModel;

public class MoveCommand implements Command{
    private AppModel appmodel;
    private int dx;
    private int dy;

    public MoveCommand(AppModel appmodel, int dx, int dy){
        this.appmodel = appmodel;
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * moves the selected by dx,dy
     */
    public void execute(){
        appmodel.moveSelected(dx,dy);
    }
}
