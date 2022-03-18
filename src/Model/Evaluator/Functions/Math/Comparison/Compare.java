package Model.Evaluator.Functions.Math.Comparison;

import Model.Evaluator.Functions.Function;
import Model.NewData.Atom;
import Model.NewData.Cons;
import Model.NewData.List;
import Model.NewData.SExpr;

public abstract class Compare implements Function {


  @Override
  public SExpr run(List args) {
    Cons cons = (Cons) args;
    return Atom.Creator.number(recursive(firstAtom(cons), cons.rest()) ? 1 : 0 );
  }

  private boolean recursive(Atom term, List rest) {
    if (rest.isNIL()) {
      return true;
    }
    else {
      Cons cons = (Cons)rest;
      Atom second = firstAtom(cons);
      if (compare(term.getNumber(),second.getNumber())) {
        return recursive(second, cons.rest());
      }
      return false;
    }
  }

  protected abstract boolean compare(double first, double second);

  private Atom firstAtom(Cons cons){
    return (Atom)cons.first();
  }

  @Override
  public boolean validArgs(List args) {
    if (!args.isNIL()) {
      Cons cons = (Cons) args;
      return cons.isSimpleList() && allNumbers(cons);
    }
    return false;
  }

  private boolean allNumbers(List args) {
    if (!args.isNIL()) {
      Cons cons = (Cons) args;
      Atom first = (Atom)cons.first();
      if (first.isNumber() ){
        return allNumbers(cons.rest());
      }
      else {
        return false;
      }
    }
    return true;
  }
}
