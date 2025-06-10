package it.softEng.view.abstractFactory;

import javafx.scene.Scene;
import javafx.stage.Stage;

public interface GuiFactory {
  Scene createMainScene(Stage stage);

}
