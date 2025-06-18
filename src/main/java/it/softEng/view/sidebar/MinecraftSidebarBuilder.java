package it.softEng.view.sidebar;

import it.softEng.view.MinecraftSettings;
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

  private final DoubleProperty height = new SimpleDoubleProperty();

  public MinecraftSidebarBuilder(Node background, Node...nodes) {
    this.nodes = nodes;
    this.background = cropBackground(background);
  }

  @Override
  public Region build() {
    Region content = buildContent();
    Pane sidebar = buildSidebar();

    sidebar.getChildren().add(background);
    sidebar.getChildren().add(content);
    sidebar.setMaxWidth(MinecraftSettings.DEFAULT.sidebarWidth());
    sidebar.setPrefWidth(MinecraftSettings.DEFAULT.sidebarWidth());
    StackPane.setAlignment(content, Pos.TOP_LEFT);
    StackPane.setAlignment(sidebar, Pos.TOP_LEFT);
    return sidebar;
  }

  private Pane buildSidebar() {
    StackPane result = new StackPane();
    height.bind(result.heightProperty());
    return result;
  }

  private Region buildContent() {
    VBox vbox = new VBox();
    vbox.getChildren().addAll(nodes);
    vbox.getStyleClass().add("minecraft-sidebar");
    vbox.setSpacing(MinecraftSettings.DEFAULT.spacing());
    StackPane.setAlignment(vbox, Pos.CENTER);
    return vbox;
  }

  private Node cropBackground(Node bg) {
    Rectangle result = new Rectangle();
    result.setTranslateY(MinecraftSettings.DEFAULT.topBarHeight());
    result.setWidth(MinecraftSettings.DEFAULT.sidebarWidth());
    result.setHeight(1000);
    bg.setTranslateY(-MinecraftSettings.DEFAULT.topBarHeight()*2);
    bg.setClip(result);
    return wrapInBounds(bg);
  }

  private Node wrapInBounds(Node bg) {
    Pane backgroundWrapper = new Pane(bg);
    backgroundWrapper.setPrefHeight(1000);
    backgroundWrapper.setMaxWidth(MinecraftSettings.DEFAULT.sidebarWidth());
    backgroundWrapper.setEffect(new BoxBlur(15, 15, 3));
    backgroundWrapper.setTranslateY(MinecraftSettings.DEFAULT.topBarHeight());
    return backgroundWrapper;
  }


}
