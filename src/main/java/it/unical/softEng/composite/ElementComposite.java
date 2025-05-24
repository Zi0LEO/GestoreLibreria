package it.unical.softEng.composite;

import it.unical.softEng.visitor.Visitor;

public interface ElementComposite {
  void show();
  String getId();
  void accept(Visitor visitor);
}
