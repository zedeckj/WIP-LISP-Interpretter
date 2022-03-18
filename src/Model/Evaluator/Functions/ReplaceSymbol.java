package Model.Evaluator.Functions;

import Model.NewData.Atom;
import Model.NewData.Cons;
import Model.NewData.List;
import Model.NewData.SExpr;

public class ReplaceSymbol implements Function {

  @Override
  public SExpr run(List args) {
    Cons consargs = (Cons) args;
    SExpr expr = consargs.first();
    Atom symbol = (Atom)((Cons)consargs.rest()).first();
    Atom replace = (Atom)((Cons)((Cons)consargs.rest()).rest()).first();
    //System.out.println("\n" + replace);
    return searchAndReplace(expr, symbol, replace);
  }

  public SExpr searchAndReplace(SExpr expr, Atom symbol, Atom replace) {
    if (expr.isAtom()) {
      return expr.equals(symbol) ? replace : expr;
    }
    else if (expr.isNIL())  {
      return expr;
    }
    else {
      Cons cons = (Cons)expr;
      return Cons.Creator.make(searchAndReplace(cons.first(), symbol, replace),
              searchAndReplace(cons.rest(), symbol, replace));
    }
  }

  @Override
  public boolean validArgs(List args) {
    if (!args.isNIL()) {
      Cons cons = (Cons) args;
      if (!cons.rest().isNIL()) {
        Cons rest = (Cons)cons.rest();
        if (rest.first().isAtom() && !rest.rest().isNIL() && !cons.first().isAtom()) {
          Atom atom = (Atom) rest.first();
          Cons last = (Cons) rest.rest();
            return atom.isSymbol() && last.first().isAtom();
        }
      }
    }
    return false;
  }
}
