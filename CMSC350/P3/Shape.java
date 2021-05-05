import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.*;
import javax.swing.*;

abstract class Shape extends Rectangle {
  protected Color shapeColor;
  protected Rectangle shapeBase;
  private boolean isSolid;
  private static int noOfShapesCreated;

  Shape(Rectangle shapeBase, Color shapeColor, boolean isSolid) {
    this.shapeBase = shapeBase;
    this.shapeColor = shapeColor;
    this.isSolid = isSolid;

    noOfShapesCreated++;
  }
  //  An instance method named setColor that accepts the Graphics object as a parameter and sets the color for the next draw operation to the color of the current shape.
  
  public void setColor(Graphics g) {
  // set color
    g.setColor(shapeColor);
  }
  // An instance method named getSolid that returns whether the shape is solid or hollow.
  public boolean getSolid() {
    return isSolid;
  }
  // A class method named getNoOfShapes that returns the number of shapes created so far
  public int getNoOfShapes() {
    // return shapes int
    return noOfShapesCreated;
  }
  // An abstract method named draw that accepts a Graphics object as a parameter
  abstract void draw(Graphics g);
}