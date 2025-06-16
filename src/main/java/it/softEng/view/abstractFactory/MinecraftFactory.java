package it.softEng.view.abstractFactory;

import it.softEng.model.ContributionData;
import it.softEng.view.ContributionMap;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.Effect;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.Objects;


public class MinecraftFactory extends GuiFactory {
  private final double TOPBAR_HEIGHT = 73;
  private final double SIDEBAR_WIDTH = 200;
  private final double SPACING = 5;

  private static MinecraftFactory instance;
  private final Stage stage;
  private MediaPlayer videoPlayer;

  private MinecraftFactory(Stage stage) {
    this.stage = stage;
  }

  public static MinecraftFactory getInstance(Stage stage) {
    if (instance == null) {
      instance = new MinecraftFactory(stage);
    }
    return instance;
  }

  @Override
  public Node getDashboard() {
    StackPane root = createRoot();

    root.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/style.css")).toExternalForm());

    return root;
  }

  private StackPane createRoot() {
    StackPane root = new StackPane();

    //sidebar
    Pane sidebar = createSidebar(getBackground(),
        createButton("Aggiungi Libro"),
        createButton("Aggiungi Collezione"),
        createButton("Modifica Libro"),
        createButton("Visualizza lista"));
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

    Node addEntry = createButton("Ho letto oggi");
    heatmapContainer.getChildren().add(addEntry);
    HBox.setHgrow(addEntry, Priority.ALWAYS);

    return getScrollable(heatmapContainer);
  }

  private ScrollPane getScrollable(Pane heatmapContainer) {
    ScrollPane scrollPane = new ScrollPane(heatmapContainer);
    scrollPane.getStyleClass().add("minecraft-scroll-pane");
    scrollPane.setTranslateY(-TOPBAR_HEIGHT);
    scrollPane.setMaxHeight(heatmapContainer.getMaxHeight() + 50);
    Platform.runLater(() ->
        scrollPane.maxWidthProperty().bind(scrollPane.getScene().widthProperty().subtract((SIDEBAR_WIDTH+SPACING)*2))
        );
    scrollPane.widthProperty().addListener((_, _, newValue) -> {
      if(newValue.doubleValue() > heatmapContainer.getWidth()){
        double padding = (newValue.doubleValue() - heatmapContainer.getWidth())/2;
        scrollPane.setPadding(new Insets(0, 0,0,padding));
      }
      else{
        scrollPane.setPadding(new Insets(0));
      }
    });
    scrollPane.setFitToHeight(true);
    scrollPane.setFitToWidth(false);
    scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
    scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    return scrollPane;
  }

  private Pane createTopBar(Node... nodes) {
    HBox bar = new HBox();
    bar.setSpacing(SPACING);
    bar.setMaxHeight(TOPBAR_HEIGHT);
    bar.setPadding(new Insets(SPACING));

    bar.getChildren().addAll(nodes);

    bar.getStyleClass().add("minecraft-topbar");
    Platform.runLater(() ->
      bar.prefWidthProperty().bind(stage.widthProperty())
    );
    StackPane.setAlignment(bar, Pos.TOP_CENTER);
    return bar;
  }

  public Node createButton(String text) {
    return new MinecraftButtonBuilder(text).build();
  }

  @Override
  public MediaPlayer createVideoPlayer(Media media) {
    return super.createVideoPlayer(new Media(Objects.requireNonNull(getClass().getResource("/video/minecraft.mp4")).toExternalForm()));
  }

  private Media getVideo(){
    return new Media(Objects.requireNonNull(getClass().getResource("/video/minecraft.mp4")).toExternalForm());
  }

  public Node getBackground() {
    if(videoPlayer == null){
      videoPlayer = createVideoPlayer(getVideo());
    }
    MediaView background = new MediaView(videoPlayer);
    background.fitWidthProperty().bind(stage.widthProperty());
    background.fitHeightProperty().bind(stage.heightProperty());
    background.setPreserveRatio(false);
    background.setMouseTransparent(true);
    return background;
  }

  private static final Effect BLUR = new BoxBlur(15, 15, 3);

  private Pane createSidebar(Node background, Node...nodes){
    StackPane sidebar = new StackPane();
    Rectangle backgroundContainer = new Rectangle();
    VBox vbox = new VBox();
    vbox.setSpacing(SPACING);

    backgroundContainer.setWidth(SIDEBAR_WIDTH);
    sidebar.setPrefWidth(SIDEBAR_WIDTH);
    stage.setOnShown(_ -> {
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