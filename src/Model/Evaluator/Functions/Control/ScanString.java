package Model.Evaluator.Functions.Control;

import java.util.Scanner;

import Model.Evaluator.Functions.Function;
import Model.Evaluator.Functions.ValidateArgs;
import Model.NewData.Atom;
import Model.NewData.Cons;
import Model.NewData.List;
import Model.NewData.SExpr;

public class ScanString implements Function {

  private boolean scanned = false;
  private String out;

  @Override
  public SExpr run(List args) {
    if (!scanned) {
      Cons cons = (Cons) args;
      Atom atom = (Atom) cons.first();
      String string = atom.getSymbol();
      System.out.print(":: " + string + " ");
      Scanner sc = new Scanner(System.in);
      out = sc.nextLine();
      scanned = true;
    }
    return Atom.Creator.make(out, true);
  }

  @Override
  public ValidateArgs validateArgs(List args) {
    if (!args.isNIL()) {
      Cons cons = (Cons) args;
      if (cons.first().isAtom() && cons.rest().isNIL()) {
        if (((Atom)cons.first()).isString()) {
          scanned = false;
          return new ValidateArgs(true);

        }
      }
    }
    return properForm();
  }

  @Override
  public ValidateArgs properForm() {
    return ValidateArgs.form("(scan <string>)");
  }

  @Override
  public boolean shouldPreprocess() {
    return true;
  }
}
