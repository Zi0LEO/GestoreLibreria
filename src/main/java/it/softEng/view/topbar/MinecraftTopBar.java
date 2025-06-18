package it.softEng.view.topbar;

import it.softEng.view.MinecraftSettings;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

public class MinecraftTopBar implements TopBarBuilder {
  private final Node[] nodes;
  public MinecraftTopBar(Node... nodes) {
    this.nodes = nodes;
  }

  @Override
  public Region build() {
    HBox bar = new HBox();
    bar.setAlignment(Pos.CENTER_LEFT);
    bar.setPrefHeight(MinecraftSettings.DEFAULT.topBarHeight());
    bar.setSpacing(MinecraftSettings.DEFAULT.spacing());
    bar.getChildren().addAll(nodes);
    bar.getStyleClass().add("minecraft-topbar");
    return bar;
  }
}
