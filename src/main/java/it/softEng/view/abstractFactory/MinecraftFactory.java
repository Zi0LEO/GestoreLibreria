package it.softEng.view.abstractFactory;

import it.softEng.view.MinecraftSettings;
import it.softEng.view.buttons.MinecraftButtonBuilder;
import it.softEng.view.heatmap.MinecraftHeatmapBuilder;
import it.softEng.view.sidebar.MinecraftSidebarBuilder;
import it.softEng.view.topbar.MinecraftTopBar;
import javafx.geometry.Pos;
import javafx.scene.Node;
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
    Region root = createRoot();

    root.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/style.css")).toExternalForm());

    return root;
  }

  private Region createRoot() {
    BorderPane root = new BorderPane();

    Region topbar = new MinecraftTopBar(
        createButton("test"),
        createButton("test2")
    ).build();
    root.setTop(topbar);


    //sidebar
     Region sidebar = createSidebar(
        createButton("Aggiungi Libro"),
        createButton("Aggiungi Collezione"),
        createButton("Modifica Libro"),
        createButton("Visualizza lista"));
     sidebar.getStyleClass().add("minecraft-sidebar");

     root.setLeft(sidebar);

     Region padding = new Region();
     padding.setMinWidth(MinecraftSettings.DEFAULT.sidebarWidth());
     root.setRight(padding);

    //heatmap
    Node heatmap = createHeatmap();
    BorderPane.setAlignment(heatmap, Pos.BOTTOM_CENTER);
    root.setCenter(heatmap);

    return root;
  }

  private Node createHeatmap() {
    return new MinecraftHeatmapBuilder().build();
  }

  public static Node createButton(String text) {
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

  private Region createSidebar(Node...nodes){
    return new MinecraftSidebarBuilder(getBackground(), nodes).build();

  }
}