package Model.Evaluator.Functions.Type;

import Model.NewData.SExpr;

public class isCons extends isType {

  @Override
  public boolean isType(SExpr arg) {
    return !arg.isAtom() && !arg.isNIL();
  }
}
