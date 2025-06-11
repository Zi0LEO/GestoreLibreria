package it.softEng.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ContributionData {
  private final List<LocalDate> data = new ArrayList<>();

  public void addContribution(LocalDate date){
    data.addLast(date);
  }
  public boolean getData(LocalDate date) {
    return data.contains(date);
  }
}
