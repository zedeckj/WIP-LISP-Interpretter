package Model.Evaluator.Functions;

import Model.Evaluator.Evaluator;
import Model.JLISPError;
import Model.NewData.Atom;
import Model.NewData.Cons;
import Model.NewData.List;
import Model.NewData.SExpr;

public class DefinePointer implements Function {


  @Override
  public SExpr run(List args) {
    Cons cons = (Cons)args;
    SExpr value = ((Cons)cons.rest()).first();
    Evaluator.setVariable(((Atom)cons.first()).getSymbol(), value);
    return value;
  }

  @Override
  public boolean shouldEvalFirst() {
    return false;
  }


  @Override
  public ValidateArgs validateArgs(List args) {
    if (!args.isNIL()) {
      Cons cons = (Cons)args;
      if (cons.first().isAtom() && !cons.rest().isNIL()) {
        Atom first = (Atom)cons.first();
        Cons rest = (Cons)cons.rest();
        if (first.isVariable()) {
          throw new JLISPError(String.format("%s is already defined as a constant", first));
        }
        if (first.isSymbol()){
          if (rest.first().isAtom()) {
            Atom term = (Atom)rest.first();
            if (term.isSymbol()) {
              return new ValidateArgs(true);
            }
          }
        }
      }
    }
    return new ValidateArgs("requires a symbol and a symbol to point to");
  }

  @Override
  public boolean shouldEvalBoth() {
    return false;
  }
}
