package it.softEng.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Main extends Application {
  @Override
  public void start(Stage stage){
    try {
      URL fxmlUrl = getClass().getResource("/it/softEng/view/Main.fxml");
      Parent root = FXMLLoader.load(fxmlUrl);
      System.out.println("FXML URL: " + fxmlUrl);
      Scene scene = new Scene(root);
      stage.setTitle("My JavaFX App");
      stage.setFullScreen(true);
      stage.setResizable(false);
      stage.setScene(scene);
      stage.show();
    }catch(IOException e){
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    launch(args);
  }
}
