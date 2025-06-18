package it.softEng.view.heatmap;

import it.softEng.model.ContributionData;
import it.softEng.view.MinecraftSettings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

import static it.softEng.view.abstractFactory.MinecraftFactory.createButton;

public class MinecraftHeatmapBuilder implements HeatmapBuilder {

  private ContributionData data;

  DoubleProperty availableWidth = new SimpleDoubleProperty();

  public MinecraftHeatmapBuilder() {
    data = new ContributionData();

  }

  @Override
  public Region build() {
    Region heatmapContainer = buildHeatmap();
    return getScrollable(heatmapContainer);
  }

  private Region buildHeatmap() {
    HBox result = new HBox();
    result.setAlignment(Pos.CENTER);
    result.getStyleClass().add("minecraft-heatmap");
    result.setMaxHeight(MinecraftSettings.DEFAULT.topBarHeight()*2);
    result.setSpacing(MinecraftSettings.DEFAULT.spacing());
    HBox.setHgrow(result, Priority.ALWAYS);

    result.getChildren().addAll(getHeatmapData(), getEntryButton());
    return result;
  }

  private Region getHeatmapData() {
    GridPane result = new ContributionMap(data);
    result.setAlignment(Pos.CENTER);
    return result;
  }
  private Node getEntryButton() {
    return createButton("Oggi ho letto");
  }

  private Region getScrollable(Region heatmapContainer) {
  ScrollPane result = new ScrollPane(heatmapContainer);
  result.getStyleClass().add("minecraft-scroll-pane");
  result.setMaxHeight(heatmapContainer.getMaxHeight() + 50);
  result.setFitToHeight(true);
  result.setFitToWidth(true);
  result.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
  result.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
  return result;
  }
}
