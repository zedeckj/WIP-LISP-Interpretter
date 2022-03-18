package Model.Evaluator.Functions.Control;

import Model.Evaluator.Functions.Function;
import Model.NewData.Atom;
import Model.NewData.Cons;
import Model.NewData.List;
import Model.NewData.SExpr;

public class If implements Function {


  @Override
  public SExpr run(List args) {
    Cons all = (Cons) args;
    Cons rest = (Cons) all.rest();
    Cons last = (Cons) rest.rest();

    Atom conditional = (Atom)all.first().evaluate();
    SExpr o1 = rest.first();
    SExpr o2 = last.first();
    return valueOf(conditional) ? o1 : o2;
  }

  @Override
  public boolean validArgs(List args) {
    if (!args.isNIL()) {
      Cons cons = (Cons) args;
      SExpr first = cons.first().evaluate();
      if (first.isAtom() && !cons.rest().isNIL()) {
        Atom conditional = (Atom)first;
        Cons rest = (Cons)cons.rest();
        if (!rest.rest().isNIL()) {
          Cons last = (Cons)rest.rest();
          if (last.rest().isNIL()) {
            return true;
          }
        }
      }
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
