package it.softEng.view.abstractFactory;

import it.softEng.model.ContributionData;
import it.softEng.view.ContributionMap;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.Effect;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class MinecraftFactory implements GuiFactory {
  private double topbarHeight;
  private final double SPACING = 5;

  private static MinecraftFactory instance;
  private Stage stage;
  private MinecraftFactory() {}

  public static MinecraftFactory getInstance() {
    if (instance == null) {
      instance = new MinecraftFactory();
    }
    return instance;
  }

  @Override
  public Scene createMainScene(Stage stage) {
    this.stage = stage;

    StackPane root = new StackPane();
    Scene scene = new Scene(root);

    MediaPlayer videoPlayer = createVideoPlayer(getVideo());

    MediaView background = createBackground(videoPlayer);
    root.getChildren().add(background);

    Button aggiuntaLibro = createButton("Aggiungi libro");
    Button aggiuntaCollezione = createButton("Aggiungi collezione");
    Button modificaElemento = createButton("Modifica libro");
    Button button3 = createButton("...");
    Button button4 = createButton("|||");

    Pane topbar = createTopBar(button3, button4);
    root.getChildren().add(topbar);

    Pane sidebar = createSidebar(createBackground(videoPlayer), aggiuntaLibro, aggiuntaCollezione, modificaElemento);
    Platform.runLater(
        () -> sidebar.setTranslateY(topbarHeight)
    );
    root.getChildren().add(sidebar);

    scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
    GridPane heatmap = new ContributionMap(new ContributionData());
    heatmap.setAlignment(Pos.BOTTOM_CENTER);
    root.getChildren().add(heatmap);
    return scene;
  }

  private Pane createTopBar(Button...buttons) {
    HBox bar = new HBox();
    bar.setSpacing(SPACING);
    bar.setMaxHeight(topbarHeight);
    bar.setPadding(new Insets(SPACING));

    bar.getChildren().addAll(buttons);

    bar.getStyleClass().add("minecraft-topbar");
    Platform.runLater(() ->
      bar.prefWidthProperty().bind(bar.getScene().widthProperty())
    );
    StackPane.setAlignment(bar, Pos.TOP_CENTER);
    Platform.runLater(() ->
      topbarHeight = bar.getHeight()
    );
    return bar;
  }

  public Button createButton(String text) {
    return new MinecraftButton(text);
  }

  public Label createLabel() {
    return null;
  }

  private Media getVideo(){
    return new Media(getClass().getResource("/video/minecraft.mp4").toExternalForm());
  }

  private MediaPlayer createVideoPlayer(Media media){
    MediaPlayer player = new MediaPlayer(media);
    player.setAutoPlay(true);
    player.setCycleCount(MediaPlayer.INDEFINITE);
    return player;
  }

  public MediaView createBackground(MediaPlayer player) {
    MediaView background = new MediaView(player);
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
    vbox.setSpacing(SPACING);

    backgroundContainer.setWidth(200);
    sidebar.setPrefWidth(200);
    Platform.runLater(() -> {
      sidebar.prefHeightProperty().bind(sidebar.getScene().heightProperty());
      backgroundContainer.heightProperty().bind(sidebar.getScene().heightProperty());
    });
    background.setClip(backgroundContainer);
    background.setEffect(BLUR);
    sidebar.getChildren().add(background);
    sidebar.setTranslateY(topbarHeight);

    vbox.getChildren().addAll(nodes);
    vbox.getStyleClass().add("minecraft-sidebar");
    sidebar.getChildren().add(vbox);
    StackPane.setAlignment(sidebar, Pos.CENTER_LEFT);
    return sidebar;
  }
}
