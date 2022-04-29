/*
 * Kyra Samuel
 * P1
 * CMSC405
 * 4/19/2022
 * 
 * Main.java
 */
package cmsc405;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.WindowConstants;

public class Main {
  public static void main(String[] args) {
    JFrame frame = new JFrame("Project 1"); 
    final Draw drawPanel = new Draw(); 

    frame.setLayout(new GridLayout(1, 1));
    frame.setContentPane(drawPanel); 
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); 
    frame.setResizable(false); 
    frame.pack(); 

    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    frame.setLocation( 
      (screen.width - frame.getWidth()) / 2,
      (screen.height - frame.getHeight()) / 2);

    final long startTime = System.currentTimeMillis();

    ActionListener listener = ae -> {
      if (drawPanel.getFrameNumber() > 4) 
        drawPanel.setFrameNumber(0);
      else 
        drawPanel.setFrameNumber(drawPanel.getFrameNumber() +1);
      
      drawPanel.setElaspedTime(System.currentTimeMillis() - startTime);
      drawPanel.repaint();
    };

    Timer drawTimer = new Timer(1600, listener);

    frame.setVisible(true);
    drawTimer.start();
  }
}