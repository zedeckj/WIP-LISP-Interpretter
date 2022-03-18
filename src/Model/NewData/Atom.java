package Model.NewData;

import Model.JLISPError;
import Model.Evaluator.Evaluator;



public class Atom implements SExpr {

  private final Type type;
  private double number;
  private String symbol;
  private int extra;
  private boolean print;

  private Atom(double number) {
    this.number = number;
    this.type = Type.Number;
  }

  private Atom(String symbol, boolean quoted) {
    if (!quoted && symbol.charAt(0) == '\'') {
      quoted = true;
      symbol = symbol.substring(1);
    }
    this.symbol = symbol;
    this.type = quoted ? Type.String : Type.Symbol;
  }

  //Lambda
  private Atom() {
    this.symbol = "'lam";
    this.type = Type.Symbol;
  }

  //Jump
  private Atom(int line) {
    this.symbol = "'jump";
    this.type = Type.Symbol;
    this.extra = line;
  }


  public boolean isString() {
    if (type == Type.String) {
      return true;
    }
    else if (representsAtom()) {
      return ((Atom) getVariableValue()).isString();
    }
    return false;
  }

  public int getExtra(){
    return extra;
  }

  private void setExtra(int extra) {
    this.extra = extra;
  }




  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Atom) {
      Atom that = (Atom) obj;
      if (that.getType() == type){
        switch (type) {
          case Number: return getNumber() == that.getNumber();
          default: return toString().equals(that.toString());
        }
      }
    }
    return false;
  }

  @Override
  public int hashCode() {
    switch (type) {
      case Number: return (int) Math.round(100 * getNumber());
      default: return symbol.hashCode();
    }
  }

  private Type getType() {
    return type;
  }

  public boolean isTrue() {
    return type == Type.Number && number == 1;
  }

  public boolean isNumber() {
    if (type == Type.Number) {
      return true;
    }
    else if (representsAtom()) {
      return ((Atom) getVariableValue()).isNumber();
    }
    return false;
  }

  public boolean isVariable() {
    if (type == Type.Symbol) {
      return Evaluator.isVariable(symbol);
    }
    return false;
  }

  private SExpr getVariableValue() {
    return Evaluator.getVariable(symbol);
  }

  private boolean representsAtom() {
    if (isVariable()) {
      SExpr value = Evaluator.getVariable(symbol);
      return value.isAtom();
    }
    return false;
  }

  public boolean isSymbol() {
    if (type == Type.Symbol) {
      if (isVariable()) {
        return false;
      }
      return true;
    }
    return false;
  }


  public double getNumber() {
    switch (type) {
      case Number: return number;
      case String: throw new JLISPError("\'" + symbol + " is not a number!");
      default:
        if (representsAtom()) {
          if (((Atom)getVariableValue()).isNumber()){
            return ((Atom) getVariableValue()).getNumber();
          }
        }
        throw new JLISPError(symbol + " is not a number!");
    }
  }

  public String getSymbol() {
    switch (type) {
      case Number: return "" + ((number % 1) == 0 ? String.format("%d",(int)number) : number);
      default: return symbol;
    }
  }

  public String toString() {
    switch (type) {
      case Number:
        return "" + ((number % 1) == 0 ? String.format("%d",(int)number) : number);
      case String: return "\'" + symbol;
      default:
        if (isVariable()) {
          return getVariableValue().toString();
        }
        return symbol;
    }
  }

  public boolean isLambda() {
    return symbol.equals("'lam");
  }


  @Override
  public boolean isAtom() {
    return true;
  }


  @Override
  public SExpr preprocess() {
    return this;
  }

  @Override
  public SExpr evaluate() {
    switch (type) {
      case Symbol:
        if (symbol.equals("'jump")) {
          Control.Control.setIteration(extra);
          return this;
        }
        else if (extra == -1) {
          return this;
        }
        else if (isVariable()){
          return Evaluator.getVariable(symbol);
        }
        else if (Evaluator.functionExists(this)) {
          return this;
        }
        throw new JLISPError("Unregonized symbol: " + symbol);

      default: return this;
    }
  }

  @Override
  public boolean isNIL() {
    return false;
  }

  public static class Creator {

    public static Atom make(String symbol, boolean quoted) {
      return new Atom(symbol, quoted);
    }

    public static Atom string(String symbol) {
      return new Atom(symbol, true);
    }

    public static Atom symbol(String symbol) {
      return new Atom(symbol, false);
    }


    public static Atom number(double number) {
      return new Atom(number);
    }

    public static Atom symbolNoEval(String symbol) {
      Atom atom = new Atom(symbol, false);
      atom.setExtra(-1);
      return atom;
    }

    public static Atom lambda(int id) {
      Atom lamda = new Atom();
      lamda.setExtra(id);
      return lamda;
    }

    public static Atom jump(int line) {
      Atom jump = new Atom(line);
      return jump;
    }
  }
}
