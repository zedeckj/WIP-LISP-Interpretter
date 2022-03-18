package Model.Evaluator.Functions.String;

import Model.Evaluator.Functions.Function;
import Model.Evaluator.Functions.ValidateArgs;
import Model.NewData.Atom;
import Model.NewData.Cons;
import Model.NewData.List;
import Model.NewData.SExpr;

public class toSymbol implements Function {

  @Override
  public SExpr run(List args) {
    Cons cons = (Cons)args;
    Atom atom = (Atom)cons.first();
    return Atom.Creator.symbol(atom.getSymbol());
  }

  @Override
  public ValidateArgs validateArgs(List in) {
    if (!in.isNIL()) {
      Cons cons = (Cons)in;
      if (cons.first().isAtom()) {
        return new ValidateArgs(true);
      }
    }
    return new ValidateArgs("Requires one non symbol argument");
  }

  @Override
  public boolean shouldPreprocess() {
    return true;
  }
}
