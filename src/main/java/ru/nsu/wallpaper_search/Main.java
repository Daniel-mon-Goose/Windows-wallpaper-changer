package ru.nsu.wallpaper_search;

import ru.nsu.wallpaper_search.gui.GuiController;

public class Main {
    public static void main(String[] args) {
        new GuiController(()-> System.out.println("notify"));
    }

}
