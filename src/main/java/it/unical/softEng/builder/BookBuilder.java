package it.unical.softEng.builder;

import it.unical.softEng.composite.Book;

public class BookBuilder {
  private String title, author, ISBN;
  private int valutation;
  private String review;

  public BookBuilder setTitle(String title) {
    this.title = title;
    return this;
  }
  public BookBuilder setAuthor(String author) {
    this.author = author;
    return this;
  }
  public BookBuilder setIsbn(String isbn) {
    this.ISBN = isbn;
    return this;
  }
  public BookBuilder setValutation(int valutation) {
    this.valutation = valutation;
    return this;
  }
  public BookBuilder setReview(String review) {
    this.review = review;
    return this;
  }

  public Book getBook() {
    return new Book(title, author, ISBN, valutation, review);
  }
}
