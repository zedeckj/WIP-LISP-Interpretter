package Model.Evaluator.Functions.Logic;

import Model.NewData.Atom;
import Model.NewData.List;

public class Xor extends Or {

  @Override
  public boolean answer(Atom first, List rest) {
    And and = new And();
    return !super.answer(first, rest) && !and.answer(first,rest);
  }
}
