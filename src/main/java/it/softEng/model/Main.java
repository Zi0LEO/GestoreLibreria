package it.softEng.model;

import javafx.application.Application;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {
  @Override
  public void start(Stage stage) {
    StackPane root = new StackPane();
    stage.setTitle("My JavaFX App");
    stage.setFullScreen(true);
    stage.setResizable(false);
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
