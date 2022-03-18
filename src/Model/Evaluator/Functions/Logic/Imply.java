package Model.Evaluator.Functions.Logic;

public class Imply extends GroupLogic2 {


  @Override
  protected boolean compare(boolean before, boolean next) {
    return !before || next;
  }
}
