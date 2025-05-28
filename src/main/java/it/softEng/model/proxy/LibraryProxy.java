package it.softEng.model.proxy;

import it.softEng.model.composite.ElementComposite;

public class LibraryProxy implements Database {
  private Database realLibrary;

  @Override
  public boolean create(ElementComposite element) {
    return false;
  }

  @Override
  public ElementComposite read(String id) {
    return null;
  }

  @Override
  public boolean update(ElementComposite element) {
    return false;
  }

  @Override
  public boolean delete(ElementComposite element) {
    return false;
  }
}
