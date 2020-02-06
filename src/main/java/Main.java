package PACKAGE_NAME;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class Main extends JFrame{

  public Main() {

    this.setVisible(true);
     setTitle("Simple example");
     setSize(300, 200);
     setLocationRelativeTo(null);
     setDefaultCloseOperation(EXIT_ON_CLOSE);
  }

  public static void main(String[] args) {
     Main ex = new Main();

  }


  }
