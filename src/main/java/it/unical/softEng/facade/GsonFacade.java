package it.unical.softEng.facade;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import it.unical.softEng.PersistentState;
import it.unical.softEng.composite.Book;
import it.unical.softEng.composite.Collection;
import it.unical.softEng.composite.ElementComposite;

import java.io.*;

public class GsonFacade {
  protected final Gson gson;
  private GsonPersistentFacade stateManager;
  protected String directoryPath;

  public GsonFacade() {
    RuntimeTypeAdapterFactory<ElementComposite> factory =
        RuntimeTypeAdapterFactory.of(ElementComposite.class, "type").
            registerSubtype(Book.class, "book").
            registerSubtype(Collection.class, "collection");
    this.gson = new GsonBuilder().
        registerTypeAdapterFactory(factory).
        setPrettyPrinting().
        create();
  }

  public boolean save(ElementComposite element) {
    File output = new File(directoryPath, element.getId()+".json");
    try(FileWriter writer = new FileWriter(output))
    {
      gson.toJson(element, writer);
    } catch (IOException e) {
      return false;
    }
    return true;
  }

  public ElementComposite read(String id){
    File output = new File(directoryPath, id+".json");
    try(FileReader reader = new FileReader(output)){
      return gson.fromJson(reader, ElementComposite.class);
    } catch (IOException e) {
      return null;
    }
  }

  public boolean persist(PersistentState ps){
    return stateManager.persist(ps);
  }

  public PersistentState load(){
    return stateManager.load();
  }
}
