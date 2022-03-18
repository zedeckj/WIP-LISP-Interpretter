package Model.Evaluator.Functions;

import Model.NewData.Atom;
import Model.NewData.Cons;
import Model.NewData.List;
import Model.NewData.SExpr;

public class TerminationDistance implements Function {

  @Override
  public SExpr run(List args) {
    return Atom.Creator.number(recur(args));
  }

  private int recur(SExpr args) {
    if (args.isAtom()) {
      return 1;
    }
    if (args.isNIL()) {
      return 0;
    }
    else {
      Cons cons = (Cons)args;
      return 1 + recur(cons.rest());
    }
  }

  @Override
  public boolean validArgs(List args) {
    return true;
  }
}
