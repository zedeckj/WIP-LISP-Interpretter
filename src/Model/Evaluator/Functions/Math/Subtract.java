package Model.Evaluator.Functions.Math;

import Model.Evaluator.Functions.Math.Add;
import Model.Evaluator.Functions.Math.NumberOperation;
import Model.NewData.Atom;
import Model.NewData.Cons;
import Model.NewData.SExpr;

public class Subtract extends NumberOperation {



  @Override
  protected double operation(SExpr args) {
    if (args.isAtom()) {
      return ((Atom)args).getNumber();
    }
    else if (!args.isNIL()) {
      Cons cons = (Cons) args;
      Add add = new Add();
      return operation(cons.first()) - add.operation(cons.rest());
    }
    return 0;
  }

}
