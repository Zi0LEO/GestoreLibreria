package it.unical.softEng.proxy;

import it.unical.softEng.composite.ElementComposite;

public class LibraryProxy implements Database {
  private Database realLibrary;

  @Override
  public boolean create(ElementComposite element) {
    return false;
  }

  @Override
  public boolean read(ElementComposite element) {
    return false;
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
