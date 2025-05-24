package it.unical.softEng.visitor;

import it.unical.softEng.composite.Book;
import it.unical.softEng.composite.Collection;

public interface Visitor {
  void visit(Collection collection);
  void visit(Book book);
}
