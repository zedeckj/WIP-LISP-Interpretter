package Model.Evaluator.Functions.Control;

import Model.Evaluator.Functions.Function;
import Model.Evaluator.Functions.ValidateArgs;
import Model.NewData.Atom;
import Model.NewData.Cons;
import Model.NewData.List;
import Model.NewData.SExpr;

public class Wait implements Function {


  @Override
  public SExpr run(List args) {
    return null;
  }

  @Override
  public ValidateArgs validateArgs(List args) {
    if (!args.isNIL()) {
      Cons cons = (Cons) args;
      if (cons.first().isAtom() && cons.rest().isNIL()) {
        Atom atom = (Atom) cons.first();
        if (atom.isNumber() && (atom.getNumber() >= 0)) {
          return new ValidateArgs(true);
        }
      }
    }
    return properForm();
  }

  @Override
  public ValidateArgs properForm() {
    return ValidateArgs.form("(wait <non negative number>)");
  }
}
