package Model.Evaluator.Functions.String;

import Model.Evaluator.Functions.Function;
import Model.Evaluator.Functions.ValidateArgs;
import Model.NewData.Atom;
import Model.NewData.Cons;
import Model.NewData.List;
import Model.NewData.NIL;
import Model.NewData.SExpr;

public class StringMaker implements Function {

  @Override
  public SExpr run(List args) {
    return Atom.Creator.make(recur(args).strip(), true);
  }


  private String recur(List args) {
    if (!args.isNIL()) {
      Cons cons = (Cons)args;
      Atom first = (Atom)cons.first();
      return first.getSymbol() + " " + recur(cons.rest());
    }
    else {
      return "";
    }
  }


  @Override
  public ValidateArgs validateArgs(List args) {
    if (!args.isNIL()) {
      Cons cons = (Cons)args;
      return new ValidateArgs(cons.isSimpleList());
    }
    return new ValidateArgs("Requires at least one argument of symbols");
  }

  @Override
  public boolean shouldEvalFirst() {
    return false;
  }

  @Override
  public boolean shouldPreprocess() {
    return true;
  }


  @Override
  public boolean shouldEvalBoth() {
    return true;
  }
}
