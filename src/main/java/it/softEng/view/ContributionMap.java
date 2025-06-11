package it.softEng.view;

import it.softEng.model.ContributionData;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class ContributionMap extends GridPane {
  private final ContributionData data;
  private static final int CELL_SIZE = 10;
  private static final int SPACING = 3;

  public ContributionMap(ContributionData data){
    this.data = data;
    this.setHgap(SPACING);
    this.setVgap(SPACING);
    this.show();
  }
  private void show(){
    LocalDate today = LocalDate.now();
    LocalDate start = today.minusWeeks(52).with(DayOfWeek.MONDAY);
    for(int week = 0; week < 52; week++){
      for(int day = 0; day < 7; day++){
        LocalDate date = start.plusWeeks(week).plusDays(day);
        boolean read = data.getData(date);
        Rectangle cell = new Rectangle(CELL_SIZE, CELL_SIZE);
        cell.setFill(read ? Color.GREEN : Color.GRAY);
        this.add(cell, week, day);
      }
    }
  }
}