package it.unical.softEng.state;

import it.unical.softEng.composite.Book;

public class Read extends ReadingState {
  public Read(Book book) {
    super(book);
  }

  @Override
  public boolean advance() {
    return false;
  }
}
