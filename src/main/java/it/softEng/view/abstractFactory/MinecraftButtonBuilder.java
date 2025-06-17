package it.softEng.view.abstractFactory;

import it.softEng.view.ButtonBuilder;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

public class MinecraftButtonBuilder implements ButtonBuilder {
  private final String text;

  public MinecraftButtonBuilder(String text) {
    this.text = text;
  }

  @Override
  public Region build() {
    Label label = createLabel(text);
    return createButton(label);
  }

  private Label createLabel(String text) {
    Label label = new Label(text);
    label.getStyleClass().add("minecraft-button-label");
    label.setMaxWidth(Double.MAX_VALUE);
    return label;
  }
  private Button createButton(Label label) {
    Button button = new Button();
    button.getStyleClass().add("minecraft-button");
    button.setPrefWidth(200);
    button.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
    button.setGraphic(label);
    return button;
  }
}
