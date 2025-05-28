package it.softEng.model.state;

import it.softEng.model.composite.Book;

public abstract class ReadingState {
  Book book;
  public ReadingState(Book book) {
    this.book = book;
  }
  public abstract boolean advance();
}
