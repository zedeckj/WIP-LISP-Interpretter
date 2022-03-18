package Model.Evaluator.Functions.Control;

import Model.Evaluator.Evaluator;
import Model.Evaluator.Functions.Function;
import Model.Evaluator.Functions.Lambda.AnonymousFunction;
import Model.NewData.Atom;
import Model.NewData.Cons;
import Model.NewData.List;
import Model.NewData.NIL;
import Model.NewData.SExpr;

public class Recursion implements Function {


  @Override
  public SExpr run(List args) {
    Cons cons = (Cons)args;
    int max = (int)((Atom)cons.first()).getNumber();
    Atom lambda = (Atom)(((Cons)cons.rest()).first()).evaluate();
    SExpr arg = ((Cons)(((Cons)cons.rest()).rest())).first();
    return recur(max, lambda, arg, 0);
  }

  private SExpr recur(int max, Atom lambda, SExpr arg, int i) {
    AnonymousFunction func = new AnonymousFunction();
    func.setExtra(lambda.getExtra());
    SExpr out = func.run(Cons.Creator.make(arg, new NIL())).evaluate();
    //System.out.printf("out:%s lam:%s arg:%s i:%d\n",out, lambda, arg, i);
    if (i == max - 1) {
      return out;
    }
    else {
      return recur(max, lambda, out, ++i);
    }
  }

  @Override
  public boolean validArgs(List args) {
    if (!args.isNIL()) {
      Cons cons = (Cons)args;
      if (cons.first().isAtom() && !cons.rest().isNIL()) {
        Atom first = (Atom)cons.first();
        Cons rest = (Cons)cons.rest();
        if (first.isNumber() && rest.first().isAtom() && !rest.rest().isNIL()) {
          if (first.getNumber() % 1 != 0 || first.getNumber() < 1) {
            return false;
          }
          Atom func = (Atom)rest.first();
          Cons last = (Cons)rest.rest();
          if (Evaluator.functionExists(func) && last.rest().isNIL()) {
            return true;
          }
        }
      }
    }
    return false;
  }

  @Override
  public boolean shouldEvalFirst() {
    return false;
  }
}
