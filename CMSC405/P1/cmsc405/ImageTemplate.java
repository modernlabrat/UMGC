/*
 * Kyra Samuel
 * P1
 * CMSC405
 * 4/19/2022
 * 
 * ImageTemplate.java
 */
package cmsc405;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class ImageTemplate {
  protected static final int[][] SMILEY = {
    {0, 0, 0, 1, 1, 1, 1, 0, 0, 0},
    {0, 0, 1, 6, 6, 6, 6, 1, 0, 0},
    {0, 1, 6, 6, 6, 6, 6, 6, 1, 0},
    {1, 6, 6, 1, 1, 6, 1, 6, 6, 1},
    {1, 6, 6, 1, 1, 6, 1, 1, 6, 1},
    {1, 6, 6, 6, 6, 6, 1, 1, 6, 1},
    {1, 6, 6, 1, 1, 6, 1, 1, 6, 1},
    {0, 1, 6, 1, 1, 6, 1, 6, 1, 0},
    {0, 0, 1, 6, 6, 6, 6, 1, 0, 0},
    {0, 0, 0, 1, 1, 1, 1, 0, 0, 0}
  };

  protected static final int[][] CHERRY = {
    {0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    {1, 4, 4, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    {1, 4, 4, 4, 4, 1, 1, 0, 0, 0, 0, 0, 0, 0},
    {0, 1, 1, 4, 1, 4, 4, 1, 0, 0, 0, 0, 0, 0},
    {0, 0, 1, 4, 1, 1, 1, 4, 1, 1, 1, 1, 0, 0},
    {0, 0, 0, 1, 4, 1, 0, 1, 2, 2, 2, 2, 1, 0},
    {0, 0, 0, 1, 1, 4, 1, 2, 2, 2, 2, 2, 2, 1},
    {0, 0, 1, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 1},
    {0, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 0, 2, 1},
    {0, 1, 2, 2, 2, 2, 2, 2, 1, 0, 0, 2, 2, 1},
    {0, 1, 2, 2, 2, 2, 0, 2, 1, 2, 2, 2, 1, 0},
    {0, 1, 2, 2, 0, 0, 2, 2, 1, 1, 1, 1, 0, 0},
    {0, 0, 1, 2, 2, 2, 2, 1, 0, 0, 0, 0, 0, 0},
    {0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0}
  };

  protected static final int[][] CHICK = {
    {0, 0, 0, 0, 0, 6, 6, 6, 0, 0},
    {0, 0, 0, 0, 6, 6, 6, 6, 6, 0},
    {0, 0, 0, 0, 6, 6, 6, 1, 6, 0},
    {0, 0, 0, 0, 6, 6, 6, 6, 8, 7},
    {6, 0, 6, 6, 6, 6, 6, 6, 6, 0},
    {6, 1, 6, 6, 1, 6, 6, 6, 6, 0},
    {6, 6, 1, 1, 6, 6, 6, 6, 6, 0},
    {6, 6, 6, 6, 6, 6, 6, 6, 6, 0},
    {0, 6, 6, 6, 6, 6, 6, 6, 0, 0},
    {0, 0, 6, 6, 6, 6, 6, 0, 0, 0},
    {0, 0, 0, 0, 7, 0, 7, 0, 0, 0},
    {0, 0, 0, 7, 7, 8, 7, 8, 0, 0}
  };

  public BufferedImage getImage(int[][] imagePixels, int sizeX, int sizeY) {
    BufferedImage image = new BufferedImage(sizeX, sizeY, BufferedImage.TYPE_INT_RGB);
      for (int x = 0; x < sizeX; x++) {
        for (int y = 0; y < sizeY; y++) {
          int pixelColor = imagePixels[x][y];

          switch(pixelColor) {
            case 0:
              pixelColor = Color.WHITE.getRGB();
              break;
            case 1:
              pixelColor = Color.BLACK.getRGB();
              break;
            case 2:
              pixelColor = Color.RED.getRGB();
              break;
            case 3:
              pixelColor = Color.PINK.getRGB();
              break;
            case 4:
              pixelColor = new Color(56, 93, 56).getRGB();
              break;
            case 5:
              pixelColor = new Color(0, 128, 0).getRGB();
              break;
            case 6:
              pixelColor = Color.YELLOW.getRGB();
              break;
            case 7:
              pixelColor = Color.ORANGE.getRGB();
              break;
            case 8:
              pixelColor = new Color(255, 213, 128).getRGB();
              break;
            default:
              pixelColor = Color.black.getRGB();
          }

          image.setRGB(x, y, pixelColor);
        }
      }
    return image;
  }
}
