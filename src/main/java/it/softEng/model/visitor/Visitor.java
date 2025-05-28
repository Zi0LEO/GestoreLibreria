package it.softEng.model.visitor;

import it.softEng.model.composite.Book;
import it.softEng.model.composite.Collection;

public interface Visitor {
  void visit(Collection collection);
  void visit(Book book);
}
