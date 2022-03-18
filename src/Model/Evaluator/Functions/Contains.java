package Model.Evaluator.Functions;

import Model.NewData.Atom;
import Model.NewData.Cons;
import Model.NewData.List;
import Model.NewData.SExpr;

public class Contains implements Function {


  @Override
  public SExpr run(List args) {
    Cons cons = (Cons)args;
    Cons rest = (Cons)cons.rest();
    Atom find = (Atom)rest.first();
    return Atom.Creator.number(contains((Cons)cons.first(), find) ? 1 : 0) ;
  }


  private boolean contains(List list, Atom item) {
    if (list.isNIL()) {
      return false;
    }
    else {
      Cons cons = (Cons) list;
      if (cons.first().isAtom()) {
        return cons.first().equals(item) || contains(cons.rest(), item);
      }
      else {
        return contains((List)cons.first(), item) || contains(cons.rest(), item);
      }
    }
  }


  @Override
  public boolean validArgs(List args) {
    if (!args.isNIL()) {
      Cons cons = (Cons)args;
      if (!cons.first().isAtom()) {
        Cons rest = (Cons)cons.rest();
        return rest.first().isAtom();
      }
    }
    return false;
  }

}
