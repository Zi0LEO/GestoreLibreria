package it.softEng.view.abstractFactory;

import it.softEng.model.ContributionData;
import it.softEng.view.ContributionMap;
import it.softEng.view.MinecraftSettings;
import it.softEng.view.MinecraftSidebarBuilder;
import it.softEng.view.MinecraftTopBar;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.util.Objects;


public class MinecraftFactory extends GuiFactory {

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
     Region sidebar = createSidebar(getBackground(),
        createButton("Aggiungi Libro"),
        createButton("Aggiungi Collezione"),
        createButton("Modifica Libro"),
        createButton("Visualizza lista"));
     sidebar.getStyleClass().add("minecraft-sidebar");
    root.getChildren().add(sidebar);

    Node test = new MinecraftButtonBuilder("test").build();

    Region topbar = new MinecraftTopBar(test).build();
    root.getChildren().add(topbar);


    //heatmap
    Node heatmap = createHeatmap();
    StackPane.setAlignment(heatmap, Pos.BOTTOM_CENTER);
    root.getChildren().add(heatmap);

    return root;
  }

  private Node createHeatmap() {
    HBox heatmapContainer = new HBox();
    heatmapContainer.setMaxHeight(MinecraftSettings.DEFAULT.topBarHeight()*2);
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
    scrollPane.setTranslateY(-MinecraftSettings.DEFAULT.topBarHeight());
    scrollPane.setMaxHeight(heatmapContainer.getMaxHeight() + 50);
    Platform.runLater(() ->
        scrollPane.maxWidthProperty().bind(scrollPane.getScene().widthProperty().subtract(
            (MinecraftSettings.DEFAULT.sidebarWidth()+MinecraftSettings.DEFAULT.spacing())*2))
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

  private Region createSidebar(Node background, Node...nodes){
    return new MinecraftSidebarBuilder(background, nodes).build();

  }
}