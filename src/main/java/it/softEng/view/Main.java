package it.softEng.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.media.Media;
import javafx.stage.Stage;

public class Main extends Application {
  GuiFactory factory;

  @Override
  public void start(Stage stage){

    this.factory = new MinecraftFactory();
    double WIDTH = stage.getWidth();
    double HEIGHT = stage.getHeight();

    StackPane root = new StackPane();
    BorderPane mainContent = new BorderPane();
    VBox vbox = new VBox();

    Scene scene = new Scene(root);

    Button button1 = factory.createButton("test1");
    Button button2 = factory.createButton("test2");
    Media background = factory.createBackground();

    MediaPlayer backgroundPlayer = new MediaPlayer(background);
    MediaView mediaView = new MediaView(backgroundPlayer);
    backgroundPlayer.setAutoPlay(true);
    backgroundPlayer.setCycleCount(MediaPlayer.INDEFINITE);
    mediaView.prefHeight(HEIGHT);
    root.getChildren().add(mediaView);

    vbox.getChildren().addAll(button1, button2);
    vbox.setSpacing(10);
    vbox.prefWidthProperty().bind(mainContent.widthProperty().multiply(0.15));
    vbox.prefHeightProperty().bind(mainContent.heightProperty());
    vbox.setStyle("-fx-background-color: rgba(0,100,100,0.59);");
    mainContent.setLeft(vbox);
    root.getChildren().add(mainContent);

    stage.setTitle("My JavaFX App");
    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
