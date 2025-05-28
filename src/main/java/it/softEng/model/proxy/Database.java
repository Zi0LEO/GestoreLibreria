package it.softEng.model.proxy;

import it.softEng.model.composite.ElementComposite;

public interface Database {
  boolean create(ElementComposite element);
  ElementComposite read(String id);
  boolean update(ElementComposite element);
  boolean delete(ElementComposite element);
}
