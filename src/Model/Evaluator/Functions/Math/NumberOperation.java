package Model.Evaluator.Functions.Math;

import Model.Evaluator.Functions.Function;
import Model.NewData.Atom;
import Model.NewData.Cons;
import Model.NewData.List;
import Model.NewData.SExpr;

public abstract class NumberOperation implements Function {


  protected abstract double operation(SExpr args);

  @Override
  public SExpr run(List args) {
    return Atom.Creator.number(operation(args));
  }

  private boolean validargs(SExpr args) {
    if (args.isAtom()) {
      Atom atom = ((Atom)args);
      return atom.isNumber();
    }
    else if (!args.isNIL()) {
      Cons cons = (Cons)args;
      return validargs(cons.first()) && validargs(cons.rest());
    }
    return true;
  }

  @Override
  public boolean validArgs(List args) {
    if (!args.isNIL()) {
      Cons cons = (Cons)args;
      if (cons.first().isAtom()) {
        Atom first = (Atom)cons.first();
        if (first.isNumber()) {
          return validargs(cons.rest());
        }
      }
    }
    else {
      return true;
    }
    return false;
  }
}
