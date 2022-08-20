/*
 * Kyra Samuel
 * CMSC451 
 * BenchmarkFrame.java
 * Description: Creates a JFrame that displays Benchmark Results
 */
package P2;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.Border;

public class BenchmarkFrame extends JFrame {
  public BenchmarkFrame(List<String[]> dataList, String title) {
    super(title);
    setPreferredSize(new Dimension(600, 400));
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setLayout(new GridLayout(dataList.size() + 1, 5, 2, 2));

    //add columns
    add(new JLabel("Size", SwingConstants.CENTER));
    add(new JLabel("Avg Count", SwingConstants.CENTER));
    add(new JLabel("Coef Count", SwingConstants.CENTER));
    add(new JLabel("Avg Time", SwingConstants.CENTER));
    add(new JLabel("Coef Time", SwingConstants.CENTER));

    Border border = BorderFactory.createLineBorder(Color.black);

    // add values to frame
    for (int i = 0; i < dataList.size(); i++)
      for (String value: dataList.get(i)) {
        JLabel valueLbl = new JLabel(value, SwingConstants.RIGHT);
        valueLbl.setBorder(border);

        add(valueLbl);
      }
    
    pack();
    setLocationRelativeTo(null);
    setVisible(true);
  }
}