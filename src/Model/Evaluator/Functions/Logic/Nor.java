package Model.Evaluator.Functions.Logic;

import Model.NewData.Atom;
import Model.NewData.List;

public class Nor extends Or {

  @Override
  public boolean answer(Atom first, List rest) {
    return !super.answer(first, rest);
  }
}
