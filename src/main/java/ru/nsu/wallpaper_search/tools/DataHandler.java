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
    ArrayList<BufferedImage> thumbnails = new ArrayList<>();
    List<PicCell> links;
    public DataHandler() {}

  //  public ArrayList<BufferedImage> findPics() {}

    @Override
    public void run() {
        try {
            links = new Scraper().respondWithQuery(width.toString() + "x" + height.toString(), theme);
            //TODO: download thumbnails

            //dummy
            //for (int i = 0; i < 15; i++) {
                for (var thumb: links) {
                    thumbnails.add(ImageIO.read(new File(ImageLoader.loadThumbnail(thumb))));
                }
                //thumbnails.add(ImageIO.read(new File("./src/main/resources/cat.jpg")));
                //thumbnails.add(ImageIO.read(new File("./src/main/resources/dog.jpg")));
            //}
        } catch (IOException e) {
            //ignore
        }
        //return thumbnails;
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

    public ArrayList<BufferedImage> getThumbnails() {
        return thumbnails;
    }

    public List<PicCell> getLinks() {
        return links;
    }
}