package Model.Evaluator.Functions;

import Model.NewData.List;
import Model.NewData.SExpr;

public class IsValid implements Function {


  @Override
  public SExpr run(List args) {
    return null;
  }

  @Override
  public ValidateArgs validateArgs(List args) {
    if (!args.isNIL()) {
      return new ValidateArgs(true);
    }
    return new ValidateArgs("Requires arguments");
  }
}
