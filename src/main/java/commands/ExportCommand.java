package commands;

import gui.AppDisplay;
import javax.swing.*;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.filechooser.*;

public class ExportCommand implements Command{
    private AppDisplay ad;

    public ExportCommand(AppDisplay ad){
        this.ad = ad;
    }

    public void execute(){
        JFileChooser chooser = new JFileChooser();
        for(String s: ImageIO.getReaderFileSuffixes()){
            chooser.setFileFilter(new FileNameExtensionFilter("." + s, s));
        }
        if(chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            FileNameExtensionFilter f = (FileNameExtensionFilter) chooser.getFileFilter();
            String type = f.getExtensions()[0];
            File file = chooser.getSelectedFile();
            if(!file.getName().endsWith("." + type)){
                 file = new File(file + "." + type);
            }
            System.out.println(file);
            ad.export(file, type);
        }
    }
}
