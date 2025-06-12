package it.softEng.view.abstractFactory;

import javafx.scene.Node;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public abstract class GuiFactory {
  public abstract Node getBackground();
  public abstract Node getDashboard();

  protected MediaPlayer createVideoPlayer(Media media){
    MediaPlayer player = new MediaPlayer(media);
    player.setAutoPlay(true);
    player.setCycleCount(MediaPlayer.INDEFINITE);
    return player;
  }

}
