package ru.nsu.wallpaper_search.gui;

public class GuiController {
    private Gui view;

    public GuiController() {
        view = new Gui();
        view.pack();
        view.setVisible(true);
    }
}
