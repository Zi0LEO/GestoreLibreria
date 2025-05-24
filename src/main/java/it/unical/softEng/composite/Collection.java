package it.unical.softEng.composite;

import java.util.LinkedList;
import java.util.List;
import it.unical.softEng.visitor.Visitor;

public class Collection implements ElementComposite {
  private static int idCounter = 0;
  private int id;

  public String getIdCounter(){
    return String.valueOf(idCounter);
  }
  @Override
  public String getId() {
    return Integer.toString(id);
  }
  @Override
  public void show() {
    System.out.println("Collection: " + name + "\n");
    for (ElementComposite child : children)
      child.show();
  }
  @Override
  public void accept(Visitor visitor) {
    visitor.visit(this);
  }

  public Collection(String name) {
    this.name = name;
    this.id = idCounter;
    idCounter++;
  }


  private String name;
  public String getName() {
    return name;
  }

  private List<ElementComposite> children = new LinkedList<>();

  public boolean add(ElementComposite e) {
    return children.add(e);
  }
}
