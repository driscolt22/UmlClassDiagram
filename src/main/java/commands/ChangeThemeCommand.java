package commands;

import gui.AppDisplay;
import javax.swing.JOptionPane;
import gui.Render;
import gui.ThemedRender;

public class ChangeThemeCommand implements Command{
    private AppDisplay ad;

    public ChangeThemeCommand(AppDisplay ad){
        this.ad = ad;
    }

    /**
     * Propmpots the user for a new theme, then changes it in AppDisplay
     */
    public void execute(){
        Object[] possibilities = {"Default", "DarkMode"};
        String s = (String)JOptionPane.showInputDialog(
                    null,
                    "What Theme",
                    "Pick a Theme",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    possibilities,
                    "Default");
        System.out.println(s);
        if(s != null){
            switch(s){
                case "Default":
                ad.setRender(new Render());
                break;
                case "DarkMode":
                ad.setRender(new ThemedRender());
                break;
            }
            ad.update();
        }
    }
}
