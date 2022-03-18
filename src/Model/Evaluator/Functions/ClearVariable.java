package Model.Evaluator.Functions;

import Model.Evaluator.Evaluator;
import Model.NewData.Atom;
import Model.NewData.Cons;
import Model.NewData.List;
import Model.NewData.NIL;
import Model.NewData.SExpr;

public class ClearVariable implements Function {

  @Override
  public SExpr run(List args) {
    Cons cons = (Cons)args;
    Evaluator.clearVariable(((Atom)cons.first()).getSymbol());
    return new NIL();
  }

  @Override
  public boolean validArgs(List args) {
    if (!args.isNIL()){
      Cons cons = (Cons)args;
      if (cons.first().isAtom()) {
        Atom first = (Atom)cons.first();
        return first.isSymbol() && cons.rest().isNIL();
      }
    }
    return false;
  }

  @Override
  public boolean shouldEvalFirst() {
    return false;
  }
}
