package Model.Evaluator.Functions;

public class ValidateArgs {

  private boolean valid;
  private String msg;

  public boolean isValid(){
    return valid;
  }

  public ValidateArgs(String msg) {
    this.valid = false;
    this.msg = msg;
  }

  public ValidateArgs(boolean valid) {
    this.valid = valid;
    this.msg = valid ? "Arguments are valid" : "Arguments are invalid";
  }

  public static ValidateArgs form(String form){
    return new ValidateArgs("must be in the form of " + form);
  }

  public String getMsg() {
    return msg;
  }
}
