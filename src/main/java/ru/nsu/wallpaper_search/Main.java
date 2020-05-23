package ru.nsu.wallpaper_search;

import ru.nsu.wallpaper_search.gui.GuiController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        GuiController g = new GuiController(()-> System.out.println("search"),
                                ()-> System.out.println("change wallpapers"));
        ArrayList<BufferedImage> images = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            try {
                images.add(ImageIO.read(new File("/home/sofiya/NSU/upprpo/cat.jpg")));
                images.add(ImageIO.read(new File("/home/sofiya/NSU/upprpo/dog.jpg")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        g.drawImages(images);
    }

}
