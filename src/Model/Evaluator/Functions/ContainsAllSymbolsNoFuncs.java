package Model.Evaluator.Functions;

import Model.Evaluator.Evaluator;
import Model.NewData.Atom;
import Model.NewData.Cons;
import Model.NewData.List;
import Model.NewData.SExpr;

// first/list contains all of rest/elements, ignoring functions in rest/elements.
public class ContainsAllSymbolsNoFuncs implements Function {

  private Contains contains;

  public Atom no;

  @Override
  public SExpr run(List args) {
    contains = new Contains();
    Cons cons = (Cons) args;
    List list = (List)cons.first();
    List elements = cons.rest();
    return Atom.Creator.number(containsAll(list, elements) ? 1 : 0);
  }

  private boolean containsAll(List list, List elements) {
    //System.out.printf("List: %s \nElements: %s\n", list, elements);
    Contains contains = new Contains();
    if (elements.isNIL()) {
      return true;
    }
    else {
      Cons elems = (Cons) elements;
      if (elems.first().isAtom()) {
        Atom element = (Atom) elems.first();
        //System.out.printf("element: %s\n", element);
        if (!element.isSymbol() ||
        (!list.isNIL() && (((Atom) contains.run(Cons.Creator.make(list, element))).isTrue()))
                || Evaluator.functionExists(element)) {
          return containsAll(list, elems.rest());
        }
        else {
          no = element;
          return false;
        }
      }
      else {
        return containsAll(list, (List)elems.first()) && containsAll(list, elems.rest());
      }
    }




    /*
    if (elements.first().isAtom()) {
      Atom element = (Atom) elements.first();
      if (((Atom) contains.run(Cons.Creator.make(list, element))).isTrue() || Evaluator.functionExists(element)) {
        if (elements.rest().isNIL()) {
          return Atom.Creator.make(1);
        } else {
          return containsAll(list, (Cons) elements.rest());
        }
      } else {
        return Atom.Creator.make(0);
      }
    }
    else if (elements.first().isNIL()) {

    }



    /*
    if (elements.first().isNIL() && elements.rest().isNIL()) {
      return Atom.Creator.make(1);
    }
    else {
      SExpr first = elements.first();
      if (first.isAtom()) {
        if (!((Atom)contains.run(Cons.Creator.make(list,first))).isTrue() &&
                (!Evaluator.functionExists(first) && !((Atom)first).isNumber() && !((Atom)first).isString())) {
          return Atom.Creator.make(0);
        }
        else if (elements.rest().isNIL()) {
          return Atom.Creator.make(1);
        }
        else {
          return containsAll(list, (Cons)elements.rest());
        }
      }
      else {
        if (first.isNIL()) {
          return containsAll(list, (Cons)elements.rest());
        }
        else {
          Cons inner = (Cons)first;
          if (((Atom)containsAll(list, inner)).isTrue()) {
            if (elements.rest().isNIL()) {
              return Atom.Creator.make(1);
            }
            else {
              return containsAll(list, (Cons)elements.rest());
            }

          }
          else {
            return Atom.Creator.make(0);
          }
        }
      }
      }
     */
  }

  @Override
  public boolean validArgs(List args) {
    if (!args.isNIL()) {
      Cons cons = (Cons)args;
      return !cons.first().isAtom();
    }
    return false;
  }

}
