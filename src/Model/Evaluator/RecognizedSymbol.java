package Model.Evaluator;


public class RecognizedSymbol {

  private String symbol;
  private SymbolType type;

  public RecognizedSymbol(String symbol, SymbolType type){
    this.symbol = symbol;
    this.type = type;
  }

  public boolean matches(String string){
    return symbol.equals(string);
  }

  public boolean isFunction() {
    return type == SymbolType.Function;
  }

  public boolean isVariable() {
    return type == SymbolType.Variable;
  }
}
