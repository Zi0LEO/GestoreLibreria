package it.softEng.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

  @FXML
  private MediaView backgroundMediaView;

  private File file;
  private Media media;
  private MediaPlayer mediaPlayer;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    backgroundMediaView.sceneProperty().addListener((obs, oldScene, newScene) -> {
      if (newScene != null) {
        backgroundMediaView.fitWidthProperty().bind(newScene.widthProperty());

      }
    });
    file = new File("src/main/resources/video/minecraft.mp4");
    media = new Media(file.toURI().toString());
    mediaPlayer = new MediaPlayer(media);
    backgroundMediaView.setMediaPlayer(mediaPlayer);
    mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
    mediaPlayer.play();
  }
}
