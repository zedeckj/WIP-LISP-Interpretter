package Model.NewData;

import Model.Evaluator.Evaluator;
import Model.JLISPError;

public interface SExpr {


  SExpr preprocess();


  SExpr evaluate();

  String toString();

  boolean isAtom();

  boolean isNIL();

  default String toString(int i) {
    return toString();
  }

  int hashCode();

  int getExtra();




}

