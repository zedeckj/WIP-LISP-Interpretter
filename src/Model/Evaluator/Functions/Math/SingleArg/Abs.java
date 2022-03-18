package Model.Evaluator.Functions.Math.SingleArg;

public class Abs extends SingleArg {


  @Override
  double operation(double num) {
    return Math.abs(num);
  }

}
