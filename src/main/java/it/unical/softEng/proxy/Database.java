package it.unical.softEng.proxy;

import it.unical.softEng.composite.ElementComposite;

public interface Database {
  boolean create(ElementComposite element);
  ElementComposite read(String id);
  boolean update(ElementComposite element);
  boolean delete(ElementComposite element);
}
