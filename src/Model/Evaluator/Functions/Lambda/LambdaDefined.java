package Model.Evaluator.Functions.Lambda;

import Model.Evaluator.Functions.Function;
import Model.Evaluator.Functions.Lambda.LambdaHelper;
import Model.Evaluator.Functions.TerminationDistance;
import Model.Evaluator.Functions.ValidateArgs;
import Model.Interpreter;
import Model.JLISPError;
import Model.NewData.Atom;
import Model.NewData.Cons;
import Model.NewData.List;
import Model.NewData.SExpr;

import static Model.Evaluator.Functions.Lambda.LambdaHelper.num;

public class LambdaDefined implements Function {

  private int id;
  private String function;
  private boolean checked;
  private boolean last;

  public LambdaDefined(String function){
    this.function = function;
  }

  @Override
  public SExpr run(List args) {
    checked = false;
    List l = (List)args.evaluate();
    return LambdaHelper.run(l, id).evaluate();
  }

  @Override
  public boolean validArgs(List args) {
    if (checked) return last;
    checked = true;
    LambdaHelper.init();
    SExpr expr = Interpreter.get(function);
    id = expr.getExtra();
    if (expr.isAtom()) {
      Atom atom = (Atom)expr;
      if (!atom.isLambda()) throw new JLISPError("Not a valid function definition");
    }
    //System.out.println("Check");
    if (!args.isNIL()) {
      Cons cons = (Cons) args;
      double count1 = ((Atom)(new TerminationDistance()).run(cons)).getNumber();
      double count2 = LambdaHelper.argsCount(id);
      if (cons.isSimpleList()) {
        //System.out.println(count1);
        //System.out.println(count2);
        if (count1 == count2){
          last = true;
          return true;
        }
      }
    }
    else {
      return LambdaHelper.noArgs(id);
    }
    last = false;
    return false;
  }
}
