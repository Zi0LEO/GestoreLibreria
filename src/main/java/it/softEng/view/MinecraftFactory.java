package it.softEng.view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.media.Media;


public class MinecraftFactory implements GuiFactory{
  @Override
  public Button createButton(String text) {
    return new MinecraftButton(text);
  }

  @Override
  public Label createLabel() {
    return null;
  }

  @Override
  public Media createBackground() {
    return new Media(getClass().getResource("/video/minecraft.mp4").toExternalForm());
  }
}
