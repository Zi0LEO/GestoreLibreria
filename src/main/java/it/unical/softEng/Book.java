package it.unical.softEng;

import it.unical.softEng.state.ReadingState;
import it.unical.softEng.state.ToRead;

public class Book implements ElementComposite{
  private String title, author, ISBN;
  private int valutation;
  private String review;
  private ReadingState state;

  public Book(String tile, String author, String ISBN, int valutation, String review) {
    this.title = tile;
    this.author = author;
    this.ISBN = ISBN;
    this.valutation = valutation;
    this.review = review;
    this.state = new ToRead(this);
  }

  public void setTitle(String title) {
    this.title = title;
  }
  public void setAuthor(String author) {
    this.author = author;
  }
  public void setISBN(String ISBN) {
    this.ISBN = ISBN;
  }
  public void setValutation(int valutation) {
    this.valutation = valutation;
  }
  public void setReview(String review) {
    this.review = review;
  }

  public void setState(ReadingState state) {
    this.state = state;
  }

  public boolean read(){
    return state.advance();
  }

  @Override
  public void show() {
    System.out.println(title + ", " + author + ", " + ISBN + ", " + valutation + ",\n" + review);
  }
}
