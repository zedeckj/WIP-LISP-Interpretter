package Model;

import java.util.ArrayList;
import java.util.Scanner;

import Model.NewData.Atom;
import Model.NewData.Cons;
import Model.NewData.NIL;
import Model.NewData.SExpr;


public class Interpreter {
  private static Scanner sc;
  private static ArrayList<String> symbols;
  private static int openParens = 0;
  private static String scan;
  private static int i = 0;
  private static boolean printall;
//((if 1 (lambda (x) (+ 0 x)) (lambda (y) (+ç 3 y)) ) 2)



  public static void run(String in, boolean printall) {
    sc = new Scanner(in.strip());
    symbols = new ArrayList<>();
    SExpr out;
    if (in.strip().equals("")) {
      out = new NIL();
    }
    else {
      SExpr mid = getSExpression(in, false);
      //System.out.printf("\u001B[32m %s\n \u001B[0m", mid);
      out = mid.preprocess().evaluate().evaluate().evaluate();
      //System.out.printf("\u001B[33m %s\n \u001B[0m", out);
    }
    if (printall) {
      System.out.println("<- "+out);
    }
  }

  public static SExpr get(String in) {
    SExpr out;
    if (in.strip().equals("")) {
      out = new NIL();
    }
    else {
      SExpr mid = getSExpression(in, false);
      //System.out.printf("\u001B[32m %s\n \u001B[0m", mid);
      out = mid.preprocess().evaluate().evaluate().evaluate();
      //System.out.printf("\u001B[33m %s\n \u001B[0m", out);
    }
    return out;
  }


  private static boolean isAtom(String token) {
    return !token.contains("(") && !token.contains(")");
  }

  private static boolean isNumber(String token) {
    try {
      for (int i = 0; i < token.length(); i++) {
        Double.parseDouble(token.substring(i,i+1));
      }

    }
    catch (NumberFormatException e) {
      return false;
    }
    return true;
  }

  private static SExpr buildSexpr(String firstString, String restString, boolean symbols) {
    SExpr first = getSExpression(firstString, symbols);
    JScanner sc = new JScanner(restString);
    if (sc.hasNext()) {
      String next = sc.getNext();
      return Cons.Creator.make(first, buildSexpr(next, sc.getRest(), symbols));
    }
    return Cons.Creator.make(first, new NIL());
  }


  private static SExpr getSExpression(String token, boolean symbols) {
    i++;
    if (isNumber(token)) {
      return Atom.Creator.number(Double.parseDouble(token));
    }
    else if (isAtom(token)) {
      return Atom.Creator.make(token, symbols);
    }
    else if (isSurrounded(token)) {
      boolean quoted = token.charAt(0) == '\'' || symbols;
      JScanner sc = new JScanner(removeParens(token.strip()));
      SExpr out = new NIL();
      if (sc.hasNext()) {
        String first = sc.getNext();
        return buildSexpr(first, sc.getRest(), quoted);
      }
      return out;
     // (if (not (or (= player ‘rock) (= player ‘scissors) (= player ‘paper))) ‘Invalid-move!  (if (= player ai) 'Draw! (if (= player 'rock) (if (= ai 'scissors) 'You-win! 'You-lose!) (if (= ai 'paper) ‘You-lose! ‘You-win!))))))
      /*
      if (hasNext()){
        String firstToken = getNext();
        SExpr first = getSExpression(firstToken);
        if (hasNext()) {
          String rest = scan;
          return Cons.Creator.make(first, getSExpression(rest));
        }
        else {
          return Cons.Creator.make(first, new NIL());
        }
      }
      else {
        return new NIL();
      }
    /*
    else if (isSurrounded(token)) {
      String inner = removeParens(token);
      Scanner sc = new Scanner(inner);
      String regex = "/(\\([^]*\\))|([^()]+)/g";
      if (sc.hasNext(regex)){
        String firstToken = sc.next(regex).strip();
        SExpr first = getSExpression(firstToken);
        if (sc.hasNext(regex)) {
          String rest = sc.next(regex);
          return Cons.Creator.make(first, getSExpression(rest));
        }
        else {
          return Cons.Creator.make(first, new NIL());
        }
      }
      else {
        return new NIL();
      }

      /*
      String inner = removeParens(token);
      Scanner sc = new Scanner(inner);
      String regex = "[(].*[)]|[^ ]*";
      if (sc.hasNext(regex)){
        String firstToken = sc.next(regex);
        SExpr first = getSExpression(firstToken);
        if (sc.hasNext(regex)) {
          String rest = token.replaceFirst(Pattern.quote(firstToken),"").strip();
          return Cons.Creator.make(first, getSExpression(rest));
        }
        else {
          return first;
        }
      }
      else {
        return new NIL();
      }

       */
    }
    else {
      throw new JLISPError("Not a valid S-Expression!");
    }
  }




  private static boolean isSurrounded(String token){
    return (token.charAt(0) == '(' || (token.charAt(1) == '(' && token.charAt(0) == '\''))
            && token.charAt(token.length()-1) == ')';
  }

  private static String removeParens(String token){
    return token.substring(1 + (token.charAt(0) == '\'' ? 1 : 0), token.length()-1);
  }

  /*
  private boolean parse() {
    while (sc.hasNext()){
      String temp = sc.next();
      boolean start = false;
      boolean end = false;
      if (temp.charAt(0) == '('){
        openParens++;
        tokens.add("" + temp.charAt(0));
        temp = temp.substring(1);
        start = true;
      }
      else if (temp.charAt(temp.length() - 1) == ')'){
        if (openParens == 0){
          return false;
        }
        openParens--;
        end = true;
        temp = temp.substring(0, temp.length()-1);
      }
      if (isNumber(temp)) {
        if (start) {
          return false;
        }
        tokens.add(temp);
      }
      else if (isSymbol(temp)) {
        tokens.add(temp);
      }
      else {
        return false;
      }
      if (end) {
        tokens.add(")");
      }
    }
    return true;
  }

   */



  private static boolean isSymbol(String token) {
    return symbols.contains(token);
  }


}
