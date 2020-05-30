package ru.nsu.wallpaper_search;

import ru.nsu.wallpaper_search.gui.GuiController;
import ru.nsu.wallpaper_search.scraper.Extractor;
import ru.nsu.wallpaper_search.tools.ChangeHandler;
import ru.nsu.wallpaper_search.tools.DataHandler;

import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        extractOne();
        new GuiController(new DataHandler(), new ChangeHandler());
    }

    private static void extractOne() {
        try (FileWriter fw = new FileWriter("./out.txt")) {
            var doc = Extractor.accessPicturesPage("1920x1080", "zelda");
            fw.write(String.valueOf(doc));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
