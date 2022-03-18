package Model.Evaluator.Functions.Logic;


import Model.Evaluator.Functions.Function;
import Model.NewData.Atom;
import Model.NewData.Cons;
import Model.NewData.List;
import Model.NewData.SExpr;

public class Not implements Function {

  @Override
  public SExpr run(List args) {
    Cons cons = (Cons)args;
    Cons rest = (Cons)cons.rest();
    Atom first = (Atom)cons.first();
    return Atom.Creator.number(valueOf(first) ? 0 : 1);
  }


  @Override
  public boolean validArgs(List args) {
    if (!args.isNIL()) {
      Cons cons = (Cons)args;
      return cons.rest().isNIL();
    }
    return false;
  }

}
