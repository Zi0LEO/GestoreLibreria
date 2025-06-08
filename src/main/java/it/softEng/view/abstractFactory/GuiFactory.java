package it.softEng.view.abstractFactory;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

public interface GuiFactory {
  Scene createMainScene(Stage stage);
  Button createButton(String text);
  Label createLabel();
  MediaView createBackground(Stage stage);

}
