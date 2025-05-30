package it.softEng.view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

public class MinecraftButton extends Button {

  public MinecraftButton(String text) {
    super(text);
    this.addBackground();
    this.addFont();
    this.getStyleClass().add("minecraft-button");
    this.setPrefSize(100, 20);
  }

  private void addFont() {
    Font font = Font.loadFont(getClass().getResource("/fonts/Minecraftia-Regular.ttf").toExternalForm(), 15);
    this.setFont(font);
  }

  private void addBackground() {
    Image buttonImage = new Image(getClass().getResource("/images/minecraftButton.png").toExternalForm());
    BorderImage borderImage = new BorderImage(
        buttonImage,
        new BorderWidths(10),
        Insets.EMPTY,
        new BorderWidths(10),
        true,
        BorderRepeat.REPEAT,
        BorderRepeat.STRETCH);
    this.setBorder(new Border(borderImage));
  }
}
