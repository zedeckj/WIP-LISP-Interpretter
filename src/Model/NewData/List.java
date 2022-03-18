package Model.NewData;

public abstract class List implements SExpr {

  private boolean print;


  @Override
  public boolean isAtom() {
    return false;
  }

  public abstract boolean isSimpleList();

}
