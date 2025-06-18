package it.softEng.view;

public record MinecraftSettings(
    double spacing,
    double topBarHeight,
    double sidebarWidth
) {
  public static final MinecraftSettings DEFAULT = new MinecraftSettings(5, 80, 200);
}
