package Model.Evaluator.Functions.Type;

import Model.NewData.List;
import Model.NewData.SExpr;

public class isAtom extends isType {

  @Override
  public boolean isType(SExpr arg) {
    return arg.isAtom();
  }
}
