package it.unical.softEng;

public class GestoreLibreria {
  private GestoreLibreria instance;

  private GestoreLibreria() {}

  public GestoreLibreria createGestoreLibreria(){
    if (instance == null)
      instance = new GestoreLibreria();
    return instance;
  }
}
