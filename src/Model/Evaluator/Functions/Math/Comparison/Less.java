package Model.Evaluator.Functions.Math.Comparison;

public class Less extends Compare {
  @Override
  protected boolean compare(double first, double second) {
    return first < second;
  }
}
