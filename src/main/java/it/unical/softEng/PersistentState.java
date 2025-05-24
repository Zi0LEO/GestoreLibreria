package it.unical.softEng;

import it.unical.softEng.mediator.Mediator;
import it.unical.softEng.mediator.MediatorComponent;

public class PersistentState implements MediatorComponent {
  Mediator mediator;
  private static PersistentState instance;

  private PersistentState(){}

  public static PersistentState getInstance(){
    if(instance == null){
      instance = new PersistentState();
    }
    return instance;
  }

  @Override
  public void setMediator(Mediator mediator) {
    this.mediator = mediator;
  }

  @Override
  public String getName() {
    return "";
  }
}
