/*
 * Kyra Samuel
 * P1
 * CMSC405
 * 4/19/2022
 * 
 * Draw.java
 */
package cmsc405;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Draw extends JPanel {
  private int frameNumber;
  private long elapsedTime;
  private float pixelSize;


  int translateX;
  int translateY;
  double rotation;
  double scaleX;
  double scaleY;

  ImageTemplate myImages;
  BufferedImage smileyImage;
  BufferedImage cherryImage;
  BufferedImage chickImage;


  public Draw() {
    this.setPreferredSize(new Dimension(800, 400));
    this.translateX = 0;
    this.translateY = 0;
    this.rotation = 0.0;
    this.scaleX = 1.0;
    this.scaleY = 1.0;

    this.myImages = new ImageTemplate();
    this.smileyImage = myImages.getImage(ImageTemplate.SMILEY, 10, 10);
    this.cherryImage = myImages.getImage(ImageTemplate.CHERRY, 14, 14);
    this.chickImage = myImages.getImage(ImageTemplate.CHICK, 12, 10);
  }

  public int getFrameNumber() {
    return this.frameNumber;
  }

  public void setFrameNumber(int frameNumber) {
    this.frameNumber = frameNumber;
  }

  public void setElaspedTime(long elaspedTime) {
    this.elapsedTime = elaspedTime;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g.create();

    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g2.setPaint(Color.WHITE);
    g2.fillRect(0, 0, getWidth(), getHeight());

    applyWindowToViewportTransformation(g2, -45, 45, -45, 45, true);

    AffineTransform savedTransform = g2.getTransform();

    switch (frameNumber) {
      case 1:
        translateX = 0;
        translateY = 0;
        scaleX = 1.0;
        scaleY = 1.0;
        rotation = 0;
        break;
      case 2:
        translateX = -5;
        translateY = 7;
        break;
      case 3:
        rotation = Math.toRadians(45);
        break;
      case 4:
        rotation = Math.toRadians(90);
        break;
      case 5:
        scaleX = 2.0;
        scaleY = 0.5;
        break;
      default:
        break;
    }

    g2.translate(translateX, translateY);

    g2.translate(-50, 0);
    g2.rotate(rotation); 
    g2.scale(scaleX, scaleY); 
    g2.drawImage(smileyImage, 0, 0, this); 
    g2.setTransform(savedTransform);
    
    // cherry image
    g2.translate(translateX, translateY); 

    g2.rotate(rotation); 
    g2.scale(scaleX, scaleY);
    g2.drawImage(cherryImage, 0, 0, this);
    g2.setTransform(savedTransform);

    // chick image
    g2.translate(translateX, translateY); 

    g2.translate(50, 0);
    g2.rotate(rotation);
    g2.scale(scaleX, scaleY);
    g2.drawImage(chickImage, 0, 0, this);
    g2.setTransform(savedTransform);
  }

  // AnimationStarter.java Code
  private void applyWindowToViewportTransformation(Graphics2D g2,
    double left, double right, double bottom, double top,
    boolean preserveAspect) {
  int width = getWidth(); 
  int height = getHeight();

  if (preserveAspect) {
    double displayAspect = Math.abs((double) height / width);
    double requestedAspect = Math.abs((bottom - top) / (right - left));
    if (displayAspect > requestedAspect) {
      double excess = (bottom - top) * (displayAspect / requestedAspect - 1);
      bottom += excess / 2;
      top -= excess / 2;
    } else if (displayAspect < requestedAspect) {
      double excess = (right - left) * (requestedAspect / displayAspect - 1);
      right += excess / 2;
      left -= excess / 2;
    }
  }

  g2.scale(width / (right - left), height / (bottom - top));
  g2.translate(-left, -top);

  double pixelWidth = Math.abs((right - left) / width);
  double pixelHeight = Math.abs((bottom - top) / height);

  pixelSize = (float) Math.max(pixelWidth, pixelHeight);
  }
}
