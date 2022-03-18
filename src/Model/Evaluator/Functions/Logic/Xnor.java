package Model.Evaluator.Functions.Logic;

import Model.NewData.Atom;
import Model.NewData.List;

public class Xnor extends Xor {

  @Override
  public boolean answer(Atom first, List rest) {
    return !super.answer(first, rest);
  }
}
