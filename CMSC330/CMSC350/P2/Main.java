import.util.ArrayList;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import java.io.*;

import java.util.*;

import javax.swing.border.Border; 
import javax.swing.BorderFactory;

class Main {
  List<Polynomial> polynomials = new ArrayList<Polynomial>();
  // JFrame
  JFrame frame;
  JButton openFileBtn;

  JTextArea output;
  
  private final static String newline = "\n";

  public main() {
    // setting up the frame
    frame = new JFrame();
    // adding a layout
    FlowLayout guiLayout = new FlowLayout(FlowLayout.CENTER);
    frame.setLayout(guiLayout);
    // adding a panel
    JPanel panel = new JPanel(new GridLayout(2,1,7,5));
    frame.add(panel);
    
    // set up textarea
    output = new JTextArea(10,20);
    JScrollPane scrollPane = new JScrollPane(output); 
    JTextArea.setEditable(false);
    output.setBorder(BorderFactory.createLineBorder(Color.RED););
    panel.add(output);

    // set up button
    openFileButton = new JButton("Open File");
    // action listener
    addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e) {
        polynomials = convertFile();
        try {
          for (String polynomial : polynomials ) {
            Polynomial newPolynomial = new Polymonial(polynomial);
            // do work 
            output.append(newPolynomial.toString() + newline);
          }
        } catch (InvalidPolynomialSyntax ex) {

        }
      }
    });

    // establish frame properties
    guiFrame.setTitle("Polynomials");
    guiFrame.setSize(350,350);
    guiFrame.setLocationRelativeTo(null);
    guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    guiFrame.setVisible(true);
  }
  
  public static void main(String[] args) {
    new Main();
  }

  public String convertFile() {
    return ;
  }

  public void deepAnalysis() {
    JFrame deepFrame = new JFrame();
    JPanel deepPanel = new JPanel();
    deepFrame.add(deepPanel);

    JTextArea = new JTextArea();
    
  }
}