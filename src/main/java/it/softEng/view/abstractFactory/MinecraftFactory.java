package it.softEng.view.abstractFactory;

import it.softEng.model.ContributionData;
import it.softEng.view.ContributionMap;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.Effect;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;



public class MinecraftFactory implements GuiFactory {
  private final double TOPBAR_HEIGHT = 73;
  private final double SIDEBAR_WIDTH = 200;
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
    StackPane root = createRoot();

    Scene scene = new Scene(root);

    scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());

    return scene;
  }

  private StackPane createRoot() {
    StackPane root = new StackPane();

    //background
    MediaPlayer videoPlayer = createVideoPlayer(getVideo());
    MediaView background = createBackground(videoPlayer);
    root.getChildren().add(background);

    //sidebar
    Pane sidebar = createSidebar(createBackground(videoPlayer),
        createButton("Aggiungi Libro"),
        createButton("Aggiungi Collezione"),
        createButton("Modifica Libro"));
    Platform.runLater(
        () -> sidebar.setTranslateY(TOPBAR_HEIGHT)
    );
    root.getChildren().add(sidebar);

    //topbar
    Pane topbar = createTopBar(
        createButton("Aiuto"),
        createButton("Impostazioni")
    );
    root.getChildren().add(topbar);


    //heatmap
    Node heatmap = createHeatmap();
    StackPane.setAlignment(heatmap, Pos.BOTTOM_CENTER);
    root.getChildren().add(heatmap);

    return root;
  }

  private Node createHeatmap() {
    HBox heatmapContainer = new HBox();
    heatmapContainer.setMaxHeight(TOPBAR_HEIGHT*2);
    heatmapContainer.setAlignment(Pos.CENTER);

    heatmapContainer.setSpacing(50);

    GridPane heatmap = new ContributionMap(new ContributionData());
    heatmap.setAlignment(Pos.CENTER);
    heatmapContainer.getChildren().add(heatmap);
    HBox.setHgrow(heatmap, Priority.ALWAYS);

    Button addEntry = createButton("Ho letto oggi");
    heatmapContainer.getChildren().add(addEntry);
    HBox.setHgrow(addEntry, Priority.ALWAYS);

    return getScrollable(heatmapContainer);
  }

  private ScrollPane getScrollable(Pane heatmapContainer) {
    ScrollPane scrollPane = new ScrollPane(heatmapContainer);
    scrollPane.getStyleClass().add("scroll-pane");
    scrollPane.setTranslateY(-TOPBAR_HEIGHT);
    scrollPane.setMaxHeight(heatmapContainer.getMaxHeight());
    Platform.runLater(() ->
      scrollPane.maxWidthProperty().bind(heatmapContainer.widthProperty().add(SPACING))
    );
    scrollPane.setFitToHeight(true);
    scrollPane.setFitToWidth(false);
    scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
    scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    return scrollPane;
  }

  private Pane createTopBar(Button...buttons) {
    HBox bar = new HBox();
    bar.setSpacing(SPACING);
    bar.setMaxHeight(TOPBAR_HEIGHT);
    bar.setPadding(new Insets(SPACING));

    bar.getChildren().addAll(buttons);

    bar.getStyleClass().add("minecraft-topbar");
    Platform.runLater(() ->
      bar.prefWidthProperty().bind(stage.widthProperty())
    );
    StackPane.setAlignment(bar, Pos.TOP_CENTER);
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
    background.setMouseTransparent(true);
    return background;
  }

  private static final Effect BLUR = new BoxBlur(15, 15, 3);

  private Pane createSidebar(MediaView background, Node...nodes){
    StackPane sidebar = new StackPane();
    Rectangle backgroundContainer = new Rectangle();
    VBox vbox = new VBox();
    vbox.setSpacing(SPACING);

    backgroundContainer.setWidth(SIDEBAR_WIDTH);
    sidebar.setPrefWidth(SIDEBAR_WIDTH);
    stage.setOnShown(event -> {
      sidebar.prefHeightProperty().bind(Bindings.subtract(stage.heightProperty(), TOPBAR_HEIGHT));
      backgroundContainer.heightProperty().bind(stage.heightProperty());
      sidebar.setTranslateY(TOPBAR_HEIGHT);
      background.setTranslateY(-TOPBAR_HEIGHT);
    });
    background.setClip(backgroundContainer);
    background.setEffect(BLUR);
    sidebar.getChildren().add(background);

    vbox.getChildren().addAll(nodes);
    vbox.getStyleClass().add("minecraft-sidebar");
    sidebar.getChildren().add(vbox);
    StackPane.setAlignment(sidebar, Pos.CENTER_LEFT);
    return sidebar;
  }
}
