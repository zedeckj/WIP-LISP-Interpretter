package Model.NewData;

public class NIL extends List {

  @Override
  public boolean isAtom() {
    return false;
  }

  @Override
  public boolean isNIL() {
    return true;
  }

  @Override
  public int getExtra() {
    return 0;
  }

  @Override
  public SExpr preprocess() {
    return this;
  }

  @Override
  public SExpr evaluate() {
    return this;
  }

  @Override
  public boolean equals(Object obj) {
    return obj instanceof NIL;
  }

  @Override
  public boolean isSimpleList() {
    return true;
  }

  @Override
  public String toString() {
    return "()";
  }

  @Override
  public int hashCode() {
    return 0;
  }
}
