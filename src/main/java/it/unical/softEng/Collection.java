package it.unical.softEng;

import java.util.LinkedList;
import java.util.List;

public class Collection implements ElementComposite {

  private String name;
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

  private List<ElementComposite> children = new LinkedList<>();

  public boolean add(ElementComposite e) {
    return children.add(e);
  }

  public void show() {
    System.out.println("Collection: " + name + "\n");
    for (ElementComposite child : children)
      child.show();
  }
}
