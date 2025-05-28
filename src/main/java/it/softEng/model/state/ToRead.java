package it.softEng.model.state;

import it.softEng.model.composite.Book;

public class ToRead extends ReadingState {
  public ToRead(Book book) {
    super(book);
  }

  @Override
  public boolean advance() {
    book.setState(new BeingRead(book));
    return true;
  }
}
