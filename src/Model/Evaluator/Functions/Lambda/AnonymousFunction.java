package Model.Evaluator.Functions.Lambda;

import Model.Evaluator.Functions.Function;
import Model.Evaluator.Functions.TerminationDistance;
import Model.NewData.Atom;
import Model.NewData.Cons;
import Model.NewData.List;
import Model.NewData.SExpr;

public class AnonymousFunction implements Function {

  SExpr out;
  private int lambdaID = 0;


  @Override
  public SExpr run(List args) {
    List l = (List)args.evaluate();
    return LambdaHelper.run(l, lambdaID).evaluate();
  }


  @Override
  public boolean validArgs(List args) {
    //System.out.println("Check");
    if (!args.isNIL()) {
        Cons cons = (Cons) args;
        double count1 = ((Atom)(new TerminationDistance()).run(cons)).getNumber();
        double count2 = LambdaHelper.argsCount(lambdaID);
        //System.out.println("count1: " + count1 + " count2: " + count2);
          //System.out.println(count1);
          //System.out.println(count2);
          if (count1  == count2) {
            return true;
          }
    }
    else {
      return LambdaHelper.noArgs(lambdaID);
    }
    return false;
  }

  @Override
  public void setExtra(int extra) {
    //System.out.println("Anon set:"+extra);
    lambdaID = extra;
  }
}
