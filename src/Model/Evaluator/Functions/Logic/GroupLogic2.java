package Model.Evaluator.Functions.Logic;

import Model.Evaluator.Functions.Function;
import Model.Evaluator.Functions.ValidateArgs;
import Model.NewData.Atom;
import Model.NewData.Cons;
import Model.NewData.List;
import Model.NewData.SExpr;

public abstract class GroupLogic2 implements Function {


  @Override
  public SExpr run(List args) {
    Cons cons = (Cons) args;
    boolean first = firstAtom(cons).isTrue();
    boolean second = firstAtom((Cons)cons.rest()).isTrue();
    if (firstCompare(first, second)) {
      return Atom.Creator.number(answer(firstAtom(cons), cons.rest()) ? 1 : 0 );
    }
    return Atom.Creator.number(0);
  }

  public boolean answer(Atom first, List rest) {
    return recursive(first, rest);
  }

  private boolean recursive(Atom term, List rest) {
    if (rest.isNIL()) {
      return true;
    }
    else {
      Cons cons = (Cons)rest;
      Atom second = firstAtom(cons);
      if (compare(term.isTrue(),second.isTrue())) {
        return recursive(second, cons.rest());
      }
      return false;
    }
  }

  protected boolean firstCompare(boolean first, boolean second) {
    return compare(first, second);
  }

  protected abstract boolean compare(boolean before, boolean next);

  private Atom firstAtom(Cons cons){
    return (Atom)cons.first();
  }

  @Override
  public ValidateArgs validateArgs(List args) {
    System.out.println("checking");
    if (!args.isNIL()) {
      Cons cons = (Cons) args;
      if (cons.rest().isNIL()) {
        return new ValidateArgs(false);
      }
      return new ValidateArgs(cons.isSimpleList() && allNumbers(cons));
    }
    return new ValidateArgs(false);
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
