package it.softEng.view;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;

public class MinecraftSidebarBuilder implements SidebarBuilder {
  private final Node[] nodes;
  private final Node background;

  private DoubleProperty height = new SimpleDoubleProperty();

  public MinecraftSidebarBuilder(Node background, Node...nodes) {
    this.nodes = nodes;
    this.background = background;
  }

  @Override
  public Region build() {
    Region content = buildContent();
    Pane sidebar = buildSidebar();
    cropBackground();

    sidebar.getChildren().addAll(background, content);
    return sidebar;
  }

  private Pane buildSidebar() {
    StackPane result = new StackPane();
    result.setTranslateY(MinecraftSettings.DEFAULT.topBarHeight());
    result.setMaxWidth(MinecraftSettings.DEFAULT.sidebarWidth());
    height.bind(result.heightProperty());
    return result;
  }

  private Region buildContent() {
    VBox vbox = new VBox();
    vbox.getChildren().addAll(nodes);
    vbox.getStyleClass().add("minecraft-sidebar");
    vbox.setSpacing(MinecraftSettings.DEFAULT.spacing());
    StackPane.setAlignment(vbox, Pos.CENTER_LEFT);
    return vbox;
  }

  private void cropBackground() {
    Rectangle result = new Rectangle();
    result.setWidth(MinecraftSettings.DEFAULT.sidebarWidth());
    result.heightProperty().bind(height);
    background.setTranslateY(-MinecraftSettings.DEFAULT.topBarHeight());
    background.setClip(result);
    background.setEffect(new BoxBlur(15, 15, 3));
  }

}
