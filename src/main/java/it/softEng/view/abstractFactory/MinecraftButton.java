package it.softEng.view.abstractFactory;

import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;

public class MinecraftButton extends Button {
  private final Label label;

  public MinecraftButton(String text) {
    label = new Label(text);
    label.getStyleClass().add("minecraft-button-label");
    label.setMaxWidth(Double.MAX_VALUE);
    this.setGraphic(label);
    this.setPrefWidth(200);
    this.getStyleClass().add("minecraft-button");
    this.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
  }
}
