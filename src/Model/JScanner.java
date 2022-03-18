package Model;

public class JScanner {

  private String scan;

  public JScanner(String in){
    this.scan = in.strip();
  }

  public boolean hasNext() {
    return scan.length() > 0;
  }

  public String getRest() {
    return scan;
  }

  public String getNext() {
    int l = scan.length();
    String out;
    if (scan.charAt(0) == '(') {
      int p = 1;
      for (int j = 1; j < l; j++) {
        if (scan.charAt(j) == '(') {
          p++;
        }
        else if (scan.charAt(j) == ')') {
          p--;
        }
        if (p == 0){
          out = scan.substring(0, j + 1).strip();
          scan = scan.substring(j + 1).strip();
          return out;
        }
      }
    }
    else if (!scan.contains(" ")) {
      out = scan;
      scan = "";
      return out;
    }
    else {
      for (int j = 0; j < l; j++) {
        if (scan.charAt(j) == ' ') {
          out = scan.substring(0, j);
          scan = scan.substring(j + 1).strip();
          return out;
        }
      }
    }
    throw new JLISPError("no next token!");
  }


}
