package commands;

import app_model.AppModel;
import app_model.LineFactory;
import javax.swing.JOptionPane;

public class CreateLineCommand implements Command{
    private AppModel appmodel;

    public CreateLineCommand(AppModel appmodel){
        this.appmodel = appmodel;
    }

    public void execute(){
        Object[] possibilities = {"AggregationLine", "AssociationLine", "CompostionLine",
            "DependencyLine","ImplementationLine","InheritanceLine"};
        String s = (String)JOptionPane.showInputDialog(
                    null,
                    "What kind of Line?",
                    "Pick a Line",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    possibilities,
                    "asdfs");
        System.out.println(s);
        if(s != null){
            switch(s){
                case "AggregationLine":
                    appmodel.addObj(LineFactory.createAggregationLine());
                    break;
                case "AssociationLine":
                    appmodel.addObj(LineFactory.createAssociationLine());
                    break;
                case "CompostionLine":
                    appmodel.addObj(LineFactory.createCompositionLine());
                    break;
                case "DependencyLine":
                    appmodel.addObj(LineFactory.createDependencyLine());
                    break;
                case "ImplementationLine":
                    appmodel.addObj(LineFactory.createImplementationLine());
                    break;
                case "InheritanceLine":
                    appmodel.addObj(LineFactory.createInheritanceLine());
                    break;
            }
        }
    }
}
