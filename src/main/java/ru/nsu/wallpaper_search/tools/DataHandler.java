package ru.nsu.wallpaper_search.tools;

import ru.nsu.wallpaper_search.scraper.Scraper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataHandler implements Runnable{

    private Integer width = 1920;
    private Integer height = 1080;
    private String theme = "cat";
    private ArrayList<BufferedImage> thumbnails;
    private List<PicCell> links;
    private boolean error = false;


    @Override
    public void run()  {

        try {
            thumbnails = new ArrayList<>();
            links = new ArrayList<>();
            links = new Scraper().respondWithQuery(width.toString() + "x" + height.toString(), theme);
            for (var thumb : links) {
                thumbnails.add(ImageIO.read(new File(ImageLoader.loadThumbnail(thumb))));
            }
        } catch (IOException e) {
            error = true;
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

    public boolean isError() {
        return error;
    }
}