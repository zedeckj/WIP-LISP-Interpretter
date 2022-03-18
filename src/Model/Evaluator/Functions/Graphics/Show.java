package Model.Evaluator.Functions.Graphics;

import Model.Evaluator.Functions.Function;
import Model.Evaluator.Functions.ValidateArgs;
import Model.NewData.Atom;
import Model.NewData.Cons;
import Model.NewData.List;
import Model.NewData.SExpr;
import Model.View.Graphics.Display;
import Model.View.Graphics.Renderer;

public class Show implements Function {

  @Override
  public SExpr run(List args) {
    Cons cons = (Cons) args;
    Atom atom = (Atom)cons.first();
    Display display = new Display(atom.getSymbol(), 500, 500);
    Renderer.render(display);
    return Atom.Creator.make("show", false);
  }

  @Override
  public ValidateArgs validateArgs(List args) {
    if (!args.isNIL()){
      Cons cons = (Cons)args;
      if (cons.first().isAtom()) {
        Atom atom = (Atom)cons.first();
        if (atom.isString()) {
          return new ValidateArgs(true);
        }
      }

    }
    return new ValidateArgs("Requires one string argument");
  }
}
