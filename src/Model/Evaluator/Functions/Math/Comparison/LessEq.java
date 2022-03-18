package Model.Evaluator.Functions.Math.Comparison;

public class LessEq extends Compare {
  @Override
  protected boolean compare(double first, double second) {
    return first <= second;
  }
}
