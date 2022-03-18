package Model.Evaluator.Functions.Math;

import Model.Evaluator.Functions.Math.Multiply;
import Model.Evaluator.Functions.Math.NumberOperation;
import Model.NewData.Atom;
import Model.NewData.Cons;
import Model.NewData.SExpr;

public class Divide extends NumberOperation {

  @Override
  protected double operation(SExpr args) {
    if (args.isAtom()) {
      return ((Atom)args).getNumber();
    }
    else if (!args.isNIL()) {
      Cons cons = (Cons) args;
      Multiply mult = new Multiply();
      return operation(cons.first()) / mult.operation(cons.rest());
    }
    return 1;
  }

}
