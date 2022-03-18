package Model.Evaluator.Functions.Lambda;

import java.util.ArrayList;

import Model.Evaluator.Functions.ReplaceSymbol;
import Model.JLISPError;
import Model.NewData.Atom;
import Model.NewData.Cons;
import Model.NewData.List;
import Model.NewData.NIL;
import Model.NewData.SExpr;

public class LambdaHelper {

  private Cons symbols;
  private SExpr expr;
  private int count;
  private static ReplaceSymbol replace;
  private static ArrayList<LambdaHelper> helpers;
  private SExpr noArgsOut = null;

  private LambdaHelper(SExpr expr, Cons symbols, int count) {
    this.expr = ((Cons)expr).first();
    this.symbols = symbols;
    this.count = count;
  }

  private LambdaHelper(SExpr out) {
    this.noArgsOut = out;
  }

  public static boolean noArgs(int id) {
    return helpers.get(id).noArgsOut != null;
  }

  public static void init(){
    helpers = new ArrayList<>();
  }

  public static int num() {
    return helpers.size();
  }


  public static void set(SExpr expr, Cons symbols, int count) {
    if (helpers == null) {
      helpers = new ArrayList<>();
    }
    helpers.add(new LambdaHelper(expr, symbols, count));
    LambdaHelper.replace = new ReplaceSymbol();
    //System.out.println("\n" + expr + " : " + symbols);
  }

  public static void set(SExpr out) {
    if (helpers == null) {
      helpers = new ArrayList<>();
    }
    helpers.add(new LambdaHelper(out));
  }


  public static SExpr run (List args, int id) {
   // System.out.println("Helper"+id);
    if (args.isNIL()) {
      if (helpers.get(id).noArgsOut != null) {
        return helpers.get(id).noArgsOut;
      }
      else {
        throw new JLISPError("Given lambda requires args!");
      }
    }
    Cons cons = (Cons)args;
    LambdaHelper helper = helpers.get(id);
    if (helper.noArgsOut != null) {
      return helper.noArgsOut;
    }
    return replace(helper.expr, helper.symbols, cons);
    //return Atom.Creator.make(2);
  }

  public static int argsCount(int id) {
    return helpers.get(id).count;
  }

  public static SExpr replace(SExpr in, Cons symbols, Cons args) {
    SExpr out = replace.run(Cons.Creator.make(in, Cons.Creator.make(symbols.first(), args.first())));
    if (!args.rest().isNIL()) {
      out = replace(out, (Cons)symbols.rest(), (Cons)args.rest());
    }
    return out;
  }



}
