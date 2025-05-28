package it.softEng.model.proxy;

import it.softEng.model.composite.ElementComposite;
import it.softEng.model.facade.GsonFacade;

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
