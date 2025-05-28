package it.softEng.model.state;

import it.softEng.model.composite.Book;

public class BeingRead extends ReadingState {

  public BeingRead(Book book) {
    super(book);
  }

  @Override
  public boolean advance() {
    super.book.setState(new Read(book));
    return false;
  }
}
