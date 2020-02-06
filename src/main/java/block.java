package PACKAGE_NAME;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class block extends JFrame{
  private int x;
  private int y;
  private int width;
  private int length;

  public block(int x, int y, int length, int width)
  {
    this.x = x;
    this.y = y;
    this.width = width;
    this.length = length;
  }

}
