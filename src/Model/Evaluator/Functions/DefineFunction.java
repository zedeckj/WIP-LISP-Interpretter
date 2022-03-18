package Model.Evaluator.Functions;

import Model.Evaluator.Evaluator;
import Model.Evaluator.Functions.Lambda.AnonymousFunction;
import Model.NewData.Atom;
import Model.NewData.Cons;
import Model.NewData.List;
import Model.NewData.SExpr;

public class DefineFunction implements Function {

  @Override
  public SExpr run(List args) {
    Cons cons = (Cons) args;
    Atom name = (Atom)cons.first();
    Cons rest = (Cons)cons.rest();
    Atom lambda = (Atom)rest.first();
    Function function = new AnonymousFunction();
    function.setExtra(lambda.getExtra());
    //System.out.println("Define:" + lambda.getExtra());
    Evaluator.addFunction(Atom.Creator.make(name.getSymbol(),false),
            function);
    return name;
  }

  @Override
  public boolean validArgs(List args) {
    if (!args.isNIL()) {
      Cons cons = (Cons)args;
      if (cons.first().isAtom() && !cons.rest().isNIL()) {
        Atom first = (Atom)cons.first();
        Cons rest = (Cons)cons.rest();
        if (first.isSymbol() && rest.first().isAtom()) {
          Atom snd = (Atom) rest.first();
          if (snd.equals(Atom.Creator.lambda(-1))) {
            return true;
          }
        }
      }
    }
    return false;
  }

  @Override
  public boolean conditionalEvalFirstArgumentFirst(Cons cons) {
    if (cons.first().isAtom()) {
      return false;
    }
    return true;
  }

  @Override
  public boolean shouldEvalFirst() {
    return true;
  }
}
