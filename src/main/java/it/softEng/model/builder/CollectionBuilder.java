package it.softEng.model.builder;

import it.softEng.model.composite.Collection;

public class CollectionBuilder {
  private String name;

  public CollectionBuilder setName(String name) {
    this.name = name;
    return this;
  }
  public Collection getCollection() {
    return new Collection(name);
  }
}
