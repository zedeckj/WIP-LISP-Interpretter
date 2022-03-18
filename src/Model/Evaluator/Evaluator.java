package Model.Evaluator;

import java.util.HashMap;
import java.util.Map;

import Model.Evaluator.Functions.ContainsAllSymbolsNoFuncs;
import Model.Evaluator.Functions.Control.If;
import Model.Evaluator.Functions.Control.Recursion;
import Model.Evaluator.Functions.Control.ScanString;
import Model.Evaluator.Functions.DefineFunction;
import Model.Evaluator.Functions.Goto;
import Model.Evaluator.Functions.Graphics.Pixel;
import Model.Evaluator.Functions.Graphics.Show;
import Model.Evaluator.Functions.Lambda.AnonymousFunction;
import Model.Evaluator.Functions.Lambda.Lambda;
import Model.Evaluator.Functions.Equals;
import Model.Evaluator.Functions.Lambda.LambdaDefined;
import Model.Evaluator.Functions.Logic.And;
import Model.Evaluator.Functions.Logic.Imply;
import Model.Evaluator.Functions.Logic.Nand;
import Model.Evaluator.Functions.Logic.Nor;
import Model.Evaluator.Functions.Logic.Not;
import Model.Evaluator.Functions.Logic.Or;
import Model.Evaluator.Functions.Logic.Xnor;
import Model.Evaluator.Functions.Logic.Xor;
import Model.Evaluator.Functions.Math.Add;
import Model.Evaluator.Functions.Contains;
import Model.Evaluator.Functions.ContainsAll;
import Model.Evaluator.Functions.DefineVariable;
import Model.Evaluator.Functions.Math.Comparison.Greater;
import Model.Evaluator.Functions.Math.Comparison.GreaterEq;
import Model.Evaluator.Functions.Math.Comparison.Less;
import Model.Evaluator.Functions.Math.Comparison.LessEq;
import Model.Evaluator.Functions.Math.Divide;
import Model.Evaluator.Functions.Function;
import Model.Evaluator.Functions.Math.Multiply;
import Model.Evaluator.Functions.Math.SingleArg.Abs;
import Model.Evaluator.Functions.Math.SingleArg.Add1;
import Model.Evaluator.Functions.Math.SingleArg.Add2;
import Model.Evaluator.Functions.Math.SingleArg.Minus1;
import Model.Evaluator.Functions.Math.SingleArg.Minus2;
import Model.Evaluator.Functions.Math.SingleArg.Random;
import Model.Evaluator.Functions.Math.SingleArg.Sqrt;
import Model.Evaluator.Functions.Math.Subtract;
import Model.Evaluator.Functions.ReplaceSymbol;
import Model.Evaluator.Functions.String.Echo;
import Model.Evaluator.Functions.String.StringMaker;
import Model.Evaluator.Functions.TerminationDistance;
import Model.Evaluator.Functions.Type.First;
import Model.Evaluator.Functions.Type.Rest;
import Model.Evaluator.Functions.Type.isAtom;
import Model.Evaluator.Functions.Type.isCons;
import Model.Evaluator.Functions.Type.isNIL;
import Model.Evaluator.Functions.Type.isNumber;
import Model.Evaluator.Functions.Type.isString;
import Model.Evaluator.Functions.ValidateArgs;
import Model.JLISPError;
import Model.NewData.Atom;
import Model.NewData.Cons;
import Model.NewData.List;
import Model.NewData.SExpr;

public class Evaluator {


  private static Lambda lamcheck = new Lambda();
  private static Map<SExpr, Function> functions = new HashMap<>();
  private static Map<String, SExpr> variables = new HashMap<>();
  private static String lambdaString = "lambda";
  private static boolean init = false;


