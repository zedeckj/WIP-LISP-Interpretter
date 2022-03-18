package Model.Evaluator.Functions;

import Model.Evaluator.Evaluator;
import Model.NewData.Atom;
import Model.NewData.Cons;
import Model.NewData.List;
import Model.NewData.SExpr;

public class DefineVariable implements Function {


  @Override
  public SExpr run(List args) {
    Cons cons = (Cons)args;
    SExpr value = ((Cons)cons.rest()).first();
    Evaluator.setVariable(((Atom)cons.first()).getSymbol(), value);
    return value;
  }

  @Override
  public boolean shouldEvalFirst() {
    return true;
  }


  @Override
  public boolean validArgs(List args) {
    if (!args.isNIL()) {
      Cons cons = (Cons)args;
      if (cons.first().isAtom() && !cons.rest().isNIL()) {
        Atom first = (Atom)cons.first();
        Cons rest = (Cons)cons.rest();
        return first.isSymbol() || first.isVariable();
      }
    }
    return false;
  }

  @Override
  public boolean shouldEvalBoth() {
    return false;
  }
}
