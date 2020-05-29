package ru.nsu.wallpaper_search.tools;

import ru.nsu.wallpaper_search.scraper.Scraper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataHandler implements Runnable{

    Integer width = 1920;
    Integer height = 1080;
    String theme = "cat";
    ArrayList<BufferedImage> thumbnails;
    List<PicCell> links;


    @Override
    public void run() {
        try {
            thumbnails = new ArrayList<>();
            links = new ArrayList<>();
            links = new Scraper().respondWithQuery(width.toString() + "x" + height.toString(), theme);
            for (var thumb : links) {
                thumbnails.add(ImageIO.read(new File(ImageLoader.loadThumbnail(thumb))));
            }
        } catch (IOException e) {
            //ignore
        }
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public List<BufferedImage> getThumbnails() {
        return thumbnails;
    }

    public List<PicCell> getLinks() {
        return links;
    }
}