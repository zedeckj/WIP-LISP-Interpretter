package Model.Evaluator.Functions;

import Model.NewData.List;
import Model.NewData.SExpr;

public class Dummy implements Function {

  @Override
  public SExpr run(List args) {
    return null;
  }

  @Override
  public boolean validArgs(List args) {
    return false;
  }
}
