import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.*;
import javax.swing.*;

public class Rectangular extends Shape {
  public Rectangular(Rectangle shapeBase, Color shapeColor, boolean isSolid) {
    super(shapeBase, shapeColor, isSolid);
  }

  @Override
  public void draw(Graphics g) {
   if (getSolid()) {
      g.fillRect(super.shapeBase.x, super.shapeBase.y, super.shapeBase.width, super.shapeBase.height);
    } else {
      g.drawRect(super.shapeBase.x, super.shapeBase.y, super.shapeBase.width, super.shapeBase.height);
    }
  }
}