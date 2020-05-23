package ru.nsu.wallpaper_search;

import ru.nsu.wallpaper_search.gui.GuiController;
import ru.nsu.wallpaper_search.tools.DataHandler;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        GuiController g = new GuiController(()-> System.out.println("search"),
                                ()-> System.out.println("change wallpapers"));

        ArrayList<BufferedImage> images = new DataHandler().findPics(g.getWidth(), g.getHeight(), g.getRequest());

        g.drawImages(images);
    }

}
