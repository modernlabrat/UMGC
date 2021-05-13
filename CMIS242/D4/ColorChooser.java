import java.awt.event.*;    
import java.awt.*;    
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
  
public class ColorChooser {    
    JButton colorBtn;    
    JFrame frame;
    Color currentColor;

    public ColorChooser() {
        frame = new JFrame();
        frame.setLayout(new GridLayout(2, 1));
        currentColor = Color.WHITE;
        
        JPanel colorPanel = new JPanel();
        Border raisedetched = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
        colorPanel.setBorder(raisedetched);
        colorPanel.setSize(new Dimension(350, 210));
        colorPanel.setPreferredSize(new Dimension(350, 210));

        JPanel colorPanelHolder = new JPanel();
        colorPanelHolder.add(colorPanel);

        colorBtn = new JButton("Color");
        colorBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Color color = JColorChooser.showDialog(frame, "Select A Color", currentColor);
                currentColor = color;
                colorPanel.setBackground(color);
            }
        });

        frame.add(colorPanelHolder);
        frame.add(colorBtn);
        
        frame.setSize(new Dimension(500, 500));
        frame.setPreferredSize(new Dimension(500, 500));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    } 
}  