package Model.Evaluator.Functions.Type;

import Model.Evaluator.Functions.Function;
import Model.Evaluator.Functions.ValidateArgs;
import Model.NewData.Cons;
import Model.NewData.List;
import Model.NewData.SExpr;

public class First implements Function {

  @Override
  public SExpr run(List args) {
    Cons cons = (Cons)args;
    return cons.first();
  }

  @Override
  public ValidateArgs validateArgs(List args) {
    if (!args.isNIL()) {
      return new ValidateArgs(true);
    }
    return new ValidateArgs("requires a cons");
  }
}
