package Model.Evaluator.Functions.Math.Comparison;

public class GreaterEq extends Compare {
  @Override
  protected boolean compare(double first, double second) {
    return first >= second;
  }
}
