package it.unical.softEng.facade;

import it.unical.softEng.PersistentState;

import java.io.*;

public class GsonPersistentFacade extends GsonFacade {
  public boolean persist(PersistentState ps) {
    File output = new File(super.directoryPath, "persistent.json");
    try (FileWriter writer = new FileWriter(output)) {
      gson.toJson(ps, writer);
    } catch (IOException e) {
      return false;
    }
    return true;
  }

  public PersistentState load(){
    File input = new File(super.directoryPath, "persistent.json");
    try(FileReader reader = new FileReader(input)){
      return gson.fromJson(reader, PersistentState.class);
    }catch(IOException e) {
      return null;
    }
  }
}
