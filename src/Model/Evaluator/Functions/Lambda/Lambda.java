package Model.Evaluator.Functions.Lambda;

import Model.Evaluator.Functions.Contains;
import Model.Evaluator.Functions.ContainsAllSymbolsNoFuncs;
import Model.Evaluator.Functions.Function;
import Model.Evaluator.Functions.TerminationDistance;
import Model.Evaluator.Functions.ValidateArgs;
import Model.NewData.Atom;
import Model.NewData.Cons;
import Model.NewData.List;
import Model.NewData.NIL;
import Model.NewData.SExpr;

public class Lambda implements Function {

  private static int lastId = 0;
  private static SExpr last = new NIL();

  @Override
  public SExpr run(List args) {
    int thisId = last.equals(args) ? lastId : lastId++;
    last = args;
    int count = (int)((Atom)(new TerminationDistance()).run((List)((Cons)args).first())).getNumber();
    if (count == 0) {
      LambdaHelper.set(((Cons)((Cons)args).rest()).first());
    }
    else {
      LambdaHelper.set(((Cons) args).rest(), (Cons)((Cons) args).first(), count);
    }
    //System.out.println("Lambda:"+thisId);

    return Atom.Creator.lambda(thisId);
  }

  private boolean allSymbols(SExpr args) {
    if (args.isAtom()) {
      Atom atom = (Atom)args;
      return atom.isSymbol();
    }
    else if (!args.isNIL()) {
      Cons cons = (Cons) args;
      return allSymbols(cons.first()) && allSymbols(cons.rest());
    }
    else {
      return true;
    }
  }


  @Override
  public boolean shouldEvalFirst() {
    return false;
  }

  @Override
  public ValidateArgs validateArgs(List args) {
    if (!args.isNIL()){
      Cons cons = (Cons) args;
      if (!cons.first().isAtom()) {
        List vars = (List)cons.first();
        if (!vars.isSimpleList() || !allSymbols(vars)) {
          return new ValidateArgs("Lambda parameters must be a simple list of symbols");
        }

        if (!cons.rest().isNIL()) {
          SExpr expr = ((Cons)cons.rest()).first();
          if (!((Cons) cons.rest()).rest().isNIL()) {
            return new ValidateArgs("Too many terms for Lambda");
          }
          if (expr.isAtom()) {
            Atom atom = (Atom)expr;
            if (atom.isSymbol()) {
              Contains contains = new Contains();
              if (!((Atom)contains.run(Cons.Creator.make(vars,atom))).isTrue()) {
                return new ValidateArgs("Expression contains unrecognized symbol");
              }
            }
            return new ValidateArgs(true);
          }
          else if (expr.isNIL()) {
            return new ValidateArgs(true);
          }
          else {
            Cons consExpr = (Cons)expr;
            ContainsAllSymbolsNoFuncs noextra = new ContainsAllSymbolsNoFuncs();
            //if (!((Atom)noextra.run(Cons.Creator.make(cons.first(),Cons.Creator.make(((Cons)cons.rest()).first(), new NIL())))).isTrue()){
            if (!((Atom)noextra.run(Cons.Creator.make(cons.first(), cons.rest()))).isTrue()) {
              return new ValidateArgs("Expression contains unrecognized symbol: " + noextra.no);
            }
            return new ValidateArgs(true);
          }
        }
      }
      else {
        return new ValidateArgs("Requires a list of local variables");
      }

    }
    return new ValidateArgs("Cannot be NIL");

    /*
    if (!args.isNIL()) {
      Cons cons = (Cons) args;
      ContainsAll contains = new ContainsAll();
      ContainsAllNoFuncs noextra = new ContainsAllNoFuncs();
      if (cons.first().isNIL()) {
        return !cons.rest().isNIL() && allSymbols(cons.first()) &&
                ((List)cons.first()).isSimpleList() &&
                ((Atom)contains.run(Cons.Creator.make(((Cons)cons.rest()).first(),
                        cons.first()))).isTrue();
      }
      return !cons.rest().isNIL() && allSymbols(cons.first()) &&
              ((List)cons.first()).isSimpleList()
             && ((Atom)noextra.run(Cons.Creator.make(cons.first(),Cons.Creator.make(((Cons)cons.rest()).first(), new NIL())))).isTrue();
    }
    return false;

     */
  }

  /*
  @Override
  public boolean validArgs(List args) {
    AnonymousFunction func = new AnonymousFunction();
    if (!args.isNIL()) {
      Cons cons = (Cons)args;
      if (!cons.first().isAtom() && !cons.first().isNIL()) {
        Cons exp = (Cons)cons.first();
        if (exp.first().isAtom()) {
          Atom atom = (Atom)exp.first();
          return atom.getSymbol().equals("lambda") && func.validArgs(exp.rest()) &&
                  validLambaArgs(exp.rest(), cons.rest());
        }
      }
    }
    return false;
  }

   */



}

