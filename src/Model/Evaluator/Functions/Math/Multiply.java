package Model.Evaluator.Functions.Math;

import Model.Evaluator.Functions.Math.NumberOperation;
import Model.NewData.Atom;
import Model.NewData.Cons;
import Model.NewData.SExpr;

public class Multiply extends NumberOperation {

  @Override
  protected double operation(SExpr args) {
    if (args.isAtom()) {
      return ((Atom)args).getNumber();
    }
    else if (!args.isNIL()) {
      Cons cons = (Cons) args;
      return operation(cons.first()) * operation(cons.rest());
    }
    return 1;
  }

}
