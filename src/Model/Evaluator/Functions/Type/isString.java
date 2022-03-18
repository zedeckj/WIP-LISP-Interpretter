package Model.Evaluator.Functions.Type;

import Model.NewData.Atom;
import Model.NewData.SExpr;

public class isString extends isType {

  @Override
  public boolean isType(SExpr arg) {
    return arg.isAtom() && ((Atom) arg).isString();
  }
}
