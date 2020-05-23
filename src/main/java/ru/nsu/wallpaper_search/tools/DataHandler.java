package ru.nsu.wallpaper_search.tools;

import ru.nsu.wallpaper_search.scraper.Scraper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataHandler{
    public DataHandler() {}

    public ArrayList<BufferedImage> findPics(Integer width, Integer height, String theme) {
        List<PicCell> links;
        ArrayList<BufferedImage> thumbnails = new ArrayList<>();
        try {
            links = new Scraper().respondWithQuery(width.toString() + "x" + height.toString(), theme);
            //TODO: download thumbnails

            //dummy
            for (int i = 0; i < 15; i++) {
                thumbnails.add(ImageIO.read(new File("./src/main/resources/cat.jpg")));
                thumbnails.add(ImageIO.read(new File("./src/main/resources/dog.jpg")));
            }
        } catch (IOException e) {
            //ignore
        }
        return thumbnails;
    }
}

/*

        }
*/