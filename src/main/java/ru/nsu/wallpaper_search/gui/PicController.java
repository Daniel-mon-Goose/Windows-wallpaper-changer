package ru.nsu.wallpaper_search.gui;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PicController {
    private PicView view;

    public PicController() throws IOException {
        BufferedImage pic = ImageIO.read(new File("/home/alena/Desktop/cat.jpg"));
        view = new PicView(pic);
        view.pack();
        view.setVisible(true);
    }

}
