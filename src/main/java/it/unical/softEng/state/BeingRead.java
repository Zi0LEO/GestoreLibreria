package it.unical.softEng.state;

import it.unical.softEng.composite.Book;

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
