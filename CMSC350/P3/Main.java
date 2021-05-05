import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.*;
import javax.swing.*;
import java.util.*;

class Main {
  // components
  JFrame drawingFrame;
  // labels
  JLabel shapeTypeLbl, fillTypeLbl, colorLbl, widthLbl, heightLbl, xCoordLbl, yCoordLbl;
  // arrays for combo boxes
  String[] shapeTypeCBArray = {"Rectangle", "Oval"};
  String[] fillTypeCBArray = {"Hollow", "Solid"};
  String[] colorCBArray = {"Black", "Red", "Orange", "Yellow", "Green", "Blue", "Magenta"};
  //
  Color selectedColor;
  boolean setSolid;
  Shape newShape;
  // textfields
  JTextField widthTF, heightTF, xCoordTF, yCoordTF;

  JButton drawButton;

  int widthInput, heightInput, xCoordInput, yCoordInput;

  String shapeTypeSelected, fillTypeSelected;
  ArrayList <Shape>  shapesArray = new ArrayList<Shape>();

  public Main() {
    // create frame
    drawingFrame = new JFrame();
    // create labels
    shapeTypeLbl = new JLabel("Shape Type",JLabel.LEFT);
    fillTypeLbl = new JLabel("Fill Type",JLabel.LEFT);
    colorLbl = new JLabel("Color",JLabel.LEFT);
    widthLbl = new JLabel("Width",JLabel.LEFT);
    heightLbl = new JLabel("Height",JLabel.LEFT);
    xCoordLbl = new JLabel("x coordinate",JLabel.LEFT);
    yCoordLbl = new JLabel("y coordinate",JLabel.LEFT);
   
    // create combox boxes
    JComboBox shapeTypeCB = new JComboBox<>(shapeTypeCBArray);
    shapeTypeCB.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String selectedShapeTypeString = (String) shapeTypeCB.getSelectedItem();

        switch (selectedShapeTypeString) {
          case "Oval":
            shapeTypeSelected = "Oval";
          break;
          case "Rectangle":
            shapeTypeSelected = "Rectangle";
          break;
          default:
            shapeTypeSelected = "Rectangle";
          break;
        }
      }
    });

    JComboBox fillTypeCB = new JComboBox<>(fillTypeCBArray);
      fillTypeCB.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          String selectedShapeTypeString = (String) fillTypeCB.getSelectedItem();
          
          switch (selectedShapeTypeString) {
            case "Solid":
              fillTypeSelected = "Solid";
            break;
            case "Hollow":
              fillTypeSelected = "Hollow";
            break;
            default:
              fillTypeSelected = "Hollow";
            break;
          }
        }
      });
    
    JComboBox colorTypeCB = new JComboBox<>(colorCBArray);
    colorTypeCB.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String selectedColorString = (String)colorTypeCB.getSelectedItem();
        
        switch (selectedColorString) {
          case "Black":
            selectedColor = Color.BLACK;
          break;
          case "Red":
            selectedColor = Color.RED;
          break;
          case "Orange":
            selectedColor = Color.ORANGE;
          break;
          case "Yellow":
            selectedColor = Color.YELLOW;
          break;
          case "Green":
            selectedColor = Color.GREEN;
          break;
          case "Blue":
            selectedColor = Color.BLUE;
          break;
          case "Magenta":
            selectedColor = Color.MAGENTA;
          break;
          default:
            selectedColor = Color.BLACK;
          break;
        }
      }
    });
    
    // create textfields
    widthTF = new JTextField();
    widthTF.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String widthInputString = (String)widthTF.getText();
        try {
          widthInput = Integer.valueOf(widthInputString);
        } catch(Exception illegal) {
          JOptionPane.showMessageDialog(drawingFrame, "Enter an integer only");
        }
      }
    });
    heightTF = new JTextField();
    heightTF.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String heightInputString = (String) heightTF.getText();
        try {
          heightInput = Integer.valueOf(heightInputString);
        } catch(Exception illegal) {
          JOptionPane.showMessageDialog(drawingFrame, "Enter an integer only");
        }
      }
    });
    xCoordTF = new JTextField();
    xCoordTF.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String xCInputString = (String) xCoordTF.getText();
        try {
          xCoordInput = Integer.valueOf(xCInputString);
        } catch(Exception illegal) {
          JOptionPane.showMessageDialog(drawingFrame, "Enter an integer only");
        }
      }
    });
    yCoordTF = new JTextField();
    yCoordTF.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String yCInputString = (String)yCoordTF.getText();
        try {
          yCoordInput = Integer.valueOf(yCInputString);
        } catch(Exception illegal) {
          JOptionPane.showMessageDialog(drawingFrame, "Enter an integer only");
        }
      }
    });
   
    // left panel
    JPanel leftPanel = new JPanel(new GridLayout(7,2,10,10));
    leftPanel.add(shapeTypeLbl);
    leftPanel.add(shapeTypeCB);
    leftPanel.add(fillTypeLbl);
    leftPanel.add(fillTypeCB);
    leftPanel.add(colorLbl);
    leftPanel.add(colorTypeCB);
    leftPanel.add(widthLbl);
    leftPanel.add(widthTF);
    leftPanel.add(heightLbl);
    leftPanel.add(heightTF);
    leftPanel.add(xCoordLbl);
    leftPanel.add(xCoordTF);
    leftPanel.add(yCoordLbl);
    leftPanel.add(yCoordTF);
    // right panel
    JPanel rightPanel = new JPanel();
    rightPanel.setBorder(BorderFactory.createTitledBorder("Shape Drawing"));
    Drawing newDrawing = new Drawing();
    rightPanel.add(newDrawing);

    // button
    drawButton = new JButton("Draw");
    drawButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        Rectangle defaultShape = new Rectangle();defaultShape.width = widthInput;
        defaultShape.height = heightInput;
        defaultShape.x = xCoordInput;
        defaultShape.y = yCoordInput;

        if (fillTypeSelected == "Solid") {
          setSolid = true;
        } else {
          setSolid = false;
        }

        if (shapeTypeSelected == "Oval") {
          newShape = new Oval(defaultShape, selectedColor, setSolid);
          shapesArray.add(newShape);
        } else if (shapeTypeSelected == "Rectangle") {
          newShape = new Rectangular(defaultShape, selectedColor, setSolid);
          shapesArray.add(newShape);
        }
        try {
        newDrawing.drawShape(newShape);
        } catch(OutsideBounds invalidShape) {
          JOptionPane.showMessageDialog(drawingFrame, invalidShape);
        }
      }
    });
    
    // add panels
    drawingFrame.setLayout(new FlowLayout());
    drawingFrame.add(leftPanel);
    drawingFrame.add(rightPanel);
    drawingFrame.add(drawButton);
    
    // frame properties
    drawingFrame.setTitle("Geometric Drawing");
    drawingFrame.setSize(500,350);
    drawingFrame.setLocationRelativeTo(null); // centres to screen
    drawingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closes frame
    drawingFrame.setVisible(true); // show frame
  }

  public static void main(String[] args) {
    // create frame
    new Main();
    
  }
}