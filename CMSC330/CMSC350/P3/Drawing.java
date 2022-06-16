import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.*;
import javax.swing.*;

public class Drawing extends JPanel {
  Shape currentShape;
  private static final int PASSING_SCORE = 4;
  boolean validShape = false;

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (validShape) {
      currentShape.draw(g);
    }

  }

  @Override
  public Dimension getPreferredSize() {
    Dimension defaultDimension = new Dimension(200,200);
    return defaultDimension;
  } 
  
  public void drawShape(Shape currentShape) throws OutsideBounds {
    int score = 0;

    if (currentShape.shapeBase.width <= getPreferredSize().width) {
      score += score;
    } else if (currentShape.shapeBase.height <= getPreferredSize().height) {
      score += score;
    } else if ((currentShape.shapeBase.x <= getPreferredSize().width - currentShape.shapeBase.width && currentShape.shapeBase.x >= 0)) {
      score += score;
    } else if ((currentShape.shapeBase.y <= getPreferredSize().height - currentShape.shapeBase.height && currentShape.shapeBase.y >= 0)) {
      score += score;
    }
    
    if (score != PASSING_SCORE) {
      throw new OutsideBounds("Does not fit");
    } else {
      validShape = true;
    }
  }
}