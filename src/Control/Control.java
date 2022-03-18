package Control;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import Model.Interpreter;
import Model.JLISPError;



public class Control {

  protected static ArrayList<String> lines;
  protected static boolean filemode;
  protected static int iteration;

  private static int currentLine() {
    return iteration + 1;
  }

  public static void setIteration(int i){
    iteration = i;
  }


  public static boolean isFilemode() {
    return filemode;
  }



  protected static ArrayList<String> createLines(Scanner in) {
    ArrayList<String> lines = new ArrayList<>();
    while (in.hasNextLine()) {
      lines.add(in.nextLine());
    }
    return lines;
  }

  protected static void runLines(ArrayList<String> lines) {

    String ln = "";
    int begin = 0;
    boolean waiting = false;
    for (setIteration(0); iteration < lines.size(); setIteration(iteration + 1)) {
      String add = lines.get(iteration);
      if (add.length() > 0) {
        if (waiting) {
          waiting = false;
          begin = iteration;
        }
        if (add.charAt(0) == '#') {
          continue;
        }
      }
      if (add.contains(";")) {
        ln = ln + add.substring(0, add.indexOf(';'));
        try {
          Interpreter.run(ln,false);
        }
        catch (JLISPError e) {
          System.out.printf("<-\u001B[33m Error between lines %d-%d:\n" + e.getMessage() + "\u001B[0m\n",
                  begin + 1, currentLine());
          return;
        }
        ln = "";
        waiting = true;
      }
      else {
        ln = ln + add;
      }
    }
    if (!ln.equals("")) {
      System.out.printf("<-\u001B[33m Error at line %d: End of line ; missing! \u001B[0m\n", currentLine());
    }
  }



}
