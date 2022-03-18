package Model.Evaluator.Functions;

import Control.Control;
import Model.JLISPError;
import Model.NewData.Atom;
import Model.NewData.Cons;
import Model.NewData.List;
import Model.NewData.SExpr;

public interface Function {

  SExpr run(List args);

  default boolean validArgs(List args) {
    return false;
  }

  default ValidateArgs validateArgs(List args) {
    return new ValidateArgs(validArgs(args));
  }

  default boolean shouldPreprocess() {
    return false;
  }

  default boolean shouldEvalFirst() {
    return true;
  }

  default boolean shouldEvalBoth() {
    return true;
  }

  default boolean conditionalEvalFirstArgumentFirst(Cons args) {
    return shouldEvalBoth();
  }

  default SExpr checkedRun(String name, SExpr expr) {
    //System.out.println(expr);
    if (expr.isAtom()) {
      throw new JLISPError("Function needs a list!");
    }
    List args = (List)expr;
      if (shouldEvalFirst()) {
        if (!args.isNIL() && !conditionalEvalFirstArgumentFirst((Cons)args)){
          Cons cons = (Cons)args;
          args = (List)Cons.Creator.make(cons.first(), cons.rest().evaluate());
        }
        else {
          args = (List)args.evaluate();
        }
    }
      ValidateArgs status = validateArgs(args);
    if (!status.isValid()) {

      throw new JLISPError(String.format("Invalid parameters for function %s: %s",
              name, status.getMsg()));
    }
    return run(args);
  }

  default boolean valueOf(Atom atom) {
    if (atom.isNumber()) {
      return atom.getNumber() != 0;
    }
    return false;
  }


  default void setExtra(int extra) {
  }

  default ValidateArgs properForm() {
    return new ValidateArgs("wrong");
  }

}
