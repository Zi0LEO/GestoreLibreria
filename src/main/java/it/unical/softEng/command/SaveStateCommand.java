package it.unical.softEng.command;

import it.unical.softEng.composite.Collection;

public class SaveStateCommand implements Command{

  @Override
  public void execute(){
    String createdInstances = Integer.valueOf(Collection.getIdCounter()).toString();
  }
}
