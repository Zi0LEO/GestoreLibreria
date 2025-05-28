package it.softEng.model.composite;

import it.softEng.model.visitor.Visitor;

public interface ElementComposite {
  void show();
  String getId();
  void accept(Visitor visitor);
}
