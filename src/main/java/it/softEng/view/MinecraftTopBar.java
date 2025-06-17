package it.softEng.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

public class MinecraftTopBar implements TopBarBuilder {
  private final Node[] nodes;
  public MinecraftTopBar(Node... nodes) {
    this.nodes = nodes;
  }

  @Override
  public Region build() {
    HBox bar = new HBox();
    bar.setSpacing(MinecraftSettings.DEFAULT.spacing());
    bar.setMaxHeight(MinecraftSettings.DEFAULT.topBarHeight());
    bar.setPadding(new Insets(MinecraftSettings.DEFAULT.spacing()));

    bar.getChildren().addAll(nodes);

    bar.getStyleClass().add("minecraft-topbar");
    StackPane.setAlignment(bar, Pos.TOP_CENTER);
    return bar;
  }
}
