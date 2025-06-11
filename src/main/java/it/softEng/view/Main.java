package it.softEng.view;

import it.softEng.view.abstractFactory.GuiFactory;
import it.softEng.view.abstractFactory.MinecraftFactory;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
  GuiFactory factory;

  @Override
  public void init() throws Exception {
    super.init();
  }

  @Override
  public void start(Stage stage){

    this.factory = MinecraftFactory.getInstance();
    Scene scene = this.factory.createMainScene(stage);
    stage.setMinHeight(600);
    stage.setMinWidth(1000);

    stage.setTitle("My JavaFX App");
    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
