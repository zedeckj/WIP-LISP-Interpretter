package Model.Evaluator.Functions.Math.SingleArg;

public class Random extends SingleArg {

  @Override
  double operation(double num) {
    java.util.Random random = new java.util.Random();
    return num % 1 == 0 ? random.nextInt((int)num) :
            random.nextInt((int)Math.floor(num)) + random.nextDouble();
  }
}
