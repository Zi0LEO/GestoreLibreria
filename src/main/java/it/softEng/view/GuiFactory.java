package it.softEng.view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.media.Media;

public interface GuiFactory {
  Button createButton(String text);
  Label createLabel();
  Media createBackground();

}
