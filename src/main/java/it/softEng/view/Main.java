package it.softEng.view;

import it.softEng.view.abstractFactory.GuiFactory;
import it.softEng.view.abstractFactory.MinecraftFactory;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {
  GuiFactory factory;

  @Override
  public void init() throws Exception {
    super.init();
  }

  @Override
  public void start(Stage stage){

    this.factory = MinecraftFactory.getInstance(stage);
    Node background = factory.getBackground();
    StackPane root = new StackPane(background);

    Node mainContent = factory.getDashboard();
    root.getChildren().add(mainContent);

    stage.setScene(new Scene(root));
    stage.setMinHeight(600);
    stage.setMinWidth(1000);

    stage.setTitle("Gestore Libreria");
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
