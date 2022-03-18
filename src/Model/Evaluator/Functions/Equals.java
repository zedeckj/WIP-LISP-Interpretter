package Model.Evaluator.Functions;

import Model.NewData.Atom;
import Model.NewData.Cons;
import Model.NewData.List;
import Model.NewData.SExpr;

public class Equals implements Function {

  @Override
  public SExpr run(List args) {
    Cons cons = (Cons) args;
    return Atom.Creator.number(allSame(firstAtom(cons), cons.rest()) ? 1 : 0 );
  }

  private boolean allSame(Atom first, List rest) {
    if (rest.isNIL()) {
      return true;
    }
    else {
      Cons cons = (Cons)rest;
      if (first.equals(firstAtom(cons))) {
        return allSame(first, cons.rest());
      }
      return false;
    }
  }

  private Atom firstAtom(Cons cons){
    return (Atom)cons.first();
  }

  @Override
  public boolean validArgs(List args) {
    if (!args.isNIL()) {
      Cons cons = (Cons) args;
      return cons.isSimpleList();
    }
    return false;
  }

  @Override
  public boolean shouldEvalFirst() {
    return true;
  }

  @Override
  public boolean shouldEvalBoth() {
    return true;
  }
}
