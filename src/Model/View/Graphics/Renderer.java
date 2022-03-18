package Model.View.Graphics;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Renderer {
  private static Display display;
  private static Graphics g;
  private static BufferStrategy bs;
  private static int cellSize;
  private static Color [][] pixels;


  public static void initPixels() {
    int width = 50;
    int height = 50;
    pixels = new Color[width][height];
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        pixels[i][j] = Color.BLACK;
      }
    }
  }

  public static void setPixel(Color color, int x, int y) {
    pixels[x][y] = color;
  }


  public static void render(Display display)  {
    int n = pixels.length;;
    bs = display.getCanvas().getBufferStrategy();
    if (bs == null) {
      display.getCanvas().createBufferStrategy(3);
      render(display);
    }
    //System.out.println("test");
    g = bs.getDrawGraphics();
    //g.clearRect(0, 0, n*10, n*10);

    for (int x=0; x<n; x++){
      for (int y=0; y<n; y++){
        System.out.print(" ");
        drawPixel(pixels[x][y], x, y);
      }
    }
    //drawMenu(g);
    bs.show();
    //bs.show();
    //g.dispose();
  }

  private static void drawPixel(Color color, int x, int y) {

    g.setColor(color);
    g.fillRect(10 * x, 10 * y, 10, 10);
  }

  private static void close(){
    display.getJFrame().dispose();
  }
}
