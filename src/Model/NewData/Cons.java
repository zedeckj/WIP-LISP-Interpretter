package Model.NewData;

import Model.Evaluator.Evaluator;
import Model.JLISPError;


public class Cons extends List {

  private SExpr first;
  private List rest;

  @Override
  public int hashCode() {
    return first.hashCode() + rest.hashCode();
  }

  @Override
  public SExpr evaluate() {
    //System.out.println(this + " " + Evaluator.isFunction(this));
    return Evaluator.isFunction(this) ? Evaluator.runFunction(this) :
            Cons.Creator.make(first.evaluate(), rest.evaluate());
  }

  @Override
  public SExpr preprocess() {
    return Evaluator.isPreprocess(this) ? Evaluator.runFunction(Cons.Creator.make(first, rest.preprocess())) :
            Cons.Creator.make(first.preprocess(), rest.preprocess());
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof SExpr) {
      SExpr expr = (SExpr) obj;
      if (!expr.isNIL() && !expr.isAtom()) {
        Cons cons = (Cons) expr;
        return first.equals(cons.first) && rest.equals(cons.rest);
      }
    }
    return false;
  }

  @Override
  public String toString() {
    return String.format("(cons %s %s)", first.toString(1), rest.toString(1));
  }

  public String toString(int i) {
    return toString();
        /*
    String tab = "";
    for (int j = 0; j < i; j++) {
      tab += " ";
    }

    if (i % 3 == 0){
      return String.format("\n%s{cons %s %s}", tab, first.toString(i + 1), rest.toString(i + 1));
    }
    else if (i % 2 == 0){

    }
    return String.format("\n%s[cons %s %s]", tab, first.toString(i + 1), rest.toString(i + 1));

     */

  }


  @Override
  public int getExtra() {
    return 0;
  }

  private Cons(SExpr first, List rest) {
    this.first = first;
    this.rest = rest;
  }

  public SExpr first() {
    return first;
  }

  public List rest() {
    return rest;
  }

  @Override
  public boolean isSimpleList() {
    return first.isAtom() && rest.isSimpleList();
  }

  @Override
  public boolean isAtom(){
    return false;
  }

  @Override
  public boolean isNIL(){
    return false;
  }

  public static class Creator {

    public static Cons make(SExpr first, SExpr rest){
      if (rest.isAtom()) {
        rest = new Cons(rest, new NIL());
      }
      Cons cons = new Cons(first, (List)rest);
      return cons;
    }

    public static Cons make(SExpr first, List rest){
      Cons cons = new Cons(first, rest);
      return cons;
    }


  }
}
