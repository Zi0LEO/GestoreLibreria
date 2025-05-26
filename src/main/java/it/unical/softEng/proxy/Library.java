package it.unical.softEng.proxy;

import it.unical.softEng.composite.ElementComposite;
import it.unical.softEng.facade.GsonFacade;

public class Library implements Database {
  GsonFacade dataManager;

  @Override
  public boolean create(ElementComposite element) {
    return dataManager.save(element);
  }

  @Override
  public ElementComposite read(String id) {
    return dataManager.read(id);
  }

  @Override
  public boolean update(ElementComposite element) {
    ElementComposite data = dataManager.read(element.getId());
    return false;
  }

  @Override
  public boolean delete(ElementComposite element) {
    return dataManager.delete(element.getId());
  }
}
