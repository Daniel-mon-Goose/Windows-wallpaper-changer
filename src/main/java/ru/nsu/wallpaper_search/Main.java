package ru.nsu.wallpaper_search;

import ru.nsu.wallpaper_search.gui.GuiController;
import ru.nsu.wallpaper_search.tools.ChangeHandler;
import ru.nsu.wallpaper_search.tools.DataHandler;

public class Main {
    public static void main(String[] args) {
        new GuiController(new DataHandler(), new ChangeHandler());
    }
}
