package Model.View;

import java.util.HashMap;

enum Color {
  BLACK, RED, GREEN, YELLOW, BLUE, PURPLE, CYAN, WHITE;
}

public class View {
  private final int width = 60;
  private final int height = 60;
  private static int x = 1;
  private static int y = 1;
  private StringBuilder screen;
  private Color [] colors;
  private final String BLACK_CODE = "\u001B[40m";
  private final String RED_CODE = "\u001B[41m";
  private final String GREEN_CODE = "\u001B[42m";
  private final String YELLOW_CODE = "\u001B[43m";
  private final String BLUE_CODE = "\u001B[44m";
  private final String PURPLE_CODE = "\u001B[45m";
  private final String CYAN_CODE = "\u001B[46m";
  private final String WHITE_CODE = "\u001B[47m";
  private HashMap<Color, String> codes;

  public View() {
    screen = new StringBuilder();
    colors = new Color[]{Color.BLACK, Color.RED, Color.GREEN, Color.YELLOW, Color.BLUE,
            Color.PURPLE, Color.CYAN, Color.WHITE};
    codes = new HashMap<Color, String>();
    codes.put(Color.BLACK, BLACK_CODE);
    codes.put(Color.RED, RED_CODE);
    codes.put(Color.GREEN, GREEN_CODE);
    codes.put(Color.YELLOW, YELLOW_CODE);
    codes.put(Color.BLUE, BLUE_CODE);
    codes.put(Color.PURPLE, PURPLE_CODE);
    codes.put(Color.CYAN, CYAN_CODE);
    codes.put(Color.WHITE, WHITE_CODE);
  }

  private void addPixel(Color color) {
    screen.append(codes.get(color) + "   ");
  }

  private void addNewLines(int num){
    String n = "";
    for (int i = 0; i < num; i++) {
      n += "\n";
    }
    screen.append("\u001b[0m" + n);
  }
  private void addNewLine(){
    addNewLines(1);
  }

  public void pallete(){
    for (Color c : colors){
      addPixel(c);
    }

  }

  private Color test(int i, int j) {
    if (i == y && j == x){
      return Color.RED;
    }
    else {
      return Color.BLACK;
    }
  }

  public void prepare() {
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        addPixel(i == y && j == x ? Color.BLACK : Color.GREEN);
      }
      addNewLine();
    }
  }

  public void draw() throws InterruptedException {
    prepare();
    System.out.print(screen.toString());
    screen = new StringBuilder();
    Thread.sleep(500);
  }


}
