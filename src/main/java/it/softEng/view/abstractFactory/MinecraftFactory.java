package it.softEng.view.abstractFactory;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.media.Media;


public class MinecraftFactory implements GuiFactory {
  private static MinecraftFactory instance;
  private MinecraftFactory() {}

  public static MinecraftFactory getInstance() {
    if (instance == null) {
      instance = new MinecraftFactory();
    }
    return instance;
  }

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
    return new Media(getClass().getResource("/video/minecraft2.mp4").toExternalForm());
  }
}
