package Model.Evaluator.Functions;

import Model.NewData.List;
import Model.NewData.SExpr;

public class Return implements Function {


  @Override
  public SExpr run(List args) {
    return null;
  }

  @Override
  public ValidateArgs validateArgs(List args) {
    return new ValidateArgs(args.isNIL());
  }
}
