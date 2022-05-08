import java.awt.*;
import java.util.Random;
import javax.swing.*;

public class CodeWindow extends JFrame {
  private int authCode;
  private JFrame frame;
  private JLabel label;

  public CodeWindow() {
    this.authCode = genCode(); // generate new auth code

    frame = new JFrame(); // new JFrame with auth code
    frame.setPreferredSize(new Dimension(100, 100));
    label = new JLabel(String.valueOf(this.getAuthCode())); // label for auth code

    frame.setLayout(new BorderLayout());
    label.setHorizontalAlignment(SwingConstants.CENTER);
    label.setVerticalAlignment(SwingConstants.CENTER);

    frame.add(label, BorderLayout.CENTER); // add auth code label
    frame.pack();
    frame.setVisible(false);
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null);
  }

  
  /** 
   * Returns auth code
   * @return int
   */
  public int getAuthCode() {
    return this.authCode;
  }

  
  /** 
   * Generates a random five digit code
   * @return int - auth code for multi-factor auth
   */
  public int genCode() {
    Random rand = new Random( System.currentTimeMillis() );
    return 10000 + rand.nextInt(5000);
  }

  public void hideWindow() {
    // hides frame
    frame.setVisible(false);
  }

  public void showWindow() {
    // shows frame
    frame.setVisible(true);
  }
}
