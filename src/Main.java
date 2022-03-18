import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import Model.Interpreter;
import Model.JLISPError;

import Control.Control;
import Model.View.Graphics.Renderer;

public class Main extends Control {


  public static void main(String [] args) throws InterruptedException, FileNotFoundException {
    Scanner in;
    ArrayList<String> lines = new ArrayList<>();
    Renderer.initPixels();
      if (args.length == 1) {
        Control.filemode = true;
        URL url = Main.class.getResource(args[0]);
        File file = new File(url.getPath());
        in = new Scanner(new FileInputStream(file));
        lines = createLines(in);
        runLines(lines);
      }
    Control.filemode = false;
    System.out.print(">> ");
    in = new Scanner(System.in);
    while (in.hasNextLine()) {
      String ln = in.nextLine();
      try {
        Interpreter.run(ln,true);
        System.out.print(">> ");
      } catch (JLISPError e) {
        System.out.print("<-\u001B[33m Interpreter Error: " + e.getMessage() + "\u001B[0m\n");
        System.out.print(">> ");
      }
    }
    }



}
