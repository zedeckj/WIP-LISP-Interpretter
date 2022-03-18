package Model;

public class JScanner2 {

  private String scan;

  public JScanner2(String in){
    this.scan = in;
  }

  public boolean hasNext() {
    return (scan.length() == 1 && scan.charAt(0) != '(') || scan.length() > 1;
  }

  public String getNext() {
    int l = scan.length() - 1;
    if (l == 0) {
      String out = ""+scan.charAt(0);
      scan = "";
      return out;
    }
    else if (scan.charAt(l) == ' '){
      scan = scan.substring(0, l).strip();
      return getNext();
    }
    else if (scan.charAt(l) == ')') {
      for (int i = l - 1; i >= 0; i--) {
        if (scan.charAt(i) == '(') {
          String out = scan.substring(i, l + 1).strip();
          scan = scan.substring(0, i);
          return out;
        }
      }
    }
    else if (scan.charAt(l) != '(') {
      for (int i = l - 1; i >= 0; i--) {
        if (scan.charAt(i) == ' ' || i == 0) {
          String out = scan.substring(i, l + 1).strip();
          scan = scan.substring(0, i);
          return out;
        }
      }
    }
    throw new JLISPError(String.format("Not a valid next token:%s.",scan));

  }
}
