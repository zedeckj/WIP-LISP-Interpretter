package Model.Evaluator.Functions.Type;



import Model.Evaluator.Functions.Function;
import Model.Evaluator.Functions.ValidateArgs;
import Model.NewData.Atom;
import Model.NewData.Cons;
import Model.NewData.List;
import Model.NewData.SExpr;

public abstract class isType implements Function {

  @Override
  public SExpr run(List args) {
    return Atom.Creator.number(isType(getFirst(args)) ? 1 : 0);
  }

  public abstract boolean isType(SExpr arg);

  public SExpr getFirst(List args) {
    Cons cons = (Cons)args;
    return cons.first();
  }

  @Override
  public ValidateArgs validateArgs(List args) {
    if (!args.isNIL()) {
      Cons cons = (Cons) args;
      if (cons.rest().isNIL()) {
        return new ValidateArgs(true);
      }
    }
    return new ValidateArgs("Takes a single argument");
  }
}
