package it.softEng.model.state;

import it.softEng.model.composite.Book;

public class Read extends ReadingState {
  public Read(Book book) {
    super(book);
  }

  @Override
  public boolean advance() {
    return false;
  }
}
