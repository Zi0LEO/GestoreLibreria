package it.unical.softEng.state;

import it.unical.softEng.Book;

public abstract class ReadingState {
  Book book;
  public ReadingState(Book book) {
    this.book = book;
  }
  public abstract boolean advance();
}
