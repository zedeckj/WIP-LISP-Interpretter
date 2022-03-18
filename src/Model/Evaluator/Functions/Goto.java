package Model.Evaluator.Functions;


import Control.Control;
import Model.NewData.Atom;
import Model.NewData.Cons;
import Model.NewData.List;
import Model.NewData.NIL;
import Model.NewData.SExpr;

public class Goto implements Function {

  @Override
  public SExpr run(List args) {
     Cons cons = (Cons) args;
     Atom atom = (Atom)cons.first();
     Atom jump = Atom.Creator.jump((int)atom.getNumber() - 2);
    return jump;
  }

  @Override
  public ValidateArgs validateArgs(List args) {
    if (Control.isFilemode()) {
      if (!args.isNIL()) {
        Cons cons = (Cons) args;
        if (cons.first().isAtom()) {
          Atom atom = (Atom)cons.first();
          if (atom.isNumber()) {
            if (atom.getNumber() % 1 == 0) {
              return new ValidateArgs(true);
            }
          }
        }
      }
      return new ValidateArgs("Requires a single integer number argument");
    }
    else {
      return new ValidateArgs("Can only be used in file read mode");
    }
  }
}
