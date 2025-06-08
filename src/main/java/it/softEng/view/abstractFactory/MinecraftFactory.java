package it.softEng.view.abstractFactory;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.Effect;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


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
  public Scene createMainScene(Stage stage) {

    StackPane root = new StackPane();
    Scene scene = new Scene(root);

    Button button1 = createButton("test1");
    Button button2 = createButton("test2");
    MediaView background = createBackground(stage);
    root.getChildren().add(background);

    Pane sidebar = createSidebar(createBackground(stage), button1, button2);
    root.getChildren().add(sidebar);

    StackPane.setAlignment(sidebar, javafx.geometry.Pos.CENTER_LEFT);

    scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
    return scene;
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
  public MediaView createBackground(Stage stage) {
    Media video = new Media(getClass().getResource("/video/minecraft2.mp4").toExternalForm());
    MediaPlayer backgroundPlayer = new MediaPlayer(video);
    backgroundPlayer.setAutoPlay(true);
    backgroundPlayer.setCycleCount(MediaPlayer.INDEFINITE);
    MediaView background = new MediaView(backgroundPlayer);
    background.fitWidthProperty().bind(stage.widthProperty());
    background.fitHeightProperty().bind(stage.heightProperty());
    background.setPreserveRatio(false);
    return background;
  }

  private static final Effect BLUR = new BoxBlur(15, 15, 3);

  private Pane createSidebar(MediaView background, Node...nodes){
    StackPane sidebar = new StackPane();
    Rectangle backgroundContainer = new Rectangle();
    VBox vbox = new VBox();

    backgroundContainer.setWidth(200);
    sidebar.setPrefWidth(200);
    Platform.runLater(() -> {
      sidebar.prefHeightProperty().bind(sidebar.getScene().heightProperty());
      backgroundContainer.heightProperty().bind(sidebar.getScene().heightProperty());
    });
    background.setClip(backgroundContainer);
    background.setEffect(BLUR);
    sidebar.getChildren().add(background);

    vbox.getChildren().addAll(nodes);
    vbox.getStyleClass().add("minecraft-sidebar");
    vbox.setSpacing(10);
    sidebar.getChildren().add(vbox);
    return sidebar;
  }
}
