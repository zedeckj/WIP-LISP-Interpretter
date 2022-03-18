package Model.Evaluator.Functions.Logic;

public class And extends GroupLogic2 {
  @Override
  protected boolean compare(boolean first, boolean second) {
    return first && second;
  }
}
