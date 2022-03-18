package Model.Evaluator.Functions;

import Model.NewData.Atom;
import Model.NewData.Cons;
import Model.NewData.List;
import Model.NewData.SExpr;

// first contains all atoms in rest.
public class ContainsAll implements Function {

  private Contains contains;


  @Override
  public SExpr run(List args) {
    contains = new Contains();
    Cons cons = (Cons) args;
    List list = (List)cons.first();
    if (cons.rest().isNIL()) {
      return Atom.Creator.number(1);
    }
    Cons elements = (Cons)cons.rest();
    if (elements.first().isNIL() && elements.rest().isNIL()) {
      return Atom.Creator.number(1);
    }
    else if (list.isNIL()) {
      return Atom.Creator.number(0);
    }
    else {
      return containsAll((Cons)list, elements);
    }




    /*
    Cons cons = (Cons)args;
    contains = new Contains();
    Cons rest = (Cons)cons.rest();
    if (rest.isNIL() || (rest.first().isNIL() && rest.rest().isNIL()) ) {
      return Atom.Creator.make(1);
    }
    else if (cons.first().isNIL()) {
      return Atom.Creator.make(0);
    }
    else {
      return containsAll((Cons)cons.first(), cons.rest());
    }

     */
  }

  private SExpr containsAll(Cons list, Cons elements) {
    //System.out.printf("List: %s \nElements: %s\n", list, elements);
    if (elements.first().isNIL() && elements.rest().isNIL()) {
      return Atom.Creator.number(1);
    }
    else {
      SExpr first = elements.first();
      if (first.isAtom()) {
        if (!((Atom)contains.run((List)Cons.Creator.make(list,first))).isTrue()) {
          return Atom.Creator.number(0);
        }
        else if (elements.rest().isNIL()) {
          return Atom.Creator.number(1);
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
              return Atom.Creator.number(1);
            }
            else {
              return containsAll(list, (Cons)elements.rest());
            }

          }
          else {
            return Atom.Creator.number(0);
          }
        }
      }
    }


    /*
    if (elements.isNIL()) {
      return Atom.Creator.make(1);
    }
    else if (elements.isAtom()) {
      return contains.run(Cons.Creator.make(list,elements));
    }
    else {
      Cons cons = (Cons)elements;
      if (contains.run(Cons.Creator.make(list,cons.first()))
              .equals(Atom.Creator.make(1))) {
        return containsAll(list, cons.rest());
      }
      else {
        return Atom.Creator.make(0);
      }
    }

     */
  }

  @Override
  public boolean validArgs(List args) {
    if (!args.isNIL()) {
      Cons cons = (Cons)args;
      return !cons.first().isAtom() && !cons.rest().isNIL();
    }
    return false;
  }

}
