package Model.Evaluator.Functions.Type;

import Model.NewData.SExpr;

public class isNIL extends isType {

  @Override
  public boolean isType(SExpr arg) {
    return arg.isNIL();
  }
}
