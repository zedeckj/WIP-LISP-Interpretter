package Model.Evaluator.Functions.String;

import Model.Evaluator.Functions.Function;
import Model.Evaluator.Functions.ValidateArgs;
import Model.NewData.Atom;
import Model.NewData.Cons;
import Model.NewData.List;
import Model.NewData.NIL;
import Model.NewData.SExpr;

public class Echo implements Function {


  @Override
  public SExpr run(List args) {
    Cons cons = (Cons) args;
    SExpr out = cons.first().preprocess().evaluate().evaluate().evaluate();
    System.out.println("<- "+out);
    return out;
  }

  @Override
  public ValidateArgs validateArgs(List in) {
    if (in.isNIL()) {
      return new ValidateArgs("Requires at least one argument");
    }
    return new ValidateArgs(true);
  }
}