  private static void shouldInit(){
    if (!init){
      init = true;
      functions.put(Atom.Creator.symbol("<"), new Less());
      functions.put(Atom.Creator.symbol(">"), new Greater());
      functions.put(Atom.Creator.symbol("<="), new LessEq());
      functions.put(Atom.Creator.symbol(">="), new GreaterEq());
      functions.put(Atom.Creator.symbol("="), new Equals());
      functions.put(Atom.Creator.symbol("+"), new Add());
      functions.put(Atom.Creator.symbol("*"), new Multiply());
      functions.put(Atom.Creator.symbol("/"), new Divide());
      functions.put(Atom.Creator.symbol("-"), new Subtract());
      functions.put(Atom.Creator.symbol("="), new Equals());
      functions.put(Atom.Creator.symbol("add1"), new Add1());
      functions.put(Atom.Creator.symbol("add2"), new Add2());
      functions.put(Atom.Creator.symbol("sub1"), new Minus1());
      functions.put(Atom.Creator.symbol("sub2"), new Minus2());
      functions.put(Atom.Creator.symbol("sqrt"), new Sqrt());
      functions.put(Atom.Creator.symbol("abs"), new Abs());

      functions.put(Atom.Creator.symbol("pixel"), new Pixel());
      functions.put(Atom.Creator.symbol("if"), new If());
      functions.put(Atom.Creator.symbol("scan"), new ScanString());


      functions.put(Atom.Creator.symbol("add"), new Add());
      functions.put(Atom.Creator.symbol("multiply"), new Multiply());
      functions.put(Atom.Creator.symbol("divide"), new Divide());
      functions.put(Atom.Creator.symbol("minus"), new Subtract());
      functions.put(Atom.Creator.symbol("equals"), new Equals());
      functions.put(Atom.Creator.symbol("random"), new Random());

      functions.put(Atom.Creator.symbol("recur"), new Recursion());

      functions.put(Atom.Creator.symbol("valid?"), new Recursion());

      functions.put(Atom.Creator.symbol("first"), new First());
      functions.put(Atom.Creator.symbol("rest"), new Rest());
      functions.put(Atom.Creator.symbol("atom?"), new isAtom());
      functions.put(Atom.Creator.symbol("cons?"), new isCons());
      functions.put(Atom.Creator.symbol("nil?"), new isNIL());
      functions.put(Atom.Creator.symbol("number?"), new isNumber());
      functions.put(Atom.Creator.symbol("string?"), new isString());

      functions.put(Atom.Creator.symbol("\""), new StringMaker());
      functions.put(Atom.Creator.symbol("string"), new StringMaker());

      functions.put(Atom.Creator.symbol("show"), new Show());
      functions.put(Atom.Creator.symbol("echo"), new Echo());
      functions.put(Atom.Creator.symbol("and"), new And());
      functions.put(Atom.Creator.symbol("or"), new Or());
      functions.put(Atom.Creator.symbol("nand"), new Nand());
      functions.put(Atom.Creator.symbol("not"), new Not());
      functions.put(Atom.Creator.symbol("nor"), new Nor());
      functions.put(Atom.Creator.symbol("xor"), new Xor());
      functions.put(Atom.Creator.symbol("xnor"), new Xnor());
      functions.put(Atom.Creator.symbol("imply"), new Imply());

      
      
      functions.put(Atom.Creator.lambda(-1), new AnonymousFunction());
      functions.put(Atom.Creator.symbol("goto"), new Goto());
      functions.put(Atom.Creator.symbol("replace"), new ReplaceSymbol());
      functions.put(Atom.Creator.symbol("dist"), new TerminationDistance());
      functions.put(Atom.Creator.symbol("set"), new DefineVariable());
      //functions.put(Atom.Creator.symbol("pointer"), new DefinePointer());
      functions.put(Atom.Creator.symbol("contains"), new Contains());
      functions.put(Atom.Creator.symbol("contains-all"), new ContainsAll());
      functions.put(Atom.Creator.symbol("ca-nofunc"), new ContainsAllSymbolsNoFuncs());
      //functions.put(Atom.Creator.symbol("clear"), new ClearVariable());

      functions.put(Atom.Creator.symbol("name"), new DefineFunction());


      functions.put(Atom.Creator.symbol("add3"),
              new LambdaDefined("(lambda (x) (+ x 3))"));
    }

  }

  public static Function getFunction(SExpr key) {
    return functions.get(key);
  }

  public static void addFunction(SExpr key, Function func) {
    functions.put(key,func);
  }

  public static boolean functionExists(SExpr key) {
    return functions.containsKey(key) || key.toString().equals(lambdaString);
  }


  public static void clearVariable(String var) {
    shouldInit();
    variables.remove(var);
  }

  public static void setVariable(String var, SExpr val) {
    shouldInit();
    if (variables.containsKey(var)) {
      String type = "Constant";
      if (val.isAtom()) {
        Atom atom = (Atom)val;
        if (atom.isSymbol()) type = "Pointer";
      }
      //throw new JLISPError(String.format("%s %s is already defined", type, var));
    }
    variables.put(var, val);
  }

  public static boolean isPreprocess(Cons cons) {
    shouldInit();

    if (functionExists(cons.first()) && !cons.first().toString().equals(lambdaString)) {
      //System.out.println(cons);
      Function func = functions.get(cons.first());
      return func.shouldPreprocess();
    }
    return false;
  }

  public static boolean isFunction(Cons cons) {
    shouldInit();
    //System.out.println(cons.first());
    if (cons.first().isAtom()) {
      Atom first = (Atom)cons.first();
      if (functions.containsKey(first)) {
        Function func = functions.get(first);
        List rest = (List)cons.rest();
        if (func.shouldEvalFirst()) {
          if (!rest.isNIL() && !func.conditionalEvalFirstArgumentFirst((Cons)rest)) {
            Cons args = (Cons)rest;
            rest = (List)Cons.Creator.make(args.first(), args.rest().evaluate());
          }
          else {
            rest = (List)rest.evaluate();
          }
        }
        ValidateArgs status = func.validateArgs(rest);
        if (status.isValid()) {
          return true;
        }
        else {
          throw new JLISPError(String.format("Invalid arguments %s\nfor function %s: %s", rest, first, status.getMsg()));
        }
      }
      else if (first.toString().equals(lambdaString)) {
        Function func = lamcheck;
        List rest = (List)cons.rest();
        ValidateArgs status = lamcheck.validateArgs(rest);
        if (status.isValid()) {
          return true;
        }
        else {
          throw new JLISPError(String.format("Invalid arguments %s\nfor function %s: %s", cons.rest(), cons.first(), status.getMsg()));
        }
      }
    }
    return false;
  }

  public static boolean isVariable(String atom){
    shouldInit();
    return variables.containsKey(atom);
  }

  public static SExpr getVariable(String atom){
    shouldInit();
    return variables.get(atom);
  }

  public static SExpr runFunction(Cons cons){
    shouldInit();
    //System.out.println(cons);
    boolean lambda = false;
    if (cons.first().isAtom()) {
      Atom first = (Atom)cons.first();
      if (first.isSymbol() && first.toString().equals(lambdaString)) {
        lambda = true;
      }
    }
    if (lambda) {
      return (new Lambda()).run(cons.rest());
    }
    else {
      Function func = functions.get(cons.first());
      //func.setExtra(cons.first().getExtra());
      return func.checkedRun(cons.first().toString(), cons.rest());
    }

  }

}
