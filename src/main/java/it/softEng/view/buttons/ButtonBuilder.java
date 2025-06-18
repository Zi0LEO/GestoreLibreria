package it.softEng.view.buttons;

import javafx.scene.layout.Region;
import javafx.util.Builder;

public interface ButtonBuilder extends Builder<Region> {
  void setHeight(double height);
  void setWidth(double width);
}
