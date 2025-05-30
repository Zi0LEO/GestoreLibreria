package it.softEng.view.abstractFactory;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class MinecraftButton extends Button {
  private Label label;

  public MinecraftButton(String text) {
    label = new Label(text);
    label.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
    this.addBackground();
    this.addFont();
    this.setGraphic(label);
    this.getStyleClass().add("minecraft-button");
    this.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
    this.setPadding(Insets.EMPTY);
    this.setPrefSize(300,60);
  }

  private void addFont() {
    Font font = Font.loadFont(getClass().getResource("/fonts/Minecraftia-Regular.ttf").toExternalForm(), 15);
    label.setFont(font);
  }

  private void addBackground() {
    Image buttonImage = new Image(getClass().getResource("/images/minecraftButton.png").toExternalForm());
    BorderImage innerBorder = new BorderImage(
        buttonImage,
        new BorderWidths(7),
        Insets.EMPTY,
        new BorderWidths(7),
        true,
        BorderRepeat.REPEAT,
        BorderRepeat.STRETCH);
    label.setBorder(new Border(innerBorder));
    BorderStroke outerBorder = new BorderStroke(
        Color.BLACK,
        BorderStrokeStyle.SOLID,
        CornerRadii.EMPTY,
        new BorderWidths(3)
    );
    this.setBorder(new Border(outerBorder));
  }
}
