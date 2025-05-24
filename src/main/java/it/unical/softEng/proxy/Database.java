package it.unical.softEng.proxy;

import it.unical.softEng.composite.ElementComposite;

public interface Database {
  boolean create(ElementComposite element);
  boolean read(ElementComposite element);
  boolean update(ElementComposite element);
  boolean delete(ElementComposite element);
}
