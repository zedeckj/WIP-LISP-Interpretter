package Model.Evaluator.Functions.Math.SingleArg;

import Model.Evaluator.Functions.Function;
import Model.NewData.Atom;
import Model.NewData.Cons;
import Model.NewData.List;
import Model.NewData.SExpr;

public abstract class SingleArg implements Function {

  @Override
  public SExpr run(List args) {
    Cons cons = (Cons)args;
    Atom num = (Atom)cons.first();
    return Atom.Creator.number(operation(num.getNumber()));
  }

  abstract double operation(double num);

  @Override
  public boolean validArgs(List args) {
    if (!args.isNIL()){
      Cons cons = (Cons)args;
      if (cons.first().isAtom() && cons.rest().isNIL()) {
        return ((Atom)cons.first()).isNumber();
      }
    }
    return false;
  }
}
