package Model.Evaluator.Functions.Graphics;

import java.awt.*;

import Model.Evaluator.Functions.Function;
import Model.Evaluator.Functions.ValidateArgs;
import Model.NewData.Atom;
import Model.NewData.Cons;
import Model.NewData.List;
import Model.NewData.SExpr;
import Model.View.Graphics.Display;
import Model.View.Graphics.Renderer;

public class Pixel implements Function {

  @Override
  public SExpr run(List args) {
    Cons cons = (Cons)args;
    Cons rest = (Cons)cons.rest();
    Cons last = (Cons)rest.rest();
    Color color = getColor(((Atom)cons.first()).getSymbol());
    int x = (int)((Atom)rest.first()).getNumber();
    int y = (int)((Atom)last.first()).getNumber();
    Renderer.setPixel(color,x,y);
    return args;
  }

  private boolean isColor(String str) {
    try {
      getColor(str);
      return true;
    }
    catch (IllegalArgumentException e) {
      return false;
    }
  }

  private Color getColor(String color){
    switch (color) {
      case "red": return Color.red;
      case "green": return Color.green;
      case "yellow": return Color.yellow;
      case "blue": return Color.blue;
      case "black": return Color.black;
      case "white": return Color.white;
      default: throw new IllegalArgumentException();
    }
  }

  @Override
  public ValidateArgs validateArgs(List args) {
    if (!args.isNIL()){
      Cons cons = (Cons)args;
      if (cons.first().isAtom()) {
        Atom atom = (Atom)cons.first();
        if (atom.isString()) {
          if (isColor(atom.getSymbol()) && !cons.rest().isNIL()) {
            Cons rest = (Cons)cons.rest();
            if (rest.first().isAtom() && !rest.rest().isNIL()) {
              Atom x = (Atom)rest.first();
              Cons last = (Cons)rest.rest();
              if (last.first().isAtom() && last.rest().isNIL()) {
                Atom y = (Atom)last.first();
                if (x.isNumber() && y.isNumber()) {
                  if (x.getNumber() % 1 == 0 && y.getNumber() % 1 == 0) {
                    return new ValidateArgs(true);
                  }
                }
              }
            }
          }
        }
      }

    }
    return new ValidateArgs("must be in the form of (pixel <color> <decimal> <decimal>)");
  }
}
